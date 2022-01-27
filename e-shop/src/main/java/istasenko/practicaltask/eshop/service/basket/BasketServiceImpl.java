package istasenko.practicaltask.eshop.service.basket;

import istasenko.practicaltask.eshop.exception.EmptyBasketException;
import istasenko.practicaltask.eshop.model.Basket;
import istasenko.practicaltask.eshop.model.BasketedProduct;
import istasenko.practicaltask.eshop.model.Product;
import istasenko.practicaltask.eshop.model.User;
import istasenko.practicaltask.eshop.model.security.CustomUserDetails;
import istasenko.practicaltask.eshop.repository.basket.BasketRepository;
import istasenko.practicaltask.eshop.service.product.ProductService;
import istasenko.practicaltask.eshop.service.user.UserService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class BasketServiceImpl implements BasketService {

    private final UserService userService;
    private final BasketRepository basketRepository;
    private final ProductService productService;

    public BasketServiceImpl(UserService userService, BasketRepository basketRepository, ProductService productService) {
        this.userService = userService;
        this.basketRepository = basketRepository;
        this.productService = productService;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)
    public Basket get(CustomUserDetails userDetails, Long basketId) {
        Basket basket = getBasket(userDetails, basketId);
        basket.getBasketedProducts().forEach(basketedProduct -> basketedProduct.getProduct().getTitle());
        return basket;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)
    public Basket addProduct(CustomUserDetails userDetails, Long productId, Long basketId) {
        Basket basket = getBasket(userDetails, basketId);
        Product product = productService.getProduct(productId);
        Optional<BasketedProduct> basketedProduct = basketRepository.getProductStatus(basket, product);
        basketedProduct.ifPresentOrElse(
                value -> {
                    value.increaseQuantity();
                    basketRepository.update(basket);
                },
                () -> basketRepository.setProduct(product, basket)
        );
        return basket;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)
    public Basket removeProduct(CustomUserDetails userDetails, Long productId, Long basketId) {
        Basket basket = getBasket(userDetails, basketId);
        Product product = productService.getProduct(productId);
        Optional<BasketedProduct> basketedProduct = basketRepository.getProductStatus(basket, product);
        basketedProduct.ifPresentOrElse(
                value -> {
                    if (value.getQuantity() > 1) {
                        value.decreaseQuantity();
                        basketRepository.update(basket);
                    } else if (value.getQuantity().equals(1L)) {
                        value.destruct();
                        basketRepository.deleteBasketedProduct(value, basket);
                    }
                },
                () -> {
                    throw new EmptyBasketException("there are no such a product in the basket");
                });
        return basket;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)
    public void clear(Basket basket) {
        basketRepository.deleteAllBasketedProducts(basket);

    }

    private Basket getBasket(CustomUserDetails userDetails, Long basketId) {
        User user = userService.getByName(userDetails.getUsername());
        userService.isContainsBasket(user, basketId);
        return basketRepository.findById(user.getId()).orElseThrow(
                () -> new UsernameNotFoundException(String.format("There are no basket with id %d", user.getId())));
    }


}
