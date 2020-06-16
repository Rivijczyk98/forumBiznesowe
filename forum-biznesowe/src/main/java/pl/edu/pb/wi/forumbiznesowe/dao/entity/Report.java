package pl.edu.pb.wi.forumbiznesowe.dao.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Entity
@Table(name = "report")
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @ManyToMany
    private Set<User> author;

    private String className;
    private Long reportedObjectId;

    @NotBlank
    private String text;

    public Report() {
    }

    public Report(Long id, @NotBlank Set<User> author, String className, Long reportedObjectId, @NotBlank String text) {
        this.id = id;
        this.author = author;
        this.className = className;
        this.reportedObjectId = reportedObjectId;
        this.text = text;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<User> getAuthor() {
        return author;
    }

    public void setAuthor(Set<User> author) {
        this.author = author;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Long getReportedObjectId() {
        return reportedObjectId;
    }

    public void setReportedObjectId(Long reportedObjectId) {
        this.reportedObjectId = reportedObjectId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
