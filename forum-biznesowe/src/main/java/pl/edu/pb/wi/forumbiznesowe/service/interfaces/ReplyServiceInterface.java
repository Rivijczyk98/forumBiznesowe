package pl.edu.pb.wi.forumbiznesowe.service.interfaces;

import pl.edu.pb.wi.forumbiznesowe.dao.entity.Reply;

import java.util.Optional;

public interface ReplyServiceInterface {

    Iterable<Reply> findAll();

    Optional<Reply> findById(Long id);

    Iterable<Reply> findByPostId(Long id);

    void addReply(Reply reply);

    void updateReply(Reply reply);

    void deleteReply(Long id);
}
