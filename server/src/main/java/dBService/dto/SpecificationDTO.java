package dBService.dto;

import dBService.entities.SpecificationEntity;
import java.util.Date;

public class SpecificationDTO {
    private long id;
    private String name;
    private String description;
    private Date plannedDate;

    public SpecificationDTO(){}

    public SpecificationDTO(SpecificationEntity specificationEntity) {
        this.id = specificationEntity.getId();
        this.name = specificationEntity.getName();
        this.description = specificationEntity.getDescription();
        this.plannedDate = specificationEntity.getPlannedDate();
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

    public Date getPlannedDate() {
        return plannedDate;
    }

    public void setPlannedDate(Date plannedDate) {
        this.plannedDate = plannedDate;
    }
}
