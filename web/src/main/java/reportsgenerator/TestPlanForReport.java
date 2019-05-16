package reportsgenerator;

import api.TestEnvironmentAPI;
import api.UserAPI;
import dto.TestEnvironmentDTO;
import dto.TestPlanDTO;

public class TestPlanForReport extends TestPlanDTO {
    private String test_environment;
    private String creatorLogin;

    public TestPlanForReport(TestPlanDTO testPlan){
        this.setId(testPlan.getId());
        this.setName(testPlan.getName());
        this.setDescription(testPlan.getDescription());
        this.setDate_from(testPlan.getDate_from());
        this.setDate_to(testPlan.getDate_to());
        this.setComment(testPlan.getComment());
        this.setTest_environment_id(testPlan.getTest_environment_id());
        this.setCreatorId(testPlan.getCreatorId());

        try {
            this.setTest_environment(TestEnvironmentAPI.getTestEnvironment(testPlan.getTest_environment_id()).getName());
        } catch (Exception e) {
            TestEnvironmentDTO testEnvironment = new TestEnvironmentDTO(1, "Test Environment 1",
                    "Конкретный экземпляр конфигурации аппаратного и программного обеспечения, предназначенный для тестирования работы в контролируемой среде.");
            this.setTest_environment(testEnvironment.getName());
        }
        try {
            this.setCreatorLogin(UserAPI.getUser(testPlan.getCreatorId()).getLogin());
        } catch (Exception e) {
            this.setCreatorLogin("Admin");
        }

    }


    public String getTest_environment() {
        return test_environment;
    }

    public void setTest_environment(String test_environment) {
        this.test_environment = test_environment;
    }

    public String getCreatorLogin() {
        return creatorLogin;
    }

    public void setCreatorLogin(String creatorLogin) {
        this.creatorLogin = creatorLogin;
    }
}
