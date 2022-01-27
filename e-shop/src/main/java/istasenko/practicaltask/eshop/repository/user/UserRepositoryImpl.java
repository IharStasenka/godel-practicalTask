package istasenko.practicaltask.eshop.repository.user;

import istasenko.practicaltask.eshop.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@Repository
public class UserRepositoryImpl implements UserRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public Optional<User> getByName(String username) {
        try {
            User user = entityManager.createQuery(
                    "select u from User u where u.username = :username", User.class)
                    .setParameter("username", username)
                    .setHint(
                            "javax.persistence.fetchgraph",
                            entityManager.getEntityGraph("user-basket-entity-graph"))
                    .getSingleResult();
            return Optional.of(user);
        } catch (NoResultException exception) {
            return Optional.empty();
        }
    }
}
