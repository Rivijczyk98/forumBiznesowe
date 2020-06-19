package pl.edu.pb.wi.forumbiznesowe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.pb.wi.forumbiznesowe.dao.entity.Post;
import pl.edu.pb.wi.forumbiznesowe.dao.entity.Report;
import pl.edu.pb.wi.forumbiznesowe.pojo.ReportRequest;
import pl.edu.pb.wi.forumbiznesowe.service.interfaces.ReportService;

import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/reports")
public class ReportController {

    private final ReportService reportService;

    @Autowired
    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @PostMapping
    public ResponseEntity<Object> reportObject(@RequestBody ReportRequest reportRequest) {
        return reportService.createReport(reportRequest);
    }

    @GetMapping("/all")
    public Iterable<Report> getAll(){
        return reportService.findAll();
    }

    @GetMapping("/report")
    public Optional<Report> getReportByID(@RequestParam Long id) {
        return reportService.findById(id);
    }
}
