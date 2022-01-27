package istasenko.practicaltask.eshop.converter.product;

import istasenko.practicaltask.eshop.dto.ProductDto;
import istasenko.practicaltask.eshop.model.Product;

import java.util.Set;

public interface ProductConverter {
    Product convertFromProductDto(ProductDto productDto);
    ProductDto convertFromProduct(Product product);
    Set<ProductDto> convertFromProductSet(Set<Product> products);
}
