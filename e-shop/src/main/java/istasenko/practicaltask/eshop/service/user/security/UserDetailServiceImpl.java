package istasenko.practicaltask.eshop.service.user.security;

import istasenko.practicaltask.eshop.model.User;
import istasenko.practicaltask.eshop.model.security.CustomUserDetails;
import istasenko.practicaltask.eshop.service.user.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static java.util.Objects.isNull;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
    private final UserService userService;

    public UserDetailServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.getByName(username);
        System.out.println(user.getId());
        return new CustomUserDetails(user);
    }
}
