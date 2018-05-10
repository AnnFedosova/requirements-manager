package dBService.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "specification_requirement")
public class SpecificationRequirementEntity implements Serializable {
    private static final long serialVersionUID = 10052018;

    @Id
    @ManyToOne
    @JoinColumn(name = "specification_id")
    private SpecificationEntity specification;

    @Id
    @ManyToOne
    @JoinColumn(name = "requirement_id")
    private RequirementEntity requirement;

    public SpecificationRequirementEntity(){}

    public SpecificationRequirementEntity(SpecificationEntity specification, RequirementEntity requirement) {
        this.specification = specification;
        this.requirement = requirement;
    }

    public SpecificationRequirementEntity(SpecificationRequirementEntity specificationRequirementEntety) {
        this.specification = specificationRequirementEntety.specification;
        this.requirement = specificationRequirementEntety.requirement;
    }

    public SpecificationEntity getSpecification() {
        return specification;
    }

    public void setSpecification(SpecificationEntity specification) {
        this.specification = specification;
    }

    public RequirementEntity getRequirement() {
        return requirement;
    }

    public void setRequirement(RequirementEntity requirement) {
        this.requirement = requirement;
    }
}