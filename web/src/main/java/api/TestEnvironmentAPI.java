package api;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import dto.TestEnvironmentDTO;
import dto.TestPlanDTO;

import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

public class TestEnvironmentAPI {

    private final static String URL = ServerConnection.API_URL + "testEnvironment/";

    public static TestEnvironmentDTO getTestEnvironment(long testEnvironmentId) throws Exception {
        String json = JSONHelper.getJson(URL + "getTestEnvironment/" + testEnvironmentId);
        return new Gson().fromJson(json, new TypeToken<TestEnvironmentDTO>(){}.getType());
    }

    public static List<TestEnvironmentDTO> getAllTestEnvironments() throws Exception {
        String json = JSONHelper.getJson(URL + "getAllTestEnvironments");
        return new Gson().fromJson(json, new TypeToken<List<TestEnvironmentDTO>>(){}.getType());
    }

    public static Response addTestEnvironment(TestEnvironmentDTO testEnvironment) {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(URL + "addTestEnvironment");
        Invocation.Builder builder = target.request();
        return builder.post(Entity.entity(testEnvironment, "application/json; charset=UTF-8"));
    }

    public static List<TestEnvironmentDTO> getTestEnvironmentByTestPlanId(long testPlanId) throws Exception {
        String json = JSONHelper.getJson(URL + "getTestEnvironmentByTestPlanId" + testPlanId);
        return new Gson().fromJson(json, new TypeToken<List<TestEnvironmentDTO>>(){}.getType());
    }

    public static Response editTestEnvironment(TestEnvironmentDTO testEnvironment) {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(URL + "editTestEnvironment");
        Invocation.Builder builder = target.request();
        return builder.post(Entity.entity(testEnvironment, MediaType.APPLICATION_JSON));
    }
}
