package istasenko.practicaltask.eshop.converter.basket;

import istasenko.practicaltask.eshop.dto.BasketDto;
import istasenko.practicaltask.eshop.model.Basket;

public interface BasketConverter {
    BasketDto fromBasket(Basket basket);

}
