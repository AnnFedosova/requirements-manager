package dBService.dto;

import dBService.entities.RequirementEntity;

import java.util.Date;

public class RequirementDTO {
    private long id;
    private long projectId;
    private String name;
    private String discription;
    private long priorityId;
    private long typeId;
    private long positionId;
    private Date creationDate;
    private long creatorId;
    private Date modifiedDate;
    private long changerId;
    private long lastVersionId;
    private boolean relevance;

    public RequirementDTO(){}

    public RequirementDTO(RequirementEntity requirementEntity) {
        this.id = requirementEntity.getId();
        this.projectId = requirementEntity.getProject().getId();
        this.name = requirementEntity.getName();
        this.discription = requirementEntity.getDescription();
        this.priorityId = requirementEntity.getPriority().getId();
        this.typeId = requirementEntity.getType().getId();
        this.positionId = requirementEntity.getPosition().getId();
        this.creationDate = requirementEntity.getCreationDate();
        this.creatorId = requirementEntity.getCreator().getId();
        this.modifiedDate = requirementEntity.getModifiedDate();
        this.changerId = requirementEntity.getChanger().getId();
        this.lastVersionId = requirementEntity.getLastVersion().getId();
        this.relevance = requirementEntity.isRelevance();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getProjectId() {
        return projectId;
    }

    public void setProjectId(long projectId) {
        this.projectId = projectId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public long getPriorityId() {
        return priorityId;
    }

    public void setPriorityId(long priorityId) {
        this.priorityId = priorityId;
    }

    public long getTypeId() {
        return typeId;
    }

    public void setTypeId(long typeId) {
        this.typeId = typeId;
    }

    public long getPositionId() {
        return positionId;
    }

    public void setPositionId(long positionId) {
        this.positionId = positionId;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(long creatorId) {
        this.creatorId = creatorId;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public long getChangerId() {
        return changerId;
    }

    public void setChangerId(long changerId) {
        this.changerId = changerId;
    }

    public long getLastVersionId() {
        return lastVersionId;
    }

    public void setLastVersionId(long lastVersionId) {
        this.lastVersionId = lastVersionId;
    }

    public boolean isRelevance() {
        return relevance;
    }

    public void setRelevance(boolean relevance) {
        this.relevance = relevance;
    }
}
