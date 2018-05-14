package dBService.dto;

import dBService.entities.SpecificationEntity;
import java.util.Date;

public class SpecificationDTO {
    private long id;
    private String name;
    private String discription;
    private Date plannedDate;

    public SpecificationDTO(){}

    public SpecificationDTO(SpecificationEntity specificationEntity) {
        this.id = specificationEntity.getId();
        this.name = specificationEntity.getName();
        this.discription = specificationEntity.getDescription();
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

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public Date getPlannedDate() {
        return plannedDate;
    }

    public void setPlannedDate(Date plannedDate) {
        this.plannedDate = plannedDate;
    }
}
