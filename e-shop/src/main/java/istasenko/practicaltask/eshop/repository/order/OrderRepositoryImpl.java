package istasenko.practicaltask.eshop.repository.order;

import istasenko.practicaltask.eshop.model.Order;
import istasenko.practicaltask.eshop.model.User;
import istasenko.practicaltask.eshop.repository.user.UserRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class OrderRepositoryImpl implements OrderRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public Order create(User user) {
        Order order = new Order(user);
        entityManager.persist(order);
        return order;
    }

    @Override
    public Order update(Order order) {
        entityManager.merge(order);
        return null;
    }

    @Override
    public Set<Order> findAllByUserId(User user) {
        List<Order> orders = entityManager.createQuery("select o from Order o where o.user =:user", Order.class)
                .setParameter("user", user)
                .setHint(
                        "javax.persistence.fetchgraph",
                        entityManager.getEntityGraph("graph.OrderOrderedProductProduct"))
                .getResultList();
        return new HashSet<>(orders);
    }
}
