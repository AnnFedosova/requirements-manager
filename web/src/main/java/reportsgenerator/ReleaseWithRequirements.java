package reportsgenerator;

import dto.ReleaseDTO;

import java.util.List;

public class ReleaseWithRequirements {
    ReleaseDTO release;
    List<RequirementForReport> requirements;

    ReleaseWithRequirements(){}

    public ReleaseWithRequirements(ReleaseDTO release, List<RequirementForReport> requirements){
        this.release = release;
        this.requirements = requirements;
    }

    public List<RequirementForReport> getRequirements() {
        return requirements;
    }

    public ReleaseDTO getRelease() {
        return release;
    }
}
