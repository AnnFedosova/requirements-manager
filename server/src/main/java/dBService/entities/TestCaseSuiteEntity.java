package dBService.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Test_Case_Suite")
public class TestCaseSuiteEntity implements Serializable {
    private static final long serialVersionUID = 10_03_2019L;

    @Id
    @ManyToOne
    @JoinColumn(name = "test_case_id")
    private TestCaseEntity testCase;

    @Id
    @ManyToOne
    @JoinColumn(name = "test_suite_id")
    private TestSuiteEntity testSuite;

    public TestCaseSuiteEntity(){}

    public TestCaseSuiteEntity(TestCaseEntity testCase, TestSuiteEntity testSuite) {
        this.testCase = testCase;
        this.testSuite = testSuite;
    }

    public TestCaseSuiteEntity(TestCaseSuiteEntity testCaseSuiteEntity){
        this.testCase=testCaseSuiteEntity.testCase;
        this.testSuite=testCaseSuiteEntity.testSuite;
    }

    public TestCaseEntity getTestCase() {
        return testCase;
    }

    public void setTestCase(TestCaseEntity testCase) {
        this.testCase = testCase;
    }

    public TestSuiteEntity getTestSuite() {
        return testSuite;
    }

    public void setTestSuite(TestSuiteEntity testSuite) {
        this.testSuite = testSuite;
    }
}
