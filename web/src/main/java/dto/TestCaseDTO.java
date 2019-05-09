package dto;

public class TestCaseDTO {
    private long id;
    private long requirementId;
    private String name;
    private String creationDate;
    private String plan;
    private String startConditions;
    private String endConditions;
    private String data;
    private long creatorId;

    public TestCaseDTO(){}

    public TestCaseDTO(long id, long requirementId, String name, String creationDate, String plan, String startConditions, String endConditions, String data, long creatorId) {
        this.id = id;
        this.requirementId = requirementId;
        this.name = name;
        this.creationDate = creationDate;
        this.plan = plan;
        this.startConditions = startConditions;
        this.endConditions = endConditions;
        this.data = data;
        this.creatorId = creatorId;
    }

    public TestCaseDTO(String id, String requirementId, String name, String creationDate, String plan, String startConditions, String endConditions, String data, String creatorId) {
        this.id = Long.parseLong(id);
        this.requirementId = Long.parseLong(requirementId);
        this.name = name;
        this.creationDate = creationDate;
        this.plan = plan;
        this.startConditions = startConditions;
        this.endConditions = endConditions;
        this.data = data;
        this.creatorId = Long.parseLong(creatorId);
    }

    public long getId() {
        return id;
    }

    public long getRequirementId() {
        return requirementId;
    }

    public String getName() {
        return name;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public String getPlan() {
        return plan;
    }

    public String getStartConditions() {
        return startConditions;
    }

    public String getEndConditions() {
        return endConditions;
    }

    public String getData() {
        return data;
    }

    public long getCreatorId() {
        return creatorId;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setRequirementId(long requirementId) {
        this.requirementId = requirementId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public void setPlan(String plan) {
        this.plan = plan;
    }

    public void setStartConditions(String startConditions) {
        this.startConditions = startConditions;
    }

    public void setEndConditions(String endConditions) {
        this.endConditions = endConditions;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void setCreatorId(long creatorId) {
        this.creatorId = creatorId;
    }
}
