package reportsgenerator;

import dto.TestCaseDTO;
import dto.TestSuiteDTO;

import java.util.ArrayList;
import java.util.List;

public class TestSuiteWithInfoForReport {
    private TestSuiteForReport testSuite;
    private List<TestCaseForReport> testCases;

    public TestSuiteWithInfoForReport(){}


    public TestSuiteWithInfoForReport(TestSuiteForReport testSuite, List<TestCaseForReport> testCases) {
        this.testSuite = testSuite;
        this.testCases = testCases;
    }

    public TestSuiteWithInfoForReport(TestSuiteDTO testSuite, List<TestCaseDTO> testCases, boolean isTestCasesDTO) {
        if (!isTestCasesDTO) return;
        this.testSuite = new TestSuiteForReport(testSuite);
        List<TestCaseForReport> testCasesForReport = new ArrayList<TestCaseForReport>();
        for(TestCaseDTO testCase: testCases){
            testCasesForReport.add(new TestCaseForReport(testCase));
        }
        this.testCases = testCasesForReport;
    }

    public TestSuiteForReport getTestSuite() {
        return testSuite;
    }

    public void setTestSuite(TestSuiteForReport testSuite) {
        this.testSuite = testSuite;
    }

    public List<TestCaseForReport> getTestCases() {
        return testCases;
    }

    public void setTestCases(List<TestCaseForReport> testCases) {
        this.testCases = testCases;
    }


}
