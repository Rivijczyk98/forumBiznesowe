package pl.edu.pb.wi.forumbiznesowe.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pb.wi.forumbiznesowe.dao.CategoryRepository;
import pl.edu.pb.wi.forumbiznesowe.dao.PostRepository;
import pl.edu.pb.wi.forumbiznesowe.dao.UserRepository;
import pl.edu.pb.wi.forumbiznesowe.dao.entity.Category;
import pl.edu.pb.wi.forumbiznesowe.dao.entity.Post;
import pl.edu.pb.wi.forumbiznesowe.dao.entity.User;
import pl.edu.pb.wi.forumbiznesowe.dao.entity.enums.PostStatusEnum;
import pl.edu.pb.wi.forumbiznesowe.service.interfaces.PostService;

import java.util.LinkedList;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private UserRepository userRepository;

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
        post.setStatus(PostStatusEnum.APPROVED);

        Optional<Category> category = categoryRepository.findByName(categoryName);
        Optional<User> user = userRepository.findById(idUser);

        if(user.isPresent() && category.isPresent()){
            post.setAuthor(user.get());
            post.setCategory(category.get());

            postRepository.save(post);
        } else {
            System.out.println("Nie znaleziono użytkownika lub kategorii");
        }
    }

    @Override
    public void suggest(Post post, long idUser, String categoryName) {
        post.setStatus(PostStatusEnum.PENDING);
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

    public Iterable<Post> getPostsByCategory(String name){
        Iterable<Post> list = findAll();
        LinkedList<Post> newList = new LinkedList<>();

        for(Post p : list){
            if(p.getCategory().getName().toLowerCase().equals(name.toLowerCase())){
                newList.add(p);
            }
        }

        return newList;
    }
}
