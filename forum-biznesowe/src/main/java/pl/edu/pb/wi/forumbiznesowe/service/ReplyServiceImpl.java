package pl.edu.pb.wi.forumbiznesowe.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pb.wi.forumbiznesowe.dao.ReplyRepository;
import pl.edu.pb.wi.forumbiznesowe.dao.entity.Reply;
import pl.edu.pb.wi.forumbiznesowe.service.interfaces.ReplyServiceInterface;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class ReplyServiceImpl implements ReplyServiceInterface {

    private ReplyRepository replyRepository;

    @Autowired
    public ReplyServiceImpl(ReplyRepository replyRepository) {
        this.replyRepository = replyRepository;
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
            if(r.getPost().getId().equals(id)) replyList.add(r);
        });
        return replyList;
    }

    @Override
    public void addReply(Reply reply) {
        replyRepository.save(reply);
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
