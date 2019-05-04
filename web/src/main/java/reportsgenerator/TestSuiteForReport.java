package reportsgenerator;

import api.UserAPI;
import dto.TestSuiteDTO;

public class TestSuiteForReport {
    private long id;
    private String name;
    private String description;
    private String plan;
    private long creatorId;
    private String creatorLogin; //новое

    public TestSuiteForReport(long id, String name, String description, String plan, long creatorId, String creatorLogin) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.plan = plan;
        this.creatorId = creatorId;
        this.creatorLogin = creatorLogin;
    }

    public TestSuiteForReport(TestSuiteDTO testSuite){
        this.id = testSuite.getId();
        this.name = testSuite.getName();
        this.description = testSuite.getDescription();
        this.plan = testSuite.getPlan();
        this.creatorId = testSuite.getCreatorId();
        try {
            creatorLogin = UserAPI.getUser(testSuite.getCreatorId()).getLogin();
        } catch (Exception e) {
            creatorLogin = "Admin";
        }
    }
    TestSuiteForReport(){}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPlan() {
        return plan;
    }

    public void setPlan(String plan) {
        this.plan = plan;
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
