package istasenko.practicaltask.eshop.controller;

import istasenko.practicaltask.eshop.converter.product.ProductConverter;
import istasenko.practicaltask.eshop.dto.ProductDto;
import istasenko.practicaltask.eshop.model.Product;
import istasenko.practicaltask.eshop.model.security.CustomUserDetails;
import istasenko.practicaltask.eshop.service.product.ProductService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Set;

@Controller
@Validated
public class ProductController {
    private final ProductService productService;
    private final ProductConverter productConverter;

    public ProductController(ProductService productService, ProductConverter productConverter) {
        this.productService = productService;
        this.productConverter = productConverter;
    }

    @GetMapping(value = "/products")
    @ResponseBody
    public ResponseEntity<Set<ProductDto>> getAllProducts(CustomUserDetails customUserDetails) {
        Set<Product> products = productService.getAllProduct();
        return ResponseEntity.
                ok().
                contentType(MediaType.APPLICATION_JSON).
                body(productConverter.convertFromProductSet(products));
    }


}
