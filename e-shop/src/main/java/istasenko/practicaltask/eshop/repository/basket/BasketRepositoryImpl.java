package istasenko.practicaltask.eshop.repository.basket;

import istasenko.practicaltask.eshop.model.Basket;
import istasenko.practicaltask.eshop.model.BasketedProduct;
import istasenko.practicaltask.eshop.model.Product;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
@Repository
public class BasketRepositoryImpl implements BasketRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public Optional<Basket> findById(Long basketId) {
        EntityGraph entityGraph =  entityManager.getEntityGraph("basket-entity-graph");
        Map<String, Object> properties = new HashMap<>();
        properties.put("javax.persistence.fetchgraph", entityGraph);
        try{
            Basket basket = entityManager.find(Basket.class,basketId, properties);
            return Optional.of(basket);
        } catch (NoResultException exception) {
            return Optional.empty();
        }
    }

    @Override
    public Basket setProduct(Product product, Basket basket) {
        BasketedProduct basketedProduct = new BasketedProduct(product, basket);
        //basket.addBasketedProduct(basketedProduct);
        entityManager.persist(basket);
        return basket;
    }

    @Override
    public Basket update(Basket basket) {
        entityManager.merge(basket);
        return basket;
    }

    @Override
    public Basket deleteBasketedProduct(BasketedProduct basketedProduct, Basket basket) {
        entityManager.remove(basketedProduct);
        return null;
    }

    @Override
    public Basket deleteAllBasketedProducts(Basket basket) {
        basket.getBasketedProducts().forEach(basketedProduct -> entityManager.remove(basketedProduct));
        basket.setBasketedProducts(new HashSet<>());
        entityManager.merge(basket);
        return basket;
    }

    @Override
    public Optional<BasketedProduct> getProductStatus(Basket basket, Product product) {
        try{
            return Optional.of(entityManager
                    .createQuery("select bp from BasketedProduct bp where bp.basket =: basket and bp.product =:product", BasketedProduct.class)
                    .setParameter("basket", basket)
                    .setParameter("product", product)
                    .getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }
}
