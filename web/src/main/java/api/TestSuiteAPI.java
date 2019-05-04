package api;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import dto.TestPlanDTO;
import dto.TestSuiteDTO;

import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

public class TestSuiteAPI {

    private final static String URL = ServerConnection.API_URL + "testSuite/";

    public static TestSuiteDTO getTestSuite(long testSuiteId) throws Exception {
        String json = JSONHelper.getJson(URL + "getTestSuite/" + testSuiteId);
        return new Gson().fromJson(json, new TypeToken<TestSuiteDTO>(){}.getType());
    }

    public static Response createTestSuiteForTestPlan(TestSuiteDTO testSuite, long testPlanId) {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(URL + "createTestSuiteForTestPlan" + testPlanId);
        Invocation.Builder builder = target.request();
        return builder.post(Entity.entity(testSuite, "application/json; charset=UTF-8"));
    }

    public static Response createTestSuiteForProject(TestSuiteDTO testSuite, long projectId) {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(URL + "createTestSuiteForProject" + projectId);
        Invocation.Builder builder = target.request();
        return builder.post(Entity.entity(testSuite, "application/json; charset=UTF-8"));
    }

    public static List<TestSuiteDTO> getTestSuitesByProject(long projectId) throws Exception {
        String json = JSONHelper.getJson(URL + "getTestSuitesByProjectId" + projectId);
        return new Gson().fromJson(json, new TypeToken<List<TestSuiteDTO>>(){}.getType());
    }

    public static Response editTestSuite(TestSuiteDTO testSuite) {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(URL + "editTestSuite");
        Invocation.Builder builder = target.request();
        return builder.post(Entity.entity(testSuite, MediaType.APPLICATION_JSON));
    }
}
