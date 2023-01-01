package perillaleaves.shop.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import perillaleaves.shop.domain.item.Item;
import perillaleaves.shop.domain.item.ItemColor;
import perillaleaves.shop.repository.ItemColorRepository;

import java.util.List;

@Transactional
@Service
public class ItemColorService {

    private final ItemColorRepository itemColorRepository;

    public ItemColorService(ItemColorRepository itemColorRepository) {
        this.itemColorRepository = itemColorRepository;
    }

    public List<ItemColor> findAllByItem(Item item) {
        return itemColorRepository.findByItem(item);
    }

    public List<ItemColor> findAllByItemId(Long item_id) {
        return itemColorRepository.findByItemId(item_id);
    }


}
