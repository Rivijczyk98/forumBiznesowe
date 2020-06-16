package pl.edu.pb.wi.forumbiznesowe.dao.entity;

import pl.edu.pb.wi.forumbiznesowe.dao.entity.enums.PostStatusEnum;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_posts",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "post_id"))
    private Set<User> author;

    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Category> category;

    @NotBlank
    private String title;
    @NotBlank
    private String text;
    private PostStatusEnum status;
    private Date postedDate;

    public Post() {
    }

    public Post(Long id, Set<User> author, String title, String text, PostStatusEnum status) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.text = text;
        this.status = status;
    }

    public Date getPostedDate() {
        return postedDate;
    }

    public void setPostedDate(Date postedDate) {
        this.postedDate = postedDate;
    }

    public PostStatusEnum getStatus() {
        return status;
    }

    public void setStatus(PostStatusEnum status) {
        this.status = status;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Category getCategory() {
        for(Category c : category){
            return c;
        }
        return null;
    }

    public void setCategory(Set<Category> category) {
        this.category = category;
    }
}
