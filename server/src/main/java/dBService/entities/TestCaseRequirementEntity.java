package dBService.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Test_Case_Requirement")
public class TestCaseRequirementEntity implements Serializable {
    private static final long serialVersionUID = 10_03_2019L;

    @Id
    @ManyToOne
    @JoinColumn(name = "test_case_id")
    private TestCaseEntity testCase;

    @Id
    @ManyToOne
    @JoinColumn(name = "requirement_id")
    private RequirementEntity requirement;

    public TestCaseRequirementEntity(){}

    public TestCaseRequirementEntity(TestCaseEntity testCase, RequirementEntity requirement) {
        this.testCase = testCase;
        this.requirement = requirement;
    }

    public TestCaseRequirementEntity(TestCaseRequirementEntity testCaseRequirementEntity){
        this.requirement=testCaseRequirementEntity.requirement;
        this.testCase=testCaseRequirementEntity.testCase;
    }

    public TestCaseEntity getTestCase() {
        return testCase;
    }

    public void setTestCase(TestCaseEntity testCase) {
        this.testCase = testCase;
    }

    public RequirementEntity getRequirement() {
        return requirement;
    }

    public void setRequirement(RequirementEntity requirement) {
        this.requirement = requirement;
    }
}
