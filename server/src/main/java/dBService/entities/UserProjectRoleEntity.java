package dBService.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "user_project_role")
public class UserProjectRoleEntity implements Serializable {
    private static final long serialVersionUID = 10_05_2018L;

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
    @JoinColumn(name = "project_role_id")
    private ProjectRoleEntity projectRole;

    public UserProjectRoleEntity(){}

    public UserProjectRoleEntity(UserEntity user, ProjectEntity project, ProjectRoleEntity projectRole) {
        this.user = user;
        this.project = project;
        this.projectRole = projectRole;
    }

    public UserProjectRoleEntity(UserProjectRoleEntity userProjectRoleEntity) {
        this.user = userProjectRoleEntity.user;
        this.project = userProjectRoleEntity.project;
        this.projectRole = userProjectRoleEntity.projectRole;
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

    public ProjectRoleEntity getProjectRole() {
        return projectRole;
    }

    public void setProjectRole(ProjectRoleEntity projectRole) {
        this.projectRole = projectRole;
    }
}
