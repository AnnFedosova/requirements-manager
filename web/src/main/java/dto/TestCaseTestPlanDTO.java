package dto;

public class TestCaseTestPlanDTO {
    private long testCaseId;
    private long testPlanId;

    public TestCaseTestPlanDTO(){}

    public TestCaseTestPlanDTO(long testCaseId, long testPlanId) {
        this.testCaseId = testCaseId;
        this.testPlanId = testPlanId;
    }

    public TestCaseTestPlanDTO(String testCaseId, String testPlanId) {
        this.testCaseId = Long.parseLong(testCaseId);
        this.testPlanId = Long.parseLong(testPlanId);
    }

    public long getTestCaseId() {
        return testCaseId;
    }

    public void setTestCaseId(long testCaseId) {
        this.testCaseId = testCaseId;
    }

    public long getTestPlanId() {
        return testPlanId;
    }

    public void setTestPlanId(long testPlanId) {
        this.testPlanId = testPlanId;
    }
}
