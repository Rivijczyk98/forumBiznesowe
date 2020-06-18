package pl.edu.pb.wi.forumbiznesowe;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import pl.edu.pb.wi.forumbiznesowe.controller.*;

@SpringBootTest
class ForumBiznesoweApplicationTests {

	private AuthController authController;
	private CategoryController categoryController;
	private PostController postController;
	private ReplyController replyController;
	private ReportController reportController;
	private UserController userController;

	@BeforeAll
	static void initialize(){

	}

	@Test
	void contextLoads() {

	}

}
