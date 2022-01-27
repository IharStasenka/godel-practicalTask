package istasenko.practicaltask.eshop.repository.product;

import istasenko.practicaltask.eshop.model.Product;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public class ProductRepositoryImpl implements ProductRepository {
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public Set<Product> getAllProducts() {
        List<Product> products = entityManager.createQuery("SELECT p FROM Product p", Product.class).getResultList();
        return new LinkedHashSet<>(products);
    }

    @Override
    public Optional<Product> findById(Long productId) {
        try {
            Product product = entityManager.createQuery("SELECT p FROM Product p WHERE p.id = :id", Product.class)
                    .setParameter("id", productId)
                    .getSingleResult();
            return Optional.of(product);
        } catch (NoResultException noResultException) {
            return Optional.empty();
        }

    }
}
