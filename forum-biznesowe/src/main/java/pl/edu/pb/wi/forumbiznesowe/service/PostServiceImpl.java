package pl.edu.pb.wi.forumbiznesowe.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pb.wi.forumbiznesowe.dao.PostRepository;
import pl.edu.pb.wi.forumbiznesowe.dao.entity.Post;
import pl.edu.pb.wi.forumbiznesowe.dao.entity.Reply;
import pl.edu.pb.wi.forumbiznesowe.dao.entity.enums.PostStatusEnum;
import pl.edu.pb.wi.forumbiznesowe.pojo.PostRequest;
import pl.edu.pb.wi.forumbiznesowe.service.interfaces.PostService;
import pl.edu.pb.wi.forumbiznesowe.service.interfaces.ReplyService;

import java.util.LinkedList;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final ReplyService replyService;

    @Autowired
    public PostServiceImpl(PostRepository postRepository, ReplyService replyService) {
        this.postRepository = postRepository;
        this.replyService = replyService;
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
    public void update(PostRequest post) {
        if(find(post.getId()).isPresent())
        {
            Post p = find(post.getId()).get();
            p.setText(post.getText());
            postRepository.save(p);
        }

    }

    @Override
    public void delete(Post post) {
        postRepository.delete(post);
    }

    @Override
    public void delete(Long id) {
        postRepository.deleteById(id);
        for(Reply r: replyService.findByPostId(id)){
            replyService.deleteReply(r.getId());
        }
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

    public void changeIsObserved(Boolean isObserved, Post post){
        if(find(post.getId()).isPresent()){
            post.setObserved(isObserved);
            postRepository.save(post);
        }
    }

}
