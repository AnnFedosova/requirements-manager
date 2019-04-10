package dBService.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Test_Suite_Plan")
public class TestSuitePlanEntity implements Serializable {
    private static final long serialVersionUID = 10_03_2019L;

    @Id
    @ManyToOne
    @JoinColumn(name = "test_suite_id")
    private TestSuiteEntity testSuite;

    @Id
    @ManyToOne
    @JoinColumn(name = "test_plan_id")
    private TestPlanEntity testPlan;

    public TestSuitePlanEntity(){}

    public TestSuitePlanEntity(TestSuiteEntity testSuite, TestPlanEntity testPlan) {
        this.testSuite = testSuite;
        this.testPlan = testPlan;
    }

    public TestSuitePlanEntity(TestSuitePlanEntity testSuitePlanEntity){
        this.testPlan=testSuitePlanEntity.testPlan;
        this.testSuite=testSuitePlanEntity.testSuite;
    }

    public TestSuiteEntity getTestSuite() {
        return testSuite;
    }

    public void setTestSuite(TestSuiteEntity testSuite) {
        this.testSuite = testSuite;
    }

    public TestPlanEntity getTestPlan() {
        return testPlan;
    }

    public void setTestPlan(TestPlanEntity testPlan) {
        this.testPlan = testPlan;
    }
}
