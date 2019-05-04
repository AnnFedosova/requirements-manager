package api;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import dto.ProjectDTO;
import dto.TestCaseTestPlanDTO;
import dto.TestPlanDTO;
import dto.TestSuiteTestPlanDTO;

import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

public class TestPlanAPI {
    private final static String URL = ServerConnection.API_URL + "testPlan/";

    public static TestPlanDTO getTestPlan(long testPlanId) throws Exception {
        String json = JSONHelper.getJson(URL + "getTestPlan/" + testPlanId);
        return new Gson().fromJson(json, new TypeToken<TestPlanDTO>(){}.getType());
    }

    public static Response addTestPlan(TestPlanDTO testPlan) {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(URL + "addTestPlan");
        Invocation.Builder builder = target.request();
        return builder.post(Entity.entity(testPlan, "application/json; charset=UTF-8"));
    }

    public static List<TestPlanDTO> getTestPlansByProjectId(long projectId) throws Exception {
        String json = JSONHelper.getJson(URL + "getTestPlansByProjectId" + projectId);
        return new Gson().fromJson(json, new TypeToken<List<TestPlanDTO>>(){}.getType());
    }

    public static ProjectDTO getProjectByTestPlanId(long testPlanId) throws Exception {
        String json = JSONHelper.getJson(URL + "getProjectByTestPlanId" + testPlanId);
        return new Gson().fromJson(json, new TypeToken<ProjectDTO>(){}.getType());
    }

    public static Response editTestPlan(TestPlanDTO testPlan) {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(URL + "editTestPlan");
        Invocation.Builder builder = target.request();
        return builder.post(Entity.entity(testPlan, MediaType.APPLICATION_JSON));
    }

    public static Response addTestSuiteToTestPlan(TestSuiteTestPlanDTO testSuiteTestPlanDTO) {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(URL + "addTestSuiteToTestPlan");
        Invocation.Builder builder = target.request();
        return builder.post(Entity.entity(testSuiteTestPlanDTO, "application/json; charset=UTF-8"));
    }

    public static Response addTestCaseToTestPlan(TestCaseTestPlanDTO testCaseTestPlanDTO) {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(URL + "addTestCaseToTestPlan");
        Invocation.Builder builder = target.request();
        return builder.post(Entity.entity(testCaseTestPlanDTO, "application/json; charset=UTF-8"));
    }
}
