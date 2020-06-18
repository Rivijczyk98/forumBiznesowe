package pl.edu.pb.wi.forumbiznesowe.dao.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import pl.edu.pb.wi.forumbiznesowe.dao.entity.enums.PostStatusEnum;
import pl.edu.pb.wi.forumbiznesowe.dao.entity.interfaces.Reportable;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "posts")
public class Post implements Reportable {

    @Column(name = "pos_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "use_id", nullable = false)
    private User author;

    @ManyToOne
    @JoinColumn(name = "cat_id", nullable = false)
    private Category category;

    @NotBlank
    private String title;

    @NotBlank
    private String text;

    private boolean isObserved;

//    @ManyToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "sta_id", nullable = false)
//    private Status status;

    @Enumerated(EnumType.STRING)
    private PostStatusEnum status;

    @Column(nullable = false)
    @CreationTimestamp
    private Date postedDate;

    public Post(User author, Category category, String title, String text, PostStatusEnum status, Date postedDate) {
        this.author = author;
        this.category = category;
        this.title = title;
        this.text = text;
        this.status = status;
        this.postedDate = postedDate;
    }

    public boolean getIsObserved(){
        return isObserved;
    }
}
