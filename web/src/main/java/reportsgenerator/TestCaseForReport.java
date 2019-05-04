package reportsgenerator;

import api.UserAPI;
import dto.TestCaseDTO;

public class TestCaseForReport {

    private long id;
    private long requirementId;
    private String name;
    private String creationDate;
    private String plan;
    private String startConditions;
    private String endConditions;
    private String data;
    private long creatorId;
    private String creatorLogin; // новое

    public TestCaseForReport(){}

    public TestCaseForReport(TestCaseDTO testCase){
        this.id = testCase.getId();
        requirementId = testCase.getRequirementId();
        name = testCase.getName();
        creationDate = testCase.getCreationDate();
        plan = testCase.getPlan();
        startConditions = testCase.getStartConditions();
        endConditions = testCase.getEndConditions();
        data = testCase.getData();
        creatorId = testCase.getCreatorId();

        try {
            creatorLogin = UserAPI.getUser(testCase.getCreatorId()).getLogin();
        } catch (Exception e) {
            creatorLogin = "Admin";
        }
    }

    public TestCaseForReport(long id, long requirementId, String name, String creationDate, String plan, String startConditions, String endConditions, String data, long creatorId, String creatorLogin) {
        this.id = id;
        this.requirementId = requirementId;
        this.name = name;
        this.creationDate = creationDate;
        this.plan = plan;
        this.startConditions = startConditions;
        this.endConditions = endConditions;
        this.data = data;
        this.creatorId = creatorId;
        this.creatorLogin = creatorLogin;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getRequirementId() {
        return requirementId;
    }

    public void setRequirementId(long requirementId) {
        this.requirementId = requirementId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getPlan() {
        return plan;
    }

    public void setPlan(String plan) {
        this.plan = plan;
    }

    public String getStartConditions() {
        return startConditions;
    }

    public void setStartConditions(String startConditions) {
        this.startConditions = startConditions;
    }

    public String getEndConditions() {
        return endConditions;
    }

    public void setEndConditions(String endConditions) {
        this.endConditions = endConditions;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(long creatorId) {
        this.creatorId = creatorId;
    }

    public String getCreatorLogin() {
        return creatorLogin;
    }

    public void setCreatorLogin(String creatorLogin) {
        this.creatorLogin = creatorLogin;
    }
}
