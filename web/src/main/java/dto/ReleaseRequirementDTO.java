package dto;

public class ReleaseRequirementDTO {
    private long releaseId;
    private long requirementId;

    public ReleaseRequirementDTO(){}

    public ReleaseRequirementDTO(String releaseId, String requirementId) {
        this.releaseId = Long.parseLong(releaseId);
        this.requirementId = Long.parseLong(requirementId);
    }

    public ReleaseRequirementDTO(long releaseId, long requirementId) {
        this.releaseId = releaseId;
        this.requirementId = requirementId;
    }

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
