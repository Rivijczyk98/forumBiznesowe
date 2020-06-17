package pl.edu.pb.wi.forumbiznesowe.service.interfaces;

import pl.edu.pb.wi.forumbiznesowe.dao.entity.User;
import pl.edu.pb.wi.forumbiznesowe.dao.entity.enums.RoleEnum;

import java.util.List;

public interface UserService {
    List<User> findAll();

    User findById(Long id);

    User giveRole(Long id, RoleEnum role);

    void delete(Long id);

    User save(User user);
}
