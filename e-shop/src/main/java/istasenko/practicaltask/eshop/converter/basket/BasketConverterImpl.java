package istasenko.practicaltask.eshop.converter.basket;

import istasenko.practicaltask.eshop.dto.BasketDto;
import istasenko.practicaltask.eshop.dto.processedproductsdto.BasketedProductDto;
import istasenko.practicaltask.eshop.model.Basket;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class BasketConverterImpl implements BasketConverter {
    @Override
    public BasketDto fromBasket(Basket basket) {
        return new BasketDto(
                basket.getBasketedProducts().stream()
                        .map(basketedProduct ->
                                new BasketedProductDto(
                                        basketedProduct.getProduct().getTitle(),
                                        basketedProduct.getQuantity(),
                                        basketedProduct.getTotal()
                                )
                        )
                        .collect(Collectors.toSet()));
    }
}
