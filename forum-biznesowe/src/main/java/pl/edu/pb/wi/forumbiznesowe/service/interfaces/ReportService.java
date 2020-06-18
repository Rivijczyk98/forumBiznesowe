package pl.edu.pb.wi.forumbiznesowe.service.interfaces;

import org.springframework.http.ResponseEntity;
import pl.edu.pb.wi.forumbiznesowe.dao.entity.Report;
import pl.edu.pb.wi.forumbiznesowe.pojo.ReportRequest;

import java.util.Optional;

public interface ReportService {
    Iterable<Report> findAll();
    ResponseEntity<Object> createReport(ReportRequest reportRequest);
    Optional<Report> findById(Long id);
}
