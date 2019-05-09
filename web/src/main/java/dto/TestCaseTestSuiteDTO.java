package dto;

public class TestCaseTestSuiteDTO {
    private long testCaseId;
    private long testSuiteId;

    TestCaseTestSuiteDTO(){}

    public TestCaseTestSuiteDTO(long testCaseId, long testSuiteId) {
        this.testCaseId = testCaseId;
        this.testSuiteId = testSuiteId;
    }

    public TestCaseTestSuiteDTO(String testCaseId, String testSuiteId) {
        this.testCaseId = Long.parseLong(testCaseId);
        this.testSuiteId = Long.parseLong(testSuiteId);
    }

    public long getTestCaseId() {
        return testCaseId;
    }

    public void setTestCaseId(long testCaseId) {
        this.testCaseId = testCaseId;
    }

    public long getTestSuiteId() {
        return testSuiteId;
    }

    public void setTestSuiteId(long testSuiteId) {
        this.testSuiteId = testSuiteId;
    }
}
