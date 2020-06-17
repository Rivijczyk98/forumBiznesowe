package pl.edu.pb.wi.forumbiznesowe.dao.entity;

import pl.edu.pb.wi.forumbiznesowe.dao.entity.enums.PostStatusEnum;

import javax.persistence.*;

@Entity
@Table(name = "status")
public class Status {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private PostStatusEnum name;

    public Status() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PostStatusEnum getName() {
        return name;
    }

    public void setName(PostStatusEnum name) {
        this.name = name;
    }
}
