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
    public void add(Post post) {
        post.setStatus(PostStatusEnum.APPROVED);
        postRepository.save(post);
    }

    @Override
    public void suggest(Post post) {
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

    public Iterable<Post> getPostsByCategory(Long id){
        Iterable<Post> list = findAll();
        LinkedList<Post> newList = new LinkedList<>();
        for(Post p : list){
            if(p.getCategory().getId().equals(id)){
                newList.add(p);
            }
        }
        return newList;
    }
}
