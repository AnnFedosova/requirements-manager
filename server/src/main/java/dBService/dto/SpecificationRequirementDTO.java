package dBService.dto;

import dBService.entities.SpecificationRequirementEntity;

public class SpecificationRequirementDTO {
    private long specificationId;
    private long requirementId;

    public SpecificationRequirementDTO(){}

    public SpecificationRequirementDTO(SpecificationRequirementEntity specificationRequirementEntity) {
        this.specificationId = specificationRequirementEntity.getSpecification().getId();
        this.requirementId = specificationRequirementEntity.getRequirement().getId();
    }

    public long getSpecificationId() {
        return specificationId;
    }

    public void setSpecificationId(long specificationId) {
        this.specificationId = specificationId;
    }

    public long getRequirementId() {
        return requirementId;
    }

    public void setRequirementId(long requirementId) {
        this.requirementId = requirementId;
    }
}
