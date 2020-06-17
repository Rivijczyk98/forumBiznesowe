package pl.edu.pb.wi.forumbiznesowe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.edu.pb.wi.forumbiznesowe.dao.entity.Reply;
import pl.edu.pb.wi.forumbiznesowe.service.ReplyServiceImpl;

import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/replays")
public class ReplyController {

    private final ReplyServiceImpl replyServiceImpl;

    @Autowired
    public ReplyController(ReplyServiceImpl replyServiceImpl) {
        this.replyServiceImpl = replyServiceImpl;
    }

    @GetMapping("/all")
    public Iterable<Reply> findAll(){
        return replyServiceImpl.findAll();
    }

    @GetMapping
    public Optional<Reply> findById(@RequestParam Long id){
        return replyServiceImpl.findById(id);
    }

    @GetMapping("/post")
    public Iterable<Reply> findAllByPost(@RequestParam Long id){
        return replyServiceImpl.findByPostId(id);
    }

    @PostMapping
    public void addReply(@RequestBody Reply reply){
        replyServiceImpl.addReply(reply);
    }

    @PutMapping
    public void updateReply(@RequestBody Reply reply){
        replyServiceImpl.updateReply(reply);
    }

    @DeleteMapping
    public void deleteReply(@RequestParam Long id){
        replyServiceImpl.deleteReply(id);
    }

}
