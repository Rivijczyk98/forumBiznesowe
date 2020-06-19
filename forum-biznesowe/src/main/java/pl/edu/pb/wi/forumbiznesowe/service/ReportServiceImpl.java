package pl.edu.pb.wi.forumbiznesowe.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.edu.pb.wi.forumbiznesowe.dao.*;
import pl.edu.pb.wi.forumbiznesowe.dao.entity.Post;
import pl.edu.pb.wi.forumbiznesowe.dao.entity.Reply;
import pl.edu.pb.wi.forumbiznesowe.dao.entity.Report;
import pl.edu.pb.wi.forumbiznesowe.dao.entity.User;
import pl.edu.pb.wi.forumbiznesowe.dao.entity.enums.ReportedObjectEnum;
import pl.edu.pb.wi.forumbiznesowe.pojo.ReportRequest;
import pl.edu.pb.wi.forumbiznesowe.service.interfaces.ReportService;
import pl.edu.pb.wi.forumbiznesowe.util.EnumChecker;

import java.util.Optional;

@Service
public class ReportServiceImpl implements ReportService {

    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final ReplyRepository replyRepository;
    private final ReportRepository reportRepository;

    @Autowired
    public ReportServiceImpl(CategoryRepository categoryRepository,
                             UserRepository userRepository,
                             PostRepository postRepository,
                             ReplyRepository replyRepository,
                             ReportRepository reportRepository
    ) {
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
        this.postRepository = postRepository;
        this.replyRepository = replyRepository;
        this.reportRepository = reportRepository;
    }

    @Override
    public ResponseEntity<Object> createReport(ReportRequest reportRequest) {
        Optional<User> optionalAuthor = userRepository.findById(reportRequest.getAuthorId());
        if (!optionalAuthor.isPresent()) {
            return ResponseEntity.badRequest().body("User of id " + reportRequest.getAuthorId() + " does not exist");
        }

        if (!EnumChecker.isInEnum(reportRequest.getReportedObjectType(), ReportedObjectEnum.class)) {
            return ResponseEntity.badRequest()
                    .body(reportRequest.getReportedObjectType() + " does not exist in ReportedObjectEnum.");
        }

        Report report = new Report();
        report.setAuthor(optionalAuthor.get());
        report.setReportedObjectType(ReportedObjectEnum.valueOf(reportRequest.getReportedObjectType()));
        report.setText(reportRequest.getText());

        if (report.getReportedObjectType().equals(ReportedObjectEnum.POST)) {
            Optional<Post> optionalPost = postRepository.findById(reportRequest.getReportedObjectId());
            if (!optionalPost.isPresent()) {
                return ResponseEntity.badRequest()
                        .body("Post of id " + reportRequest.getReportedObjectId() + " does not exist.");
            }
        } else {
            Optional<Reply> optionalReply = replyRepository.findById(reportRequest.getReportedObjectId());
            if (!optionalReply.isPresent()) {
                return ResponseEntity.badRequest()
                        .body("Reply of id " + reportRequest.getReportedObjectId() + " does not exist.");
            }
        }

        report.setReportedObjectId(reportRequest.getReportedObjectId());

        reportRepository.save(report);
        return ResponseEntity.ok().body(report.getReportedObjectType() + " reported successfuly.");
    }

    @Override
    public Iterable<Report> findAll() {
        return reportRepository.findAll();
    }

    @Override
    public Optional<Report> findById(Long id) {
        return reportRepository.findById(id);
    }
}
