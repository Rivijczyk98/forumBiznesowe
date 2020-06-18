package pl.edu.pb.wi.forumbiznesowe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import pl.edu.pb.wi.forumbiznesowe.controller.PostController;
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

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class ForumBiznesoweApplication {

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	PostRepository postRepository;

	@Autowired
	CategoryRepository categoryRepository;

	public static void main(String[] args) {
		SpringApplication.run(ForumBiznesoweApplication.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	public void fillDB(){
		Role a = new Role(RoleEnum.ROLE_USER);
		Role b = new Role(RoleEnum.ROLE_MODERATOR);
		Role c = new Role(RoleEnum.ROLE_VIP);
		Role d = new Role(RoleEnum.ROLE_ADMIN);

		roleRepository.save(a);
		roleRepository.save(b);
		roleRepository.save(c);
		roleRepository.save(d);

		User user = new User("user","user@onet.pl","$2a$10$3ZP90w4a0j7aDReadREEQutjB69O9RPeufNNxZaIszvll.aDlSeI2"); //pass = 123456
		Set<Role> userRole = new HashSet<>();
		userRole.add(a);
		user.setRoles(userRole);
		userRepository.save(user);

		User vip = new User("vip","vip@onet.pl","$2a$10$3ZP90w4a0j7aDReadREEQutjB69O9RPeufNNxZaIszvll.aDlSeI2"); //pass = 123456
		Set<Role> vipRole = new HashSet<>();
		vipRole.add(b);
		vip.setRoles(vipRole);
		userRepository.save(vip);

		User mod = new User("mod","mod@onet.pl","$2a$10$3ZP90w4a0j7aDReadREEQutjB69O9RPeufNNxZaIszvll.aDlSeI2"); //pass = 123456
		Set<Role> modRole = new HashSet<>();
		modRole.add(c);
		mod.setRoles(modRole);
		userRepository.save(mod);

		User admin = new User("admin","admin@onet.pl","$2a$10$3ZP90w4a0j7aDReadREEQutjB69O9RPeufNNxZaIszvll.aDlSeI2"); //pass = 123456
		Set<Role> adminRole = new HashSet<>();
		adminRole.add(d);
		admin.setRoles(adminRole);
		userRepository.save(admin);

		Category category1 = new Category("Programowanie","Zagadnienia objemujące programowanie i tematyki z nim związene");

		categoryRepository.save(category1);

		Post post1 = new Post(user, category1, "Zmienne w Javie", "Mógłby mi ktoś wymienić wszystkie zmienne jakie występują w Javie?", PostStatusEnum.PENDING, Calendar.getInstance().getTime());
		Post post2 = new Post(vip, category1, "Java - typy zmiennoprzecinkowe", "Jakie są typy zmiennoprzycinkowe w Javie?", PostStatusEnum.APPROVED, Calendar.getInstance().getTime());

		postRepository.save(post1);
		postRepository.save(post2);
	}


}
