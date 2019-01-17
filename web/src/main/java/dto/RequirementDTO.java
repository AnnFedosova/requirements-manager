package dto;

public class RequirementDTO {
    private long id;
    private long projectId;
    private String name;
    private String description;
    private long priorityId;
    private long typeId;
    private long stateId;
    private long creatorId;
    private String creationDate;
    private String modifiedDate;
    private long changerId;
    private long lastVersionId;
    private boolean relevance;

    //constructor with String field
    public RequirementDTO(
            String id,
            String projectId,
            String name,
            String description,
            String priorityId,
            String typeId,
            String stateId,
            String creatorId,
            String creationDate,
            String modifiedDate,
            String changerId,
            String lastVersionId,
            String relevance){

        this.id = Long.parseLong(id);
        this.projectId = Long.parseLong(projectId);
        this.name = name;
        this.description = description;
        this.priorityId = Long.parseLong(priorityId);
        this.typeId = Long.parseLong(typeId);
        this.stateId = Long.parseLong(stateId);
        this.creatorId = Long.parseLong(creatorId);
        this.creationDate = creationDate;
        this.modifiedDate = modifiedDate;
        this.changerId = Long.parseLong(changerId);
        this.lastVersionId = Long.parseLong(lastVersionId);
        this.relevance = Boolean.valueOf(relevance);
    }


    public RequirementDTO(long id, long projectId, String name, String description, long priorityId, long typeId, long stateId, String creationDate, long creatorId, String modifiedDate, long changerId, long lastVersionId, boolean relevance) {
        this.id = id;
        this.projectId = projectId;
        this.name = name;
        this.description = description;
        this.priorityId = priorityId;
        this.typeId = typeId;
        this.stateId = stateId;
        this.creationDate = creationDate;
        this.creatorId = creatorId;
        this.modifiedDate = modifiedDate;
        this.changerId = changerId;
        this.lastVersionId = lastVersionId;
        this.relevance = relevance;
    }

    //without some fields
    public RequirementDTO(long projectId, String name, String description, long priorityId, long typeId, long stateId, String creationDate,  String modifiedDate, long changerId, long lastVersionId, boolean relevance) {
        this.projectId = projectId;
        this.name = name;
        this.description = description;
        this.priorityId = priorityId;
        this.typeId = typeId;
        this.stateId = stateId;
        this.creationDate = creationDate;
        this.modifiedDate = modifiedDate;
        this.changerId = changerId;
        this.lastVersionId = lastVersionId;
        this.relevance = relevance;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public long getStateId() {
        return stateId;
    }

    public void setStateId(long stateId) {
        this.stateId = stateId;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(long creatorId) {
        this.creatorId = creatorId;
    }

    public String getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(String modifiedDate) {
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

    public String getRelevance() {
        return Boolean.toString(relevance);
    }


    public void setRelevance(boolean relevance) {
        this.relevance = relevance;
    }
}
