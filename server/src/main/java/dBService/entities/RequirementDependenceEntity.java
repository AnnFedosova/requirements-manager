package dBService.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "requirement_dependence")
public class RequirementDependenceEntity implements Serializable {
    private static final long serialVersionUID = 10_05_2018L;

    @Id
    @ManyToOne
    @JoinColumn(name = "parent_requirement_id",nullable = false)
    private RequirementEntity parentRequirement;

    @Id
    @ManyToOne
    @JoinColumn(name = "child_requirement_id",nullable = false)
    private RequirementEntity childRequirement;

    @Id
    @ManyToOne
    @JoinColumn(name = "connection_type_id",nullable = false)
    private ConnectionTypeEntity connectionType;

    public RequirementDependenceEntity(){}

    public RequirementDependenceEntity(RequirementEntity parentRequirement, RequirementEntity childRequirement, ConnectionTypeEntity connectionType) {
        this.parentRequirement = parentRequirement;
        this.childRequirement = childRequirement;
        this.connectionType = connectionType;
    }

    public RequirementDependenceEntity(RequirementDependenceEntity requirementDependenceEntity) {
        this.parentRequirement = requirementDependenceEntity.getParentRequirement();
        this.childRequirement = requirementDependenceEntity.getChildRequirement();
        this.connectionType = requirementDependenceEntity.getConnectionType();
    }

    public RequirementEntity getParentRequirement() {
        return parentRequirement;
    }

    public void setParentRequirement(RequirementEntity parentRequirement) {
        this.parentRequirement = parentRequirement;
    }

    public RequirementEntity getChildRequirement() {
        return childRequirement;
    }

    public void setChildRequirement(RequirementEntity childRequirement) {
        this.childRequirement = childRequirement;
    }

    public ConnectionTypeEntity getConnectionType() {
        return connectionType;
    }

    public void setConnectionType(ConnectionTypeEntity connectionType) {
        this.connectionType = connectionType;
    }
}
