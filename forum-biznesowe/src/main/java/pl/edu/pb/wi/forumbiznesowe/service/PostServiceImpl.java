package pl.edu.pb.wi.forumbiznesowe.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pb.wi.forumbiznesowe.dao.PostRepository;
import pl.edu.pb.wi.forumbiznesowe.dao.entity.Post;
import pl.edu.pb.wi.forumbiznesowe.dao.entity.enums.PostStatusEnum;
import pl.edu.pb.wi.forumbiznesowe.service.interfaces.PostService;

import java.util.LinkedList;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    @Autowired
    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public Iterable<Post> findAll() {
        return postRepository.findAll();
    }

    @Override
    public Optional<Post> find(Long id) {
        return postRepository.findById(id);
    }

    @Override
    public void add(Post post, long idUser, String categoryName) {
        Optional<Category> category = categoryRepository.findByName(categoryName);
        Optional<User> user = userRepository.findById(idUser);

        Optional<Role> userRole = roleRepository.findByName(RoleEnum.ROLE_USER);

        if (user.isPresent() && category.isPresent() && userRole.isPresent()) {

            if (user.get().getRoles().contains(userRole.get())) {
                logger.info("Post będzie oczekiwał na zatwierdzenie");
                post.setStatus(PostStatusEnum.PENDING);
            } else {
                logger.info("Post nie wymaga zatwierdzenia");
                post.setStatus(PostStatusEnum.APPROVED);
            }
            post.setAuthor(user.get());
            post.setCategory(category.get());

            logger.info("Dodano post");
            postRepository.save(post);

        } else {
            logger.error("Nie znaleziono użytkownika lub kategorii");
        }
    }

    @Override
    public void suggest(Post post) {
        post.setStatus(PostStatusEnum.PENDING);
        postRepository.save(post);
    }

    @Override
    public void accept(Post post) {
        post.setStatus(PostStatusEnum.APPROVED);
        postRepository.save(post);
    }

    @Override
    public void update(Post post) {
        postRepository.save(post);
    }

    @Override
    public void delete(Post post) {
        postRepository.delete(post);
    }

    @Override
    public void delete(Long id) {
        postRepository.deleteById(id);
    }

    public Iterable<Post> getPostsByCategory(Long id) {
        Iterable<Post> list = findAll();
        LinkedList<Post> newList = new LinkedList<>();
        for (Post p : list) {
            if (p.getCategory().getId().equals(id)) {
                newList.add(p);
            }
        }
        return newList;
    }

    public void changeIsObserved(Long id) {
        if (find(id).isPresent()) {
            Post post = find(id).get();
            post.setObserved(!post.getIsObserved());
            postRepository.save(post);
        }
    }

    public void changeIsObserved(Boolean isObserved, Post post) {
        if (find(post.getId()).isPresent()) {
            post.setObserved(isObserved);
            postRepository.save(post);
        }
    }

}
