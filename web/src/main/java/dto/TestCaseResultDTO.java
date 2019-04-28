package dto;

public class TestCaseResultDTO {
    private long id;
    private String date;
    private String description;
    private long testCaseId;
    private long testerId;

    TestCaseResultDTO(){}

    public TestCaseResultDTO(long id, String date, String description, long testCaseId, long testerId) {
        this.id = id;
        this.date = date;
        this.description = description;
        this.testCaseId = testCaseId;
        this.testerId = testerId;
    }

    public TestCaseResultDTO(String id, String date, String description, String testCaseId, String testerId) {
        this.id = Long.parseLong(id);
        this.date = date;
        this.description = description;
        this.testCaseId = Long.parseLong(testCaseId);
        this.testerId = Long.parseLong(testerId);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getTestCaseId() {
        return testCaseId;
    }

    public void setTestCaseId(long testCaseId) {
        this.testCaseId = testCaseId;
    }

    public long getTesterId() {
        return testerId;
    }

    public void setTesterId(long testerId) {
        this.testerId = testerId;
    }
}
