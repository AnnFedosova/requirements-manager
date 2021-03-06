package dBService.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "user_system_role")
public class UserSystemRoleEntity implements Serializable {
    private static final long serialVersionUID = 10_05_2018L;

    @Id
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @Id
    @ManyToOne
    @JoinColumn(name = "role_id")
    private SystemRoleEntity role;

    public UserSystemRoleEntity(){}

    public UserSystemRoleEntity(UserEntity user, SystemRoleEntity role) {
        this.user = user;
        this.role = role;

    }

    public UserSystemRoleEntity(UserSystemRoleEntity userSystemRoleEntity) {
        this.user = userSystemRoleEntity.user;
        this.role = userSystemRoleEntity.role;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public SystemRoleEntity getRole() {
        return role;
    }

    public void setRole(SystemRoleEntity role) {
        this.role = role;
    }
}
