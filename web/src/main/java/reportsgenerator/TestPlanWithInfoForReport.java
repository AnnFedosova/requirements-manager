package reportsgenerator;

import dto.TestPlanDTO;

import java.util.List;

public class TestPlanWithInfoForReport {
    private TestPlanForReport testPlan;
    private List<TestCaseForReport> testCases;
    private List<TestSuiteForReport> testSuites;

    public TestPlanWithInfoForReport(){}

    public TestPlanWithInfoForReport(TestPlanDTO testPlan, List<TestCaseForReport> testCases, List<TestSuiteForReport> testSuites) {
        this.testPlan = new TestPlanForReport(testPlan);
        this.testCases = testCases;
        this.testSuites = testSuites;
    }

    public TestPlanDTO getTestPlan() {
        return testPlan;
    }

    public void setTestPlan(TestPlanForReport testPlan) {
        this.testPlan = testPlan;
    }

    public List<TestCaseForReport> getTestCases() {
        return testCases;
    }

    public void setTestCases(List<TestCaseForReport> testCases) {
        this.testCases = testCases;
    }

    public List<TestSuiteForReport> getTestSuites() {
        return testSuites;
    }

    public void setTestSuites(List<TestSuiteForReport> testSuites) {
        this.testSuites = testSuites;
    }
}
