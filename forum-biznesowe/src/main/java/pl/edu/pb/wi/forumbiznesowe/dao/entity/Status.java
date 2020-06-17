package pl.edu.pb.wi.forumbiznesowe.dao.entity;

import lombok.Getter;
import lombok.Setter;
import pl.edu.pb.wi.forumbiznesowe.dao.entity.enums.PostStatusEnum;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "statuses")
public class Status {

    @Column(name = "sta_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private PostStatusEnum name;

    public Status() {
    }

}
