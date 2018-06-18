package dto;

public class ReleaseRequirementDTO {
    private long releaseId;
    private long requirementId;

    public ReleaseRequirementDTO(){}

    public long getReleaseId() {
        return releaseId;
    }

    public void setReleaseId(long releaseId) {
        this.releaseId = releaseId;
    }

    public long getRequirementId() {
        return requirementId;
    }

    public void setRequirementId(long requirementId) {
        this.requirementId = requirementId;
    }
}
