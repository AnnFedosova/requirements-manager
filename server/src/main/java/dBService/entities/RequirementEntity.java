package dBService.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "requirements")
public class RequirementEntity implements Serializable {
    private static final long serialVersionUID = 10_05_2018L;

    @Id
    @Column(name = "id", unique = true, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private ProjectEntity project;

    @Column(name = "name")
    private String name;

    @Column(name = "description",columnDefinition = "text")
    private String description;

    @ManyToOne
    @JoinColumn(name = "requirement_priority_id")
    private RequirementPriorityEntity priority;

    @ManyToOne
    @JoinColumn(name = "requirement_type_id")
    private RequirementTypeEntity type;

    @ManyToOne
    @JoinColumn(name = "requirement_state_id")
    private RequirementStateEntity state;

    @Column(name = "creation_date")
    @Temporal(TemporalType.DATE)
    private Date creationDate;

    @ManyToOne
    @JoinColumn(name = "creator_id")
    private UserEntity creator;

    @Column(name = "modified_date")
    @Temporal(TemporalType.DATE)
    private Date modifiedDate;

    @ManyToOne
    @JoinColumn(name = "changer_id")
    private UserEntity changer;

    @ManyToOne
    @JoinColumn(name = "last_version_id")
    private RequirementEntity lastVersion;

    @Column(name = "relevance")
    private boolean relevance;

    @OneToMany(mappedBy = "requirement")
    private Set<SpecificationRequirementEntity> specificationRequirements;

    @OneToMany(mappedBy = "requirement")
    private Set<ReleaseRequirementEntity> releaseRequirements;

    public RequirementEntity(){}

    public RequirementEntity(ProjectEntity project, String name, String description, RequirementPriorityEntity priority, RequirementTypeEntity type, RequirementStateEntity state, Date creationDate, UserEntity creator, Date modifiedDate, UserEntity changer, RequirementEntity lastVersion, boolean relevance) {
        this.project = project;
        this.name = name;
        this.description = description;
        this.priority = priority;
        this.type = type;
        this.state = state;
        this.creationDate = creationDate;
        this.creator = creator;
        this.modifiedDate = modifiedDate;
        this.changer = changer;
        this.lastVersion = lastVersion;
        this.relevance = relevance;
    }
    public RequirementEntity(ProjectEntity project, String name, String description, RequirementPriorityEntity priority, RequirementTypeEntity type, RequirementStateEntity state, Date creationDate, UserEntity creator, boolean relevance) {
        this.project = project;
        this.name = name;
        this.description = description;
        this.priority = priority;
        this.type = type;
        this.state = state;
        this.creationDate = creationDate;
        this.creator = creator;
        this.relevance = relevance;
    }

    public RequirementEntity(RequirementEntity requirementEntity) {
        this.id = requirementEntity.id;
        this.project = requirementEntity.project;
        this.name = requirementEntity.name;
        this.description = requirementEntity.description;
        this.priority = requirementEntity.priority;
        this.type = requirementEntity.type;
        this.state = requirementEntity.state;
        this.creationDate = requirementEntity.creationDate;
        this.creator = requirementEntity.creator;
        this.modifiedDate = requirementEntity.modifiedDate;
        this.changer = requirementEntity.changer;
        this.lastVersion = requirementEntity.lastVersion;
        this.relevance = requirementEntity.relevance;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ProjectEntity getProject() {
        return project;
    }

    public void setProject(ProjectEntity project) {
        this.project = project;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public RequirementPriorityEntity getPriority() {
        return priority;
    }

    public void setPriority(RequirementPriorityEntity priority) {
        this.priority = priority;
    }

    public RequirementTypeEntity getType() {
        return type;
    }

    public void setType(RequirementTypeEntity type) {
        this.type = type;
    }

    public RequirementStateEntity getState() {
        return state;
    }

    public void setState(RequirementStateEntity state) {
        this.state = state;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public UserEntity getCreator() {
        return creator;
    }

    public void setCreator(UserEntity creator) {
        this.creator = creator;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public UserEntity getChanger() {
        return changer;
    }

    public void setChanger(UserEntity changer) {
        this.changer = changer;
    }

    public RequirementEntity getLastVersion() {
        return lastVersion;
    }

    public void setLastVersion(RequirementEntity lastVersion) {
        this.lastVersion = lastVersion;
    }

    public boolean isRelevance() {
        return relevance;
    }

    public void setRelevance(boolean relevance) {
        this.relevance = relevance;
    }
}
