package istasenko.practicaltask.eshop.service.user;

import istasenko.practicaltask.eshop.model.User;

public interface UserService {
    User getByName(String username);
    boolean isContainsBasket(User user, Long basketId);
}
