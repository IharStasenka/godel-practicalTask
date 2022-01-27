package istasenko.practicaltask.eshop.service.order;

import istasenko.practicaltask.eshop.exception.EmptyBasketException;
import istasenko.practicaltask.eshop.model.Basket;
import istasenko.practicaltask.eshop.model.Order;
import istasenko.practicaltask.eshop.model.OrderedProduct;
import istasenko.practicaltask.eshop.model.User;
import istasenko.practicaltask.eshop.model.security.CustomUserDetails;
import istasenko.practicaltask.eshop.repository.order.OrderRepository;
import istasenko.practicaltask.eshop.service.basket.BasketService;
import istasenko.practicaltask.eshop.service.user.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.stream.Collectors;

import static org.springframework.transaction.annotation.Propagation.REQUIRED;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final UserService userService;
    private final BasketService basketService;

    public OrderServiceImpl(OrderRepository orderRepository, UserService userService, BasketService basketService) {
        this.orderRepository = orderRepository;
        this.userService = userService;
        this.basketService = basketService;
    }

    @Override
    @Transactional(propagation = REQUIRED, isolation = Isolation.SERIALIZABLE)
    public Order setOrder(CustomUserDetails userDetails, Long basketId) {
        User user = userService.getByName(userDetails.getUsername());
        Basket basket = basketService.get(userDetails, basketId);
        if (basket.getBasketedProducts().isEmpty()) {
            throw new EmptyBasketException("Basket is empty");
        }
        Order order = orderRepository.create(user);
        order.setItems(getOrderedProductsFromBasket(order, basket));
        basketService.clear(basket);
        orderRepository.update(order);
        return order;
    }

    private Set<OrderedProduct> getOrderedProductsFromBasket(Order order, Basket basket) {
        return  basket.getBasketedProducts()
                .stream()
                .map(basketedProduct -> new OrderedProduct(basketedProduct, order))
                .collect(Collectors.toSet());
    }

    @Override
    @Transactional(propagation = REQUIRED, isolation = Isolation.SERIALIZABLE)
    public Set<Order> getAllOrders(CustomUserDetails userDetails) {
        User user = userService.getByName(userDetails.getUsername());
        return orderRepository.findAllByUserId(user);
    }
}
