package pl.edu.pb.wi.forumbiznesowe.dao.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import pl.edu.pb.wi.forumbiznesowe.dao.entity.interfaces.Reportable;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "replies")
public class Reply implements Reportable {

    @Column(name = "rpl_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "use_id", nullable = false)
    private User author;

    @NotBlank
    private String text;

    @ManyToOne
    @JoinColumn(name = "pos_id", nullable = false)
    private Post post;

    @Column(nullable = false)
    @CreationTimestamp
    private Date postedDate;

    public Reply() {
    }

}
