package istasenko.practicaltask.eshop.service.user;

import istasenko.practicaltask.eshop.model.User;
import istasenko.practicaltask.eshop.repository.user.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.transaction.annotation.Isolation.SERIALIZABLE;
import static org.springframework.transaction.annotation.Propagation.REQUIRED;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional(propagation = REQUIRED, isolation = SERIALIZABLE)
    public User getByName(String username) {
        return userRepository.getByName(username).orElseThrow(
                ()-> new UsernameNotFoundException(String.format("There are no user with name: %s",username)));
    }

    @Override
    @Transactional(propagation = REQUIRED, isolation = SERIALIZABLE)
    public boolean isContainsBasket(User user, Long basketId)  {
        if (user.getBasket().getId().equals(basketId)) return false;
        else
            throw new org.springframework.security.access.AccessDeniedException(
                    String.format("Current user doesn't have access to the basket with id: %d", basketId));
    }
}
