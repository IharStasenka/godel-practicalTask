package istasenko.practicaltask.eshop.converter.order;

import istasenko.practicaltask.eshop.dto.OrderDto;
import istasenko.practicaltask.eshop.model.Order;

import java.util.Set;

public interface OrderConverter {
    OrderDto fromOrder(Order order);
    Set<OrderDto> fromOrderSet(Set<Order> order);
}
