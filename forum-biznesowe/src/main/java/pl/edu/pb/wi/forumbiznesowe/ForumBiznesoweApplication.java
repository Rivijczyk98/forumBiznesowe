package pl.edu.pb.wi.forumbiznesowe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import pl.edu.pb.wi.forumbiznesowe.dao.RoleRepository;
import pl.edu.pb.wi.forumbiznesowe.dao.entity.Role;
import pl.edu.pb.wi.forumbiznesowe.dao.entity.enums.RoleEnum;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class ForumBiznesoweApplication {

	@Autowired
	RoleRepository roleRepository;

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


	}


}
