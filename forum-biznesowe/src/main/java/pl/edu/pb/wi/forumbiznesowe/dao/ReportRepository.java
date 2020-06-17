package pl.edu.pb.wi.forumbiznesowe.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.pb.wi.forumbiznesowe.dao.entity.Report;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {
}
