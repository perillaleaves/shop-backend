package perillaleaves.shop.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import perillaleaves.shop.domain.item.Item;
import perillaleaves.shop.domain.item.ItemColor;
import perillaleaves.shop.exception.APIError;
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

    public ItemService(ItemRepository itemRepository, ItemColorRepository itemColorRepository) {
        this.itemRepository = itemRepository;
        this.itemColorRepository = itemColorRepository;
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

    public ItemColor update(Long color_id, Long item_id, int stock) {
        ItemColor itemColor = stockUpdate(color_id, item_id, stock);

        return itemColorRepository.save(itemColor);
    }

    public Page<Item> findAll(Pageable pageable) {
        return itemRepository.findAll(pageable);
    }

    public Item findById(Long item_id) {
        return itemRepository.findById(item_id).orElse(null);
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

    private ItemColor stockUpdate(Long color_id, Long item_id, int stock) {
        if (stock < 0) {
            throw new APIError("CheckAgainStock", "수량을 다시 확인해주세요.");
        }

        Item item = itemRepository.findById(item_id).orElse(null);
        ItemColor itemColor = itemColorRepository.findByIdAndItem(color_id, item);
        itemColor.setStock(stock);

        return itemColor;
    }
}
