package dBService.dto;

import dBService.entities.RequirementPriorityEntity;

public class RequirementPriorityDTO {
    private long id;
    private String name;

    public RequirementPriorityDTO(){}

    public RequirementPriorityDTO(RequirementPriorityEntity requirementPriorityEntity) {
        this.id = requirementPriorityEntity.getId();
        this.name = requirementPriorityEntity.getName();
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
}
