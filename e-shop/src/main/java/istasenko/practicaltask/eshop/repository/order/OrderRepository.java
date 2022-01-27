package istasenko.practicaltask.eshop.repository.order;

import istasenko.practicaltask.eshop.model.Order;
import istasenko.practicaltask.eshop.model.User;

import java.util.Set;

public interface OrderRepository {
    Order create(User user);
    Order update(Order order);
    Set<Order> findAllByUserId(User user);

}
