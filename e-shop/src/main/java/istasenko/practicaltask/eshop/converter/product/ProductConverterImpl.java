package istasenko.practicaltask.eshop.converter.product;

import istasenko.practicaltask.eshop.dto.ProductDto;
import istasenko.practicaltask.eshop.model.Product;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ProductConverterImpl implements ProductConverter {
    @Override
    public Product convertFromProductDto(ProductDto productDto) {
        return new Product(productDto.getTitle(), productDto.getPrice());
    }

    @Override
    public ProductDto convertFromProduct(Product product) {
        return new ProductDto(product.getId(), product.getTitle(), product.getPrice());
    }

    @Override
    public Set<ProductDto> convertFromProductSet(Set<Product> products) {
        return products.stream().map(this::convertFromProduct).collect(Collectors.toSet());
    }
}
