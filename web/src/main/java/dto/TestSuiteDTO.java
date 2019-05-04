package dto;

public class TestSuiteDTO {
    private long id;
    private String name;
    private String description;
    private String plan;
    private long creatorId;

    TestSuiteDTO(){}

    public TestSuiteDTO(long id, String name, String description, String plan, long creatorId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.plan = plan;
        this.creatorId = creatorId;
    }

    public TestSuiteDTO(String id, String name, String description, String plan, String creatorId) {
        this.id = Long.parseLong(id);
        this.name = name;
        this.description = description;
        this.plan = plan;
        this.creatorId = Long.parseLong(creatorId);
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getPlan() {
        return plan;
    }

    public long getCreatorId() {
        return creatorId;
    }
}
