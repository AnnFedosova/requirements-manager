package reportsgenerator;

import dto.SpecificationDTO;

import java.util.List;

public class SpecificationWithRequirements {
    SpecificationDTO specification;
    List<RequirementForReport> requirements;

    public SpecificationWithRequirements(){}

    public SpecificationWithRequirements(SpecificationDTO specification, List<RequirementForReport> requirements){
        this.specification = specification;
        this.requirements = requirements;
    }

    public List<RequirementForReport> getRequirements() {
        return requirements;
    }

    public SpecificationDTO getSpecification() {
        return specification;
    }
}
