package pl.edu.pb.wi.forumbiznesowe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.pb.wi.forumbiznesowe.pojo.ReportRequest;
import pl.edu.pb.wi.forumbiznesowe.service.interfaces.ReportService;

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
}
