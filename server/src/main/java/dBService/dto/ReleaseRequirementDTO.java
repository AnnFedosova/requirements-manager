package dBService.dto;

import dBService.entities.ReleaseRequirementEntity;

public class ReleaseRequirementDTO {
    private long releaseId;
    private long requirementId;

    public ReleaseRequirementDTO(){}

    public ReleaseRequirementDTO(ReleaseRequirementEntity releaseRequirementEntity) {
        this.releaseId = releaseRequirementEntity.getRelease().getId();
        this.requirementId = releaseRequirementEntity.getRequirement().getId();
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
