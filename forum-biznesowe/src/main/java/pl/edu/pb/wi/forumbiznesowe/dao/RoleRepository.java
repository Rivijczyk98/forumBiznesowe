package pl.edu.pb.wi.forumbiznesowe.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.pb.wi.forumbiznesowe.dao.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

}
