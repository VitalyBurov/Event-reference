package by.burov.user.repository.entity;

import by.burov.user.core.enums.UserRole;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "authorities", schema = "spring")
public class Authority implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String roleName;

    @ManyToMany(mappedBy = "authorities")
    private Set<User> users;
    public Authority() {
    }

    public Authority(Long id) {
        this.id = id;
    }

    public Authority(Long id, String role) {
        this.id = id;
        this.roleName = role;
    }

    public Authority(String roleName) {
        this.roleName = roleName.substring(5);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    @Override
    public String getAuthority() {
        return getRoleName();
    }
}