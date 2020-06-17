package pl.edu.pb.wi.forumbiznesowe.dao.entity;

import lombok.Getter;
import lombok.Setter;
import pl.edu.pb.wi.forumbiznesowe.dao.entity.enums.ReportedObjectEnum;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Entity
@Table(name = "report")
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id", nullable = false)
    private User author;

    @Enumerated(EnumType.STRING)
    private ReportedObjectEnum reportedObjectName;

    @Column(nullable = false)
    private Long reportedObjectId;

    @NotBlank
    private String text;

    public Report() {
    }

}
