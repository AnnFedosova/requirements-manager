package dBService.dto;

import dBService.entities.RequirementStateEntity;

public class RequirementStateDTO {
    private long id;
    private String name;

    public RequirementStateDTO(){}

    public RequirementStateDTO(RequirementStateEntity requirementStateEntity) {
        this.id = requirementStateEntity.getId();
        this.name = requirementStateEntity.getName();
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
