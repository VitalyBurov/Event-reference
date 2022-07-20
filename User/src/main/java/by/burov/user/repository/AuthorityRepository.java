package by.burov.user.repository;

import by.burov.user.core.enums.UserRole;
import by.burov.user.repository.entity.Authority;
import org.springframework.data.jpa.repository.JpaRepository;;
import org.springframework.stereotype.Repository;

import java.util.function.Function;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority,Long> {

    Authority findByRoleName(UserRole role);

}
