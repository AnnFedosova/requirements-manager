package dBService.dto;

import dBService.entities.SpecificationEntity;
import java.util.Date;

public class SpecificationDTO {
    private long id;
    private String name;
    private String description;
    private String plannedDate;
    private long projectId;

    public SpecificationDTO(){}

    public SpecificationDTO(SpecificationEntity specificationEntity) {
        this.id = specificationEntity.getId();
        this.name = specificationEntity.getName();
        this.description = specificationEntity.getDescription();
        this.plannedDate = specificationEntity.getPlannedDate();
        this.projectId=specificationEntity.getProject().getId();
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPlannedDate() {
        return plannedDate;
    }

    public void setPlannedDate(String plannedDate) {
        this.plannedDate = plannedDate;
    }

    public long getProject() {
        return projectId;
    }

    public void setProject(long project) {
        this.projectId = project;
    }
}
