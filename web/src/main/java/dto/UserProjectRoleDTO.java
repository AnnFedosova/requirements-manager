package dto;

import java.io.Serializable;

public class UserProjectRoleDTO implements Serializable {
    private long userId;
    private long projectId;
    private long projectRoleId;

    public UserProjectRoleDTO(){}

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
