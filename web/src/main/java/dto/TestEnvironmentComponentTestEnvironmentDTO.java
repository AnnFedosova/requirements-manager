package dto;

public class TestEnvironmentComponentTestEnvironmentDTO {
    private long testEnvironmentComponentId;
    private long testEnvironmentId;

    TestEnvironmentComponentTestEnvironmentDTO(){}

    public TestEnvironmentComponentTestEnvironmentDTO(long testEnvironmentComponentId, long testEnvironmentId) {
        this.testEnvironmentComponentId = testEnvironmentComponentId;
        this.testEnvironmentId = testEnvironmentId;
    }

    public TestEnvironmentComponentTestEnvironmentDTO(String testEnvironmentComponentId, String testEnvironmentId) {
        this.testEnvironmentComponentId = Long.parseLong(testEnvironmentComponentId);
        this.testEnvironmentId = Long.parseLong(testEnvironmentId);
    }

    public long getTestEnvironmentComponentId() {
        return testEnvironmentComponentId;
    }

    public void setTestEnvironmentComponentId(long testEnvironmentComponentId) {
        this.testEnvironmentComponentId = testEnvironmentComponentId;
    }

    public long getTestEnvironmentId() {
        return testEnvironmentId;
    }

    public void setTestEnvironmentId(long testEnvironmentId) {
        this.testEnvironmentId = testEnvironmentId;
    }
}
