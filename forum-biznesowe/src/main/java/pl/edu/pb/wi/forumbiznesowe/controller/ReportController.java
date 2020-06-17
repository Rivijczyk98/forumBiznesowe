package pl.edu.pb.wi.forumbiznesowe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.pb.wi.forumbiznesowe.services.ReplyService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/replays")
public class ReportController {

    private final ReplyService replyService;

    @Autowired
    public ReportController(ReplyService replyService) {
        this.replyService = replyService;
    }
}
