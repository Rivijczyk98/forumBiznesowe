package pl.edu.pb.wi.forumbiznesowe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.edu.pb.wi.forumbiznesowe.dao.entity.User;
import pl.edu.pb.wi.forumbiznesowe.pojo.UsernameResponse;
import pl.edu.pb.wi.forumbiznesowe.service.UserServiceImpl;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserServiceImpl userService;

    @Autowired
    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping("/all")
    public List<User> getUsers() {
        return userService.findAll();
    }

    @GetMapping
    public User findUser(@RequestParam Long id) {
        return userService.findById(id);
    }

    @GetMapping("/username")
    public UsernameResponse getUsername(@RequestParam Long id){
        return new UsernameResponse(userService.getUsername(id)) ;
    }

    @PostMapping("/add")
    public User addUser(@RequestBody User user) {
        return userService.save(user);
    }

    @PatchMapping("/give")
    public User giveRole(@RequestBody User user, @RequestParam String role){
        return userService.giveRole(user, role);
    }

//    @PutMapping("/give")
//    public User giveAdmin(@RequestBody RoleChangeRequest roleChangeRequest) {
//        return userService.giveRole(roleChangeRequest.getId(), roleChangeRequest.getRole());
//    }

    @DeleteMapping("/delete")
    public void deleteUser(@RequestParam Long id) {
        userService.delete(id);
    }

}
