package dBService.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "user_project_role")
public class UserProjectRoleEntity implements Serializable {
    private static final long serialVersionUID = 10052018;

    @Id
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @Id
    @ManyToOne
    @JoinColumn(name = "project_id")
    private ProjectEntity project;

    @Id
    @ManyToOne
    @JoinColumn(name = "role_id")
    private ProjectRoleEntity role;

    public UserProjectRoleEntity(){}

    public UserProjectRoleEntity(UserEntity user, ProjectEntity project, ProjectRoleEntity role) {
        this.user = user;
        this.project = project;
        this.role = role;
    }

    public UserProjectRoleEntity(UserProjectRoleEntity userProjectRoleEntety) {
        this.user = userProjectRoleEntety.user;
        this.project = userProjectRoleEntety.project;
        this.role = userProjectRoleEntety.role;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public ProjectEntity getProject() {
        return project;
    }

    public void setProject(ProjectEntity project) {
        this.project = project;
    }

    public ProjectRoleEntity getRole() {
        return role;
    }

    public void setRole(ProjectRoleEntity role) {
        this.role = role;
    }
}
