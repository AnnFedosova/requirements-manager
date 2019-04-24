package dto;

public class TestPlanDTO {
    private long id;
    private String name;
    private String description;
    private String date_from;
    private String date_to;
    private String comment;
    private long test_environment_id;
    private long creatorId;

    public TestPlanDTO() {}

    public TestPlanDTO(long id, String name, String description, String date_from, String date_to, String comment, long test_environment_id, long creatorId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.date_from = date_from;
        this.date_to = date_to;
        this.comment = comment;
        this.test_environment_id = test_environment_id;
        this.creatorId = creatorId;
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

    public String getDate_from() {
        return date_from;
    }

    public String getDate_to() {
        return date_to;
    }

    public String getComment() {
        return comment;
    }

    public long getTest_environment_id() {
        return test_environment_id;
    }

    public long getCreatorId() {
        return creatorId;
    }
}
