package pl.edu.pb.wi.forumbiznesowe.service.interfaces;

import org.springframework.http.ResponseEntity;
import pl.edu.pb.wi.forumbiznesowe.dao.entity.Reply;

import java.util.Optional;

public interface ReplyService {

    Iterable<Reply> findAll();

    Optional<Reply> findById(Long id);

    Iterable<Reply> findByPostId(Long id);

    ResponseEntity<Object> addReply(Long postId, Reply reply);

    void updateReply(Reply reply);

    void deleteReply(Long id);
}
