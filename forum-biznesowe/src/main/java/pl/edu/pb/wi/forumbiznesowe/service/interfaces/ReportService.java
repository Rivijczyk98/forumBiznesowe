package pl.edu.pb.wi.forumbiznesowe.service.interfaces;

import org.springframework.http.ResponseEntity;
import pl.edu.pb.wi.forumbiznesowe.pojo.ReportRequest;

public interface ReportService {
    ResponseEntity<Object> createReport(ReportRequest reportRequest);
}
