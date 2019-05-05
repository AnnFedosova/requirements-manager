package api;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import dto.RequirementDTO;
import dto.TestCaseDTO;

import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

public class TestCaseAPI {
    private final static String URL = ServerConnection.API_URL + "testCase/";

    public static TestCaseDTO getTestCase(long testCaseId) throws Exception {
        String json = JSONHelper.getJson(URL + "getTestCase/" + testCaseId);
        return new Gson().fromJson(json, new TypeToken<TestCaseDTO>(){}.getType());
    }

    public static Response createTestCaseForTestPlan(TestCaseDTO testCase, String testPlanId) {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(URL + "createTestCaseForTestPlan" + testPlanId);
        Invocation.Builder builder = target.request();
        return builder.post(Entity.entity(testCase, "application/json; charset=UTF-8"));
    }

    public static Response createTestCaseForReq(TestCaseDTO testCase) {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(URL + "createTestCaseForReq");
        Invocation.Builder builder = target.request();
        return builder.post(Entity.entity(testCase, "application/json; charset=UTF-8"));
    }

    public static List<TestCaseDTO> getAllTestCases() throws Exception {
        String json = JSONHelper.getJson(URL + "getAllTestCases");
        return new Gson().fromJson(json, new TypeToken<List<TestCaseDTO>>(){}.getType());
    }

    public static List<RequirementDTO> getReqByTestCaseId(long testCaseId) throws Exception {
        String json = JSONHelper.getJson(URL + "getReqByTestCaseId" + testCaseId);
        return new Gson().fromJson(json, new TypeToken<List<RequirementDTO>>(){}.getType());
    }

    public static List<TestCaseDTO> getTestCasesByTestPlanId(long testPlanId) throws Exception {
        String json = JSONHelper.getJson(URL + "getTestCasesByTestPlanId" + testPlanId);
        return new Gson().fromJson(json, new TypeToken<List<TestCaseDTO>>(){}.getType());
    }

    public static Response editTestCase(TestCaseDTO testCase) {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(URL + "editTestCase");
        Invocation.Builder builder = target.request();
        return builder.post(Entity.entity(testCase, MediaType.APPLICATION_JSON));
    }

}
