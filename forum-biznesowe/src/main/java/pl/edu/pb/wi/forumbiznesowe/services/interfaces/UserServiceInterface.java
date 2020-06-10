package pl.edu.pb.wi.forumbiznesowe.services.interfaces;

import pl.edu.pb.wi.forumbiznesowe.dao.entity.ERole;
import pl.edu.pb.wi.forumbiznesowe.dao.entity.User;

import java.util.List;

public interface UserServiceInterface {
    List<User> findAll();

    User findById(Long id);

    User giveRole(Long id, ERole role);

    void delete(Long id);

    User save(User user);
}
