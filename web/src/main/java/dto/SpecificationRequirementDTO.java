package dto;

public class SpecificationRequirementDTO {
    private long specificationId;
    private long requirementId;

    public SpecificationRequirementDTO(){}

    public SpecificationRequirementDTO(String specificationId, String requirementId) {
        this.specificationId = Long.parseLong(specificationId);
        this.requirementId = Long.parseLong(requirementId);
    }

    public SpecificationRequirementDTO(long specificationId, long requirementId) {
        this.specificationId = specificationId;
        this.requirementId = requirementId;
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
