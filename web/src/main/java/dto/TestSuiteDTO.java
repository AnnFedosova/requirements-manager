package dto;

import reportsgenerator.TestSuiteForReport;

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

    public TestSuiteDTO(TestSuiteForReport testSuiteForReport){
        this.id = testSuiteForReport.getId();
        this.name = testSuiteForReport.getName();
        this.description = testSuiteForReport.getDescription();
        this.plan = testSuiteForReport.getPlan();
        this.creatorId = testSuiteForReport.getCreatorId();
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

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPlan(String plan) {
        this.plan = plan;
    }

    public void setCreatorId(long creatorId) {
        this.creatorId = creatorId;
    }
}
