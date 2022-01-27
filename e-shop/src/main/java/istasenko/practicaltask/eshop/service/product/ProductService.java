package istasenko.practicaltask.eshop.service.product;

import istasenko.practicaltask.eshop.model.Product;

import java.util.Set;

public interface ProductService {
    Set<Product> getAllProduct();
    Product getProduct(Long productId);
}
