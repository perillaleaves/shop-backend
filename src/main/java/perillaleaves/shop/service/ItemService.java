package perillaleaves.shop.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import perillaleaves.shop.domain.item.CartItem;
import perillaleaves.shop.domain.item.Item;
import perillaleaves.shop.domain.item.ItemColor;
import perillaleaves.shop.exception.APIError;
import perillaleaves.shop.repository.CartItemRepository;
import perillaleaves.shop.repository.ItemColorRepository;
import perillaleaves.shop.repository.ItemRepository;
import perillaleaves.shop.request.item.ItemDTO;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ItemService {

    private final ItemRepository itemRepository;
    private final ItemColorRepository itemColorRepository;
    private final CartItemRepository cartItemRepository;

    public ItemService(ItemRepository itemRepository, ItemColorRepository itemColorRepository, CartItemRepository cartItemRepository) {
        this.itemRepository = itemRepository;
        this.itemColorRepository = itemColorRepository;
        this.cartItemRepository = cartItemRepository;
    }

    public ItemColor create(ItemDTO itemDTO) {
        validate(itemDTO);
        Item item = mapper(itemDTO);

        Optional<Item> itemByName = itemRepository.findByName(itemDTO.getName());
        if (itemByName.isEmpty()) {
            itemRepository.save(item);
            ItemColor itemColor = new ItemColor(item, itemDTO.getColor());
            return itemColorRepository.save(itemColor);
        }

        if (itemByName.get().getPrice() != itemDTO.getPrice()) {
            throw new APIError("inconsistentPrice", "해당 상품의 금액과 일치하지 않습니다.");
        }

        List<ItemColor> findItemColor = itemColorRepository.findByItem(itemByName.get());
        if (findItemColor.stream().filter(f -> f.getColor().equals(itemDTO.getColor())).findFirst().isPresent()) {
            throw new APIError("ExistItem", "이미 존재하는 아이템입니다.");
        }

        ItemColor itemColor = new ItemColor(item, itemDTO.getColor());
        itemColor.setItem(itemByName.get());

        return itemColorRepository.save(itemColor);
    }

    public ItemColor update(Long color_id, int stock) {
        ItemColor itemColor = updateStock(color_id, stock);

        return itemColorRepository.save(itemColor);
    }

    public Page<Item> findAll(Pageable pageable) {
        return itemRepository.findAll(pageable);
    }

    public Item findById(Long item_id) {
        return itemRepository.findById(item_id).orElse(null);
    }

    public List<Item> findAll() {
        return itemRepository.findAll();
    }

    public void deleteItem(Long item_id, Long color_id) {
        List<CartItem> cartItems = cartItemRepository.findByItemColorId(color_id);
        for (CartItem cartItem : cartItems) {
            cartItemRepository.deleteById(cartItem.getId());
        }

        itemColorRepository.deleteById(color_id);
        List<ItemColor> itemColors = itemColorRepository.findByItemId(item_id);
        if (itemColors.isEmpty()) {
            itemRepository.deleteById(item_id);
        }
    }

    private void validate(ItemDTO itemDTO) {
        if (itemDTO.getName().isBlank()) {
            throw new APIError("EmptyName", "상품 이름을 입력해주세요.");
        }
        if (itemDTO.getPrice() < 0) {
            throw new APIError("Cost", "상품 가격을 확인해주세요.");
        }
    }

    private static Item mapper(ItemDTO itemDTO) {
        return new Item(itemDTO.getName(), itemDTO.getPrice());
    }

    private ItemColor updateStock(Long color_id, int stock) {
        if (stock < 0) {
            throw new APIError("CheckAgainStock", "수량을 다시 확인해주세요.");
        }

        ItemColor itemColor = itemColorRepository.findById(color_id).orElse(null);
        itemColor.setStock(stock);

        return itemColor;
    }
}
