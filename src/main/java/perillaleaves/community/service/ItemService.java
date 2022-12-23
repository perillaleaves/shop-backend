package perillaleaves.community.service;

import org.springframework.stereotype.Service;
import perillaleaves.community.domain.Item;
import perillaleaves.community.exception.APIError;
import perillaleaves.community.repository.ItemRepository;
import perillaleaves.community.request.item.ItemDTO;

@Service
public class ItemService {

    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public Item create(ItemDTO itemDTO) {
        validate(itemDTO);
        Item item = new Item(itemDTO.getName(), itemDTO.getPrice(), itemDTO.getStock(), itemDTO.getKind());

        return itemRepository.save(item);
    }

    private void validate(ItemDTO itemDTO) {
        if (itemDTO.getName().isBlank()) {
            throw new APIError("EmptyName", "상품 이름을 입력해주세요.");
        }
        if (itemDTO.getPrice() > 0) {
            throw new APIError("Cost", "상품 가격을 확인해주세요.");
        }


    }
}
