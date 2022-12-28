package perillaleaves.shop.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import perillaleaves.shop.domain.enumList.Kinds;
import perillaleaves.shop.domain.item.Item;
import perillaleaves.shop.domain.item.ItemColor;
import perillaleaves.shop.exception.APIError;
import perillaleaves.shop.repository.ItemColorRepository;
import perillaleaves.shop.repository.ItemRepository;
import perillaleaves.shop.request.item.ItemDTO;

import java.util.Optional;

@Service
@Transactional
public class ItemService {

    private final ItemRepository itemRepository;
    private final ItemColorRepository itemColorRepository;
    private int price;

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

        ItemColor findItemColor = itemColorRepository.findByItem(itemByName.get());
        if (findItemColor.getColor().equals(itemDTO.getColor())) {
            throw new APIError("ExistItem", "이미 존재하는 아이템입니다.");
        }

        ItemColor itemColor = new ItemColor(item, itemDTO.getColor());
        itemColor.setItem(itemByName.get());

        return itemColorRepository.save(itemColor);
    }

//    public Item update(Long item_id, int stock) {
//        Item item = stockUpdate(item_id, stock);
//
//        return itemRepository.save(item);
//    }

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

//    private Item stockUpdate(Long item_id, int stock) {
//        if (stock < 0) {
//            throw new APIError("CheckAgainStock", "수량을 다시 확인해주세요.");
//        }
//
//        Item item = itemRepository.findById(item_id).orElse(null);
//        item.setStock(stock);
//
//        return item;
//    }
}
