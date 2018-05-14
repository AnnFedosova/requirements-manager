package dBService.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "release_requirement")
public class ReleaseRequirementEntity implements Serializable {
    private static final long serialVersionUID = 10_05_2018L;

    @Id
    @ManyToOne
    @JoinColumn(name = "release_id")
    private ReleaseEntity release;

    @Id
    @ManyToOne
    @JoinColumn(name = "requirement_id")
    private RequirementEntity requirement;

    public ReleaseRequirementEntity(){}

    public ReleaseRequirementEntity(ReleaseEntity release, RequirementEntity requirement) {
        this.release = release;
        this.requirement = requirement;
    }

    public ReleaseRequirementEntity(ReleaseRequirementEntity releaseRequirementEntety) {
        this.release = releaseRequirementEntety.release;
        this.requirement = releaseRequirementEntety.requirement;
    }

    public ReleaseEntity getRelease() {
        return release;
    }

    public void setRelease(ReleaseEntity release) {
        this.release = release;
    }

    public RequirementEntity getRequirement() {
        return requirement;
    }

    public void setRequirement(RequirementEntity requirement) {
        this.requirement = requirement;
    }
}
