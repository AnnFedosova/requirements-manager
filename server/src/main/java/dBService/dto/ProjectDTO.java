package dBService.dto;

import dBService.entities.ProjectEntity;

public class ProjectDTO {
    private long id;
    private String name;
    private String discription;

    public ProjectDTO(){}

    public ProjectDTO(ProjectEntity projectEntity) {
        this.id = projectEntity.getId();
        this.name = projectEntity.getName();
        this.discription = projectEntity.getDescription();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }
}
