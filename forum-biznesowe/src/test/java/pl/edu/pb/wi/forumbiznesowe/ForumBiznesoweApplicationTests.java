package pl.edu.pb.wi.forumbiznesowe;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.edu.pb.wi.forumbiznesowe.dao.CategoryRepository;
import pl.edu.pb.wi.forumbiznesowe.dao.PostRepository;
import pl.edu.pb.wi.forumbiznesowe.dao.RoleRepository;
import pl.edu.pb.wi.forumbiznesowe.dao.UserRepository;
import pl.edu.pb.wi.forumbiznesowe.dao.entity.Category;
import pl.edu.pb.wi.forumbiznesowe.dao.entity.Post;
import pl.edu.pb.wi.forumbiznesowe.dao.entity.Role;
import pl.edu.pb.wi.forumbiznesowe.dao.entity.User;
import pl.edu.pb.wi.forumbiznesowe.dao.entity.enums.PostStatusEnum;
import pl.edu.pb.wi.forumbiznesowe.dao.entity.enums.RoleEnum;
import pl.edu.pb.wi.forumbiznesowe.pojo.CategoryRequest;
import pl.edu.pb.wi.forumbiznesowe.service.CategoryServiceImpl;
import pl.edu.pb.wi.forumbiznesowe.service.PostServiceImpl;
import pl.edu.pb.wi.forumbiznesowe.service.UserServiceImpl;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ForumBiznesoweApplicationTests {

    @Autowired
    UserRepository userRepository;
    @Autowired
    PostRepository postRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    UserServiceImpl userService;
    @Autowired
    CategoryServiceImpl categoryService;
    @Autowired
    PostServiceImpl postService;

    Role a, b, c, d;

    @BeforeEach
    public void setup() {
        a = new Role(RoleEnum.ROLE_USER);
        b = new Role(RoleEnum.ROLE_VIP);
        c = new Role(RoleEnum.ROLE_MODERATOR);
        d = new Role(RoleEnum.ROLE_ADMIN);

        roleRepository.save(a);
        roleRepository.save(b);
        roleRepository.save(c);
        roleRepository.save(d);
    }

    @AfterEach
    public void cleanUp() {
        roleRepository.deleteAll();
    }

    @Transactional
    @Test
    public void testUser() {
        User u = new User("username", "email@gmail.com", "$2a$10$3ZP90w4a0j7aDReadREEQutjB69O9RPeufNNxZaIszvll.aDlSeI2");

        Set<Role> userRole = new HashSet<>();
        userRole.add(a);
        u.setRoles(userRole);
        userRepository.save(u);

        Optional<User> user = userRepository.findByUsername("username");

        assertTrue(user.isPresent());
    }

    @Transactional
    @Test
    public void deleteUser() {
        User u = new User("username", "email@gmail.com", "$2a$10$3ZP90w4a0j7aDReadREEQutjB69O9RPeufNNxZaIszvll.aDlSeI2");

        Set<Role> userRole = new HashSet<>();
        userRole.add(a);
        u.setRoles(userRole);
        userRepository.save(u);

        userService.delete(u.getId());

        Optional<User> user = userRepository.findByUsername("username");

        assertFalse(user.isPresent());
    }

    @Transactional
    @Test
    public void addCategoryPresent() {
        CategoryRequest cr = new CategoryRequest();
        cr.setName("Programowanie");
        cr.setDescription("Zagadnienia objemujące programowanie i tematyki z nim związene");
        categoryService.saveCategory(cr);

        List<Category> categories = categoryRepository.findAll();

        assertNotNull(categories);
    }

    @Transactional
    @Test
    public void addCategoryEquals() {
        CategoryRequest cr = new CategoryRequest();
        cr.setName("Programowanie");
        cr.setDescription("Zagadnienia objemujące programowanie i tematyki z nim związene");
        categoryService.saveCategory(cr);

        List<Category> categories = categoryRepository.findAll();

        assertEquals("Programowanie", categories.get(0).getName().toString());
    }

    @Transactional
    @Test
    public void addPost() {
        User user = new User("user", "user@onet.pl", "$2a$10$3ZP90w4a0j7aDReadREEQutjB69O9RPeufNNxZaIszvll.aDlSeI2"); //pass = 123456
        Set<Role> userRole = new HashSet<>();
        userRole.add(a);
        user.setRoles(userRole);
        userRepository.save(user);

        User vip = new User("vip", "vip@onet.pl", "$2a$10$3ZP90w4a0j7aDReadREEQutjB69O9RPeufNNxZaIszvll.aDlSeI2"); //pass = 123456
        Set<Role> vipRole = new HashSet<>();
        vipRole.add(b);
        vip.setRoles(vipRole);
        userRepository.save(vip);

        Category category1 = new Category("Programowanie", "Zagadnienia objemujące programowanie i tematyki z nim związene");

        categoryRepository.save(category1);

        Post post1 = new Post(user, category1, "Zmienne w Javie", "Mógłby mi ktoś wymienić wszystkie zmienne jakie występują w Javie?", PostStatusEnum.PENDING);
        Post post2 = new Post(vip, category1, "Java - typy zmiennoprzecinkowe", "Jakie są typy zmiennoprzycinkowe w Javie?", PostStatusEnum.APPROVED);
        postRepository.save(post2);

        List<Post> posts = postRepository.findAll();

        assertNotNull(posts);
    }

}
