package perillaleaves.shop.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import perillaleaves.shop.domain.Item;
import perillaleaves.shop.domain.Kinds;
import perillaleaves.shop.exception.APIError;
import perillaleaves.shop.repository.ItemRepository;
import perillaleaves.shop.request.item.ItemDTO;

@Service
public class ItemService {

    private final ItemRepository itemRepository;
    private int price;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public Item create(ItemDTO itemDTO) {
        validate(itemDTO);
        Item item = mapper(itemDTO);

        return itemRepository.save(item);
    }

    public Item update(Long item_id, int stock) {
        Item item = stockUpdate(item_id, stock);

        return itemRepository.save(item);
    }

    public Page<Item> findAll(Pageable pageable) {
        return itemRepository.findAll(pageable);
    }

    public Item findById(Long item_id) {
        return itemRepository.findById(item_id).orElse(null);
    }

    public Page<Item> findAllByKind(Kinds kind, Pageable pageable) {
        return itemRepository.findAllByKind(kind, pageable);
    }

    private void validate(ItemDTO itemDTO) {
        price = itemDTO.getPrice();
        if (itemDTO.getName().isBlank()) {
            throw new APIError("EmptyName", "상품 이름을 입력해주세요.");
        }
        if (itemDTO.getPrice() < 0) {
            throw new APIError("Cost", "상품 가격을 확인해주세요.");
        }
    }

    private static Item mapper(ItemDTO itemDTO) {
        return new Item(itemDTO.getName(), itemDTO.getPrice(), itemDTO.getKind());
    }

    private Item stockUpdate(Long item_id, int stock) {
        if (stock < 0) {
            throw new APIError("CheckAgainStock", "수량을 다시 확인해주세요.");
        }

        Item item = itemRepository.findById(item_id).orElse(null);
        item.setStock(stock);

        return item;
    }
}
