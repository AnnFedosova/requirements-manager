package dBService.dto;

import dBService.entities.ProjectEntity;
import dBService.entities.ProjectRoleEntity;
import dBService.entities.UserEntity;
import dBService.entities.UserProjectRoleEntity;

public class UserProjectRoleDTO {

    private long userId;
    private long projectId;
    private long projectRoleId;

    public UserProjectRoleDTO(){}

    public UserProjectRoleDTO(UserEntity userEntity, ProjectEntity projectEntity, ProjectRoleEntity projectRoleEntity) {
        this.userId = userEntity.getId();
        this.projectId = projectEntity.getId();
        this.projectRoleId = projectRoleEntity.getId();
    }

    public UserProjectRoleDTO(UserProjectRoleEntity userProjectRoleEntity) {
        this.userId = userProjectRoleEntity.getUser().getId();
        this.projectId = userProjectRoleEntity.getProject().getId();
        this.projectRoleId = userProjectRoleEntity.getProjectRole().getId();
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getProjectId() {
        return projectId;
    }

    public void setProjectId(long projectId) {
        this.projectId = projectId;
    }

    public long getProjectRoleId() {
        return projectRoleId;
    }

    public void setProjectRoleId(long projectRoleId) {
        this.projectRoleId = projectRoleId;
    }
}
