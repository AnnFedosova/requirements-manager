package dBService.dto;

import dBService.entities.RequirementPositionEntity;

public class PositionDTO {
    private long id;
    private String name;

    public PositionDTO(){}

    public PositionDTO(RequirementPositionEntity requirementPositionEntity) {
        this.id = requirementPositionEntity.getId();
        this.name = requirementPositionEntity.getName();
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
