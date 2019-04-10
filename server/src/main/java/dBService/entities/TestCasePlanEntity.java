package dBService.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Test_Case_Plan")
public class TestCasePlanEntity implements Serializable {
    private static final long serialVersionUID = 10_03_2019L;

    @Id
    @ManyToOne
    @JoinColumn(name = "test_case_id")
    private TestCaseEntity testCase;

    @Id
    @ManyToOne
    @JoinColumn(name = "test_plan_id")
    private TestPlanEntity testPlan;

    public TestCasePlanEntity(){}

    public TestCasePlanEntity(TestCaseEntity testCase, TestPlanEntity testPlan) {
        this.testCase = testCase;
        this.testPlan = testPlan;
    }

    public TestCasePlanEntity(TestCasePlanEntity testCasePlanEntity){
        this.testCase=testCasePlanEntity.testCase;
        this.testPlan=testCasePlanEntity.testPlan;
    }

    public TestCaseEntity getTestCase() {
        return testCase;
    }

    public void setTestCase(TestCaseEntity testCase) {
        this.testCase = testCase;
    }

    public TestPlanEntity getTestPlan() {
        return testPlan;
    }

    public void setTestPlan(TestPlanEntity testPlan) {
        this.testPlan = testPlan;
    }
}
