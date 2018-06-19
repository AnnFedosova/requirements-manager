package dBService.dto;

import dBService.entities.SpecificationEntity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SpecificationStringDTO {
    private long id;
    private String name;
    private String description;
    private String plannedDate;

    public SpecificationStringDTO(){}

    public SpecificationStringDTO(SpecificationDTO specificationDTO) {
        SimpleDateFormat sdfr = new SimpleDateFormat("dd-MMM-yyyy");
        this.id = specificationDTO.getId();
        this.name = specificationDTO.getName();
        this.description = specificationDTO.getDescription();
        this.plannedDate = sdfr.format(specificationDTO.getPlannedDate());
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
}
