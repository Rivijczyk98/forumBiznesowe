package pl.edu.pb.wi.forumbiznesowe.dao.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import pl.edu.pb.wi.forumbiznesowe.dao.entity.enums.PostStatusEnum;
import pl.edu.pb.wi.forumbiznesowe.dao.entity.interfaces.Reportable;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Getter
@Setter
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

    public Post() {
    }

    public Post(User author, Category category, String title, String text, PostStatusEnum status){
        this.author = author;
        this.category = category;
        this.title = title;
        this.text = text;
        this.status = status;
        this.postedDate = new Date();
        this.isObserved = true;
    }

    public boolean getIsObserved(){
        return isObserved;
    }
}
