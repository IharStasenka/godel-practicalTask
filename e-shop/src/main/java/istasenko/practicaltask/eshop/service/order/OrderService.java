package istasenko.practicaltask.eshop.service.order;

import istasenko.practicaltask.eshop.model.Order;
import istasenko.practicaltask.eshop.model.security.CustomUserDetails;

import java.util.Set;

public interface OrderService {
    Order setOrder(CustomUserDetails userDetails, Long basketId);
    Set<Order> getAllOrders(CustomUserDetails userDetails);
}
