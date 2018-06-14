package dBService.dto;

import dBService.entities.RequirementTypeEntity;

public class RequirementTypeDTO {
    private long id;
    private String name;

    public RequirementTypeDTO(){}

    public RequirementTypeDTO(RequirementTypeEntity requirementTypeEntity) {
        this.id = requirementTypeEntity.getId();
        this.name = requirementTypeEntity.getName();
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
