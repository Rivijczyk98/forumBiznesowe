package pl.edu.pb.wi.forumbiznesowe.dao.entity;

import lombok.Getter;
import lombok.Setter;
import pl.edu.pb.wi.forumbiznesowe.dao.entity.enums.RoleEnum;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "roles")
public class Role {

    @Column(name = "rol_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private RoleEnum name;

    public Role(RoleEnum roleEnum) {
        this.name = roleEnum;
    }

    public Role() {
    }
}
