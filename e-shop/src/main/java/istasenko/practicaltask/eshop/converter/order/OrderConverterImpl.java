package istasenko.practicaltask.eshop.converter.order;

import istasenko.practicaltask.eshop.dto.OrderDto;
import istasenko.practicaltask.eshop.dto.processedproductsdto.OrderedProductDto;
import istasenko.practicaltask.eshop.model.Order;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class OrderConverterImpl implements OrderConverter {
    @Override
    public OrderDto fromOrder(Order order) {
        Set<OrderedProductDto> orderedProducts = getOrderedProductDtos(order);
        return new OrderDto(order.getId(), order.getOrderPrice(), order.getTimestamp(), orderedProducts);
    }

    @Override
    public Set<OrderDto> fromOrderSet(Set<Order> order) {
        return order.stream().map(this::fromOrder).collect(Collectors.toSet());
    }

    private Set<OrderedProductDto> getOrderedProductDtos(Order order) {
        return order.getItems()
                .stream()
                .map(item ->
                        new OrderedProductDto(
                                item.getProduct().getId(),
                                item.getProduct().getTitle(),
                                item.getProduct().getPrice(),
                                item.getQuantity(),
                                item.getTotal()
                                ))
                .collect(Collectors.toSet());
    }
}
