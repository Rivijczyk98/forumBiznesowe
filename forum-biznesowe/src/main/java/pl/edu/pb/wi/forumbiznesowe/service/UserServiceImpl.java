package pl.edu.pb.wi.forumbiznesowe.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pb.wi.forumbiznesowe.dao.RoleRepository;
import pl.edu.pb.wi.forumbiznesowe.dao.UserRepository;
import pl.edu.pb.wi.forumbiznesowe.dao.entity.Role;
import pl.edu.pb.wi.forumbiznesowe.dao.entity.User;
import pl.edu.pb.wi.forumbiznesowe.dao.entity.enums.RoleEnum;
import pl.edu.pb.wi.forumbiznesowe.service.interfaces.UserService;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(Long id) {
        Optional<User> user = userRepository.findById(id);

        return user.orElse(null);
    }

    @Override
    public User giveRole(User user, String role) {
        Optional<User> userToUpdateRole = userRepository.findById(user.getId());
        Optional<Role> newRole = Optional.empty();

        if (userToUpdateRole.isPresent()){
            switch (role) {
                case "ROLE_USER":
                    newRole = roleRepository.findByName(RoleEnum.ROLE_USER);
                    break;
                case "ROLE_VIP":
                    newRole = roleRepository.findByName(RoleEnum.ROLE_VIP);
                    break;
                case "ROLE_MODERATOR":
                    newRole = roleRepository.findByName(RoleEnum.ROLE_MODERATOR);
                    break;
                case "ROLE_ADMIN":
                    newRole = roleRepository.findByName(RoleEnum.ROLE_ADMIN);
                    break;
            }

            if(newRole != null && newRole.isPresent()){
                Set<Role> newRoles = new HashSet<>();
                newRoles.add(newRole.get());
                userToUpdateRole.get().setRoles(newRoles);

                System.out.println("Nadano rolę administratora użytkownikowi " + user.getUsername());
                return userRepository.save(userToUpdateRole.get());
            } else {
                System.out.println("Nie znaleziono roli " + role);
                return userRepository.save(user);
            }

        } else {
            System.out.println("Nie znaleziono uzytkownika o nazwie " + user.getUsername());
            return userRepository.save(user);
        }
    }

    @Override
    public void delete(Long id) {
        Optional<User> user = userRepository.findById(id);

        if(user.isPresent()){
            userRepository.delete(user.get());
            System.out.println("Pomyślnie usunięto użytkownika");
        }
        else {
            System.out.println("Nie znaleziono użytkownika poprzez id");
        }
    }


    @Override
    public User save(User user) {
        return null;
    }
}
