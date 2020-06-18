package pl.edu.pb.wi.forumbiznesowe.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.edu.pb.wi.forumbiznesowe.dao.PostRepository;
import pl.edu.pb.wi.forumbiznesowe.dao.ReplyRepository;
import pl.edu.pb.wi.forumbiznesowe.dao.UserRepository;
import pl.edu.pb.wi.forumbiznesowe.dao.entity.Post;
import pl.edu.pb.wi.forumbiznesowe.dao.entity.Reply;
import pl.edu.pb.wi.forumbiznesowe.dao.entity.User;
import pl.edu.pb.wi.forumbiznesowe.pojo.ReplyRequest;
import pl.edu.pb.wi.forumbiznesowe.service.interfaces.EmailService;
import pl.edu.pb.wi.forumbiznesowe.service.interfaces.ReplyService;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class ReplyServiceImpl implements ReplyService {

    private ReplyRepository replyRepository;
    private PostRepository postRepository;
    private UserRepository userRepository;
    private EmailServiceImpl emailService;

    @Autowired
    public ReplyServiceImpl(ReplyRepository replyRepository, PostRepository postRepository, UserRepository userRepository, EmailServiceImpl emailService) {
        this.replyRepository = replyRepository;
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.emailService = emailService;
    }

    @Override
    public Iterable<Reply> findAll() {
        return replyRepository.findAll();
    }

    @Override
    public Optional<Reply> findById(Long id) {
        return replyRepository.findById(id);
    }

    @Override
    public Iterable<Reply> findByPostId(Long id) {
        List<Reply> replyList = new LinkedList<>();
        findAll().forEach(r -> {
            if (r.getPost().getId().equals(id)) replyList.add(r);
        });
        return replyList;
    }

    @Override
    public Reply addReply(ReplyRequest reply) {
        Optional<Post> repliedPostOptional = postRepository.findById(reply.getPost());
        Optional<User> userOptional = userRepository.findById(reply.getAuthor());

        Reply r = new Reply();
        r.setText(reply.getText());

        if (repliedPostOptional.isPresent() && userOptional.isPresent()) {
            r.setPost(repliedPostOptional.get());
            r.setAuthor(userOptional.get());
            replyRepository.save(r);

            if(r.getPost().getIsObserved()){
                emailService.sendAutomaticGenerated(r.getPost().getAuthor().getEmail(), r.getPost().getId());
            }

            return r;
        }
        return null;
    }

    @Override
    public void updateReply(Reply reply) {
        replyRepository.save(reply);
    }

    @Override
    public void deleteReply(Long id) {
        replyRepository.deleteById(id);
    }
}
