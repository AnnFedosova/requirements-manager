package dto;

public class TestSuiteTestPlanDTO {
    private long testSuiteId;
    private long testPlanId;

    public TestSuiteTestPlanDTO(){}

    public TestSuiteTestPlanDTO(long testSuiteId, long testPlanId) {
        this.testSuiteId = testSuiteId;
        this.testPlanId = testPlanId;
    }

    public TestSuiteTestPlanDTO(String testSuiteId, String testPlanId) {
        this.testSuiteId = Long.parseLong(testSuiteId);
        this.testPlanId = Long.parseLong(testPlanId);
    }

    public long getTestSuiteId() {
        return testSuiteId;
    }

    public void setTestSuiteId(long testSuiteId) {
        this.testSuiteId = testSuiteId;
    }

    public long getTestPlanId() {
        return testPlanId;
    }

    public void setTestPlanId(long testPlanId) {
        this.testPlanId = testPlanId;
    }
}
