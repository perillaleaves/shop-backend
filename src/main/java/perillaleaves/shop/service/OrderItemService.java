package perillaleaves.shop.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import perillaleaves.shop.domain.item.OrderItem;
import perillaleaves.shop.repository.OrderItemRepository;

import java.util.List;

@Service
@Transactional
public class OrderItemService {

    private final OrderItemRepository orderItemRepository;

    public OrderItemService(OrderItemRepository orderItemRepository) {
        this.orderItemRepository = orderItemRepository;
    }

    public List<OrderItem> findOrderItems(Long order_id) {

        return orderItemRepository.findByOrdersId(order_id);
    }
}
