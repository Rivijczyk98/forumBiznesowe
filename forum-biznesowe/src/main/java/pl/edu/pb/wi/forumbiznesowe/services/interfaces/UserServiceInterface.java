package pl.edu.pb.wi.forumbiznesowe.services.interfaces;

import pl.edu.pb.wi.forumbiznesowe.dao.entity.enums.RoleEnum;
import pl.edu.pb.wi.forumbiznesowe.dao.entity.User;

import java.util.List;

public interface UserServiceInterface {
    List<User> findAll();

    User findById(Long id);

    User giveRole(User user, String role);

    void delete(Long id);

    User save(User user);
}
