package pl.edu.pb.wi.forumbiznesowe.dao.entity;

import lombok.Getter;
import lombok.Setter;
import pl.edu.pb.wi.forumbiznesowe.dao.entity.enums.ReportedObjectEnum;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Entity
@Table(name = "reports")
public class Report {

    @Column(name = "rpo_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "use_id", nullable = false)
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
