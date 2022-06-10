package test.kata.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import test.kata.model.Role;
import test.kata.model.User;
import test.kata.repository.RoleRepositoryTest;
import test.kata.repository.UserRepositoryTest;

import java.util.List;
import java.util.Set;


@Service
public class UserServiceImp implements UserService {


    private final UserRepositoryTest userRepositoryTest;
    private final RoleRepositoryTest roleRepository;

    @Autowired
    public UserServiceImp(UserRepositoryTest userRepositoryTest, RoleRepositoryTest roleRepository) {
        this.userRepositoryTest = userRepositoryTest;
        this.roleRepository = roleRepository;   }





    public List<Role> listRoles() {
        return roleRepository.findAll();
    }



    @Transactional
    public void save(User user, Set<Role> roles) {
        user.setRoles(roles);
        userRepositoryTest.save(user);
    }


    @Transactional
    public void delete(long id) {
        userRepositoryTest.deleteById(id);
    }


    public User getUserBiId(long id) {
        return userRepositoryTest.getOne(id);
    }


    public List<User> all() {
        return userRepositoryTest.findAll();
    }
}
