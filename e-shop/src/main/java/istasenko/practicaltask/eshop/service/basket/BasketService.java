package istasenko.practicaltask.eshop.service.basket;

import istasenko.practicaltask.eshop.model.Basket;
import istasenko.practicaltask.eshop.model.security.CustomUserDetails;

public interface BasketService {
    Basket get(CustomUserDetails userDetails, Long basketId);
    Basket addProduct(CustomUserDetails userDetails, Long productId, Long basketId);
    Basket removeProduct(CustomUserDetails userDetails, Long productId, Long basketId);
    void clear(Basket basket);

}
