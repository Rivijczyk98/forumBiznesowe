package pl.edu.pb.wi.forumbiznesowe.services;

import org.springframework.stereotype.Service;
import pl.edu.pb.wi.forumbiznesowe.dao.UserRepository;
import pl.edu.pb.wi.forumbiznesowe.dao.entity.User;
import pl.edu.pb.wi.forumbiznesowe.services.interfaces.UserServiceInterface;

import java.util.List;

@Service
public class UserService implements UserServiceInterface {
    public List<User> findAll() {
        return null;
    }

    public User findById(Long id) {
        return null;
    }
}
