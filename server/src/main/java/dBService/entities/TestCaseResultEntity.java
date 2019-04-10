package dBService.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Test_Case_Result")
public class TestCaseResultEntity implements Serializable {
    private static final long serialVersionUID = 10_03_2019L;

    @Id
    @Column(name = "id", unique = true, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "date")
    private String date;

    @Column(name = "description",columnDefinition = "text")
    private String description;

    @ManyToOne
    @JoinColumn(name = "test_case_id")
    private TestCaseEntity testCase;

    @ManyToOne
    @JoinColumn(name = "tester_id")
    private UserEntity tester;

    public TestCaseResultEntity(){}

    public TestCaseResultEntity(String date, String description, TestCaseEntity testCase, UserEntity tester) {
        this.date = date;
        this.description = description;
        this.testCase = testCase;
        this.tester = tester;
    }

    public TestCaseResultEntity(TestCaseResultEntity testCaseResultEntity){
        this.id=testCaseResultEntity.id;
        this.date=testCaseResultEntity.date;
        this.description=testCaseResultEntity.description;
        this.testCase=testCaseResultEntity.testCase;
        this.tester=testCaseResultEntity.tester;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TestCaseEntity getTestCase() {
        return testCase;
    }

    public void setTestCase(TestCaseEntity testCase) {
        this.testCase = testCase;
    }

    public UserEntity getTester() {
        return tester;
    }

    public void setTester(UserEntity tester) {
        this.tester = tester;
    }
}
