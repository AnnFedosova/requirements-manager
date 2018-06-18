package dto;

public class SpecificationRequirementDTO {
    private long specificationId;
    private long requirementId;

    public SpecificationRequirementDTO(){}

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
