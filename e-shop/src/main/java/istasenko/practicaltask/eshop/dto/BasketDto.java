package istasenko.practicaltask.eshop.dto;

import istasenko.practicaltask.eshop.dto.processedproductsdto.BasketedProductDto;

import java.util.HashSet;
import java.util.Set;

public class
BasketDto {

    Set<BasketedProductDto> basketedProductDtos = new HashSet<>();

    public BasketDto() {
    }

    public BasketDto(Set<BasketedProductDto> basketDto) {
        this.basketedProductDtos = basketDto;
    }

    public Set<BasketedProductDto> getBasketedProductDtos() {
        return basketedProductDtos;
    }

    public void setBasketedProductDtos(Set<BasketedProductDto> basketedProductDtos) {
        this.basketedProductDtos = basketedProductDtos;
    }

    public void addBasketedProductDto(BasketedProductDto basketedProductDto) {
        basketedProductDtos.add(basketedProductDto);
    }


}
