package Services;

import entity.*;
import repositories.OrderRepository;
import repositories.PaymentRepository;
import repositories.DishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class OrderService {
    @Autowired private OrderRepository orderRepository;
    @Autowired private PaymentRepository paymentRepository;
    @Autowired private DishRepository dishRepository;

    public Order placeOrder(Order order){

        for(OrderItem item : order.getItems()){
            item.setPriceAtOrder(item.getDish().getPrice());
        }
        order.setStatus(OrderStatus.ORDERED);
        // calculate total
        double total = order.getItems().stream().mapToDouble(i -> i.getPriceAtOrder() * i.getQuantity()).sum();
        order.setTotalAmount(total - order.getDiscountApplied());
        return orderRepository.save(order);
    }

    public List<Order> getOrdersForUser(Long userId){
        return orderRepository.findByUserId(userId);
    }

    public Order updateStatus(Long orderId, OrderStatus status, String trackingInfo){
        Order o = orderRepository.findById(orderId).orElseThrow();
        o.setStatus(status);
        o.setTrackingInfo(trackingInfo);
        return orderRepository.save(o);
    }
}
