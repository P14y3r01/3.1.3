package test.kata.service;

import org.springframework.security.core.userdetails.UserDetails;
import test.kata.model.Role;
import test.kata.model.User;

import java.util.List;
import java.util.Set;

public interface UserService {
    void save(User user, Set<Role> roles);
    void delete(long id);
    User getUserBiId(long id);
    List<User> all();

    List<Role> listRoles();
}
