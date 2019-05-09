package dto;

public class TestEnvironmentTestPlanDTO {
    private long testEnvironmentId;
    private long testPlanId;

    TestEnvironmentTestPlanDTO(){}

    public TestEnvironmentTestPlanDTO(long testEnvironmentId, long testPlanId) {
        this.testEnvironmentId = testEnvironmentId;
        this.testPlanId = testPlanId;
    }

    public TestEnvironmentTestPlanDTO(String testEnvironmentId, String testPlanId) {
        this.testEnvironmentId = Long.parseLong(testEnvironmentId);
        this.testPlanId = Long.parseLong(testPlanId);
    }

    public long getTestEnvironmentId() {
        return testEnvironmentId;
    }

    public void setTestEnvironmentId(long testEnvironmentId) {
        this.testEnvironmentId = testEnvironmentId;
    }

    public long getTestPlanId() {
        return testPlanId;
    }

    public void setTestPlanId(long testPlanId) {
        this.testPlanId = testPlanId;
    }
}
