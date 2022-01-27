package istasenko.practicaltask.eshop.repository.basket;

import istasenko.practicaltask.eshop.model.Basket;
import istasenko.practicaltask.eshop.model.BasketedProduct;
import istasenko.practicaltask.eshop.model.Product;

import java.util.Optional;

public interface BasketRepository {
    Optional<Basket> findById(Long basketId);
    Basket setProduct(Product product, Basket basket);
    Basket update(Basket basket);
    Basket deleteBasketedProduct(BasketedProduct basketedProduct, Basket basket);
    Basket deleteAllBasketedProducts(Basket basket);
    Optional<BasketedProduct> getProductStatus(Basket basket, Product product);

}
