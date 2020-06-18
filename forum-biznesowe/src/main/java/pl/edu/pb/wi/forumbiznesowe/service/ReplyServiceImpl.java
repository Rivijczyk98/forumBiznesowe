package pl.edu.pb.wi.forumbiznesowe.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.edu.pb.wi.forumbiznesowe.dao.PostRepository;
import pl.edu.pb.wi.forumbiznesowe.dao.ReplyRepository;
import pl.edu.pb.wi.forumbiznesowe.dao.entity.Post;
import pl.edu.pb.wi.forumbiznesowe.dao.entity.Reply;
import pl.edu.pb.wi.forumbiznesowe.dao.entity.User;
import pl.edu.pb.wi.forumbiznesowe.service.interfaces.EmailService;
import pl.edu.pb.wi.forumbiznesowe.service.interfaces.ReplyService;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class ReplyServiceImpl implements ReplyService {

    private ReplyRepository replyRepository;
    private PostRepository postRepository;

    private EmailServiceImpl emailService;

    @Autowired
    public ReplyServiceImpl(ReplyRepository replyRepository, PostRepository postRepository, EmailServiceImpl emailService) {
        this.replyRepository = replyRepository;
        this.postRepository = postRepository;
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
    public ResponseEntity<Object> addReply(Long postId, Reply reply) {
        Optional<Post> repliedPostOptional = postRepository.findById(postId);

        if (repliedPostOptional.isPresent()) {
            reply.setPost(repliedPostOptional.get());
            replyRepository.save(reply);

            if(reply.getPost().getIsObserved()){
                emailService.sendAutomaticGenerated(reply.getPost().getAuthor().getEmail(), reply.getPost().getId());
            }

            return ResponseEntity.ok().body("Reply added successfuly");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Post with id " + postId + " not found");
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
