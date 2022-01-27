package istasenko.practicaltask.eshop.repository.user;

import istasenko.practicaltask.eshop.model.User;

import java.util.Optional;

public interface UserRepository {
    Optional<User> getByName(String username);

}
