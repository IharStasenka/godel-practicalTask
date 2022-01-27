package istasenko.practicaltask.eshop.controller;

import istasenko.practicaltask.eshop.converter.basket.BasketConverter;
import istasenko.practicaltask.eshop.dto.ProductDto;
import istasenko.practicaltask.eshop.dto.processedproductsdto.BasketedProductDto;
import istasenko.practicaltask.eshop.model.Basket;
import istasenko.practicaltask.eshop.model.security.CustomUserDetails;
import istasenko.practicaltask.eshop.service.basket.BasketService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Set;

import static org.springframework.http.HttpHeaders.LOCATION;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@RestController
@Validated
@RequestMapping(value = "/baskets")
public class BasketController {

    private final BasketService basketService;
    private final BasketConverter basketConverter;

    public BasketController(BasketService basketService, BasketConverter basketConverter) {
        this.basketService = basketService;
        this.basketConverter = basketConverter;
    }

    @GetMapping(value = "/{basketId}")
    @ResponseBody
    public ResponseEntity<Set<BasketedProductDto>> get(@AuthenticationPrincipal CustomUserDetails customUserDetails,
                                                      @PathVariable("basketId") Long basketId) {
        Basket basket = basketService.get(customUserDetails, basketId);
        return ResponseEntity
                .status(OK)
                .contentType(APPLICATION_JSON)
                .body(basketConverter.fromBasket(basket).getBasketedProductDtos());
    }

    @PostMapping(value = "/{basketId}/products")
    @ResponseBody
    public ResponseEntity<Long> add(@AuthenticationPrincipal CustomUserDetails customUserDetails,
                                    @PathVariable("basketId") Long basketId,
                                    @RequestBody ProductDto productDto) {
        Basket updatedBasket = basketService.addProduct(customUserDetails, productDto.getId(), basketId);
        String currentUri = ServletUriComponentsBuilder.fromCurrentContextPath().toUriString();
        String savedOrderLocation = currentUri + "/baskets/" + updatedBasket.getId();

        return ResponseEntity
                .status(CREATED)
                .header(LOCATION, savedOrderLocation)
                .body(updatedBasket.getId());
    }

    @DeleteMapping(value = "/{basketId}/products")
    @ResponseBody
    public ResponseEntity<Long> delete(@AuthenticationPrincipal CustomUserDetails customUserDetails,
                                       @PathVariable("basketId") Long basketId,
                                       @RequestBody ProductDto productDto) {
        Basket updatedBasket = basketService.removeProduct(customUserDetails, productDto.getId(), basketId);
        String currentUri = ServletUriComponentsBuilder.fromCurrentContextPath().toUriString();
        String savedOrderLocation = currentUri + "/baskets/" + updatedBasket.getId();

        return ResponseEntity
                .status(OK)
                .header(LOCATION, savedOrderLocation)
                .body(updatedBasket.getId());
    }


}
