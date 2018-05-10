package dBService.dto;

import dBService.entities.RequirementTypeEntity;

public class TypeDTO {
    private long id;
    private String name;

    public TypeDTO(){}

    public TypeDTO(RequirementTypeEntity requirementTypeEntity) {
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
