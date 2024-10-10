package ku.cs.cafe.service;

import ku.cs.cafe.common.Status;
import ku.cs.cafe.entity.Menu;
import ku.cs.cafe.entity.OrderItem;
import ku.cs.cafe.entity.OrderItemKey;
import ku.cs.cafe.entity.PurchaseOrder;
import ku.cs.cafe.request.AddCartRequest;
import ku.cs.cafe.repository.MenuRepository;
import ku.cs.cafe.repository.OrderItemRepository;
import ku.cs.cafe.repository.PurchaseOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class OrderService {
    @Autowired
    private PurchaseOrderRepository orderRepository;

    @Autowired
    private OrderItemRepository itemRepository;

    @Autowired
    private MenuRepository menuRepository;

    public PurchaseOrder getCurrentOrder() {
        PurchaseOrder currentOrder = orderRepository.findByStatus(Status.ORDER);
        if (currentOrder == null) {
            PurchaseOrder newOrder = new PurchaseOrder();
            newOrder.setStatus(Status.ORDER);
            currentOrder = orderRepository.save(newOrder);
        }
        return currentOrder;
    }

    public void order(UUID menuId, AddCartRequest request) {
        PurchaseOrder currentOrder = getCurrentOrder();
        Menu menu = menuRepository.findById(menuId).get();
        OrderItem item = new OrderItem();

        item.setId(new OrderItemKey(currentOrder.getId(), menuId));
        item.setPurchaseOrder(currentOrder);
        item.setMenu(menu);
        item.setQuantity(request.getQuantity());
        itemRepository.save(item);
    }

    public void submitOrder() {
        PurchaseOrder currentOrder =
                orderRepository.findByStatus(Status.ORDER);
        currentOrder.setTimestamp(LocalDateTime.now());
        currentOrder.setStatus(Status.CONFIRM);
        orderRepository.save(currentOrder);
    }

}
