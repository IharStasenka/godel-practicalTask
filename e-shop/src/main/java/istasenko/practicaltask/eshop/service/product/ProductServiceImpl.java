package istasenko.practicaltask.eshop.service.product;

import istasenko.practicaltask.eshop.exception.ProductNotFoundException;
import istasenko.practicaltask.eshop.model.Product;
import istasenko.practicaltask.eshop.repository.product.ProductRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)
    public Set<Product> getAllProduct() {
        return productRepository.getAllProducts();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)
    public Product getProduct(Long productId) {
        return productRepository.findById(productId).orElseThrow(
                ()-> new ProductNotFoundException(String.format("Item doesn’t exist with id: %d", productId))
        );
    }
}
