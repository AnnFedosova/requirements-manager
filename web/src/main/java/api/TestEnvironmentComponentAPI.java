package api;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import dto.TestEnvironmentComponentDTO;
import dto.TestEnvironmentDTO;

import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

public class TestEnvironmentComponentAPI {

    private final static String URL = ServerConnection.API_URL + "testEnvironmentComponent/";

    public static TestEnvironmentComponentDTO getTestEnvironmentComponent(long testEnvironmentComponentId) throws Exception {
        String json = JSONHelper.getJson(URL + "getTestEnvironmentComponent/" + testEnvironmentComponentId);
        return new Gson().fromJson(json, new TypeToken<TestEnvironmentComponentDTO>(){}.getType());
    }

    public static List<TestEnvironmentComponentDTO> getAllTestEnvironmentComponents() throws Exception {
        String json = JSONHelper.getJson(URL + "getAllTestEnvironmentComponents");
        return new Gson().fromJson(json, new TypeToken<List<TestEnvironmentComponentDTO>>(){}.getType());
    }

    public static Response addTestEnvironmentComponent(TestEnvironmentComponentDTO testEnvironmentComponent) {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(URL + "addTestEnvironmentComponent");
        Invocation.Builder builder = target.request();
        return builder.post(Entity.entity(testEnvironmentComponent, "application/json; charset=UTF-8"));
    }

       public static Response editTestEnvironmentComponent(TestEnvironmentComponentDTO testEnvironmentComponent) {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(URL + "editTestEnvironmentComponent");
        Invocation.Builder builder = target.request();
        return builder.post(Entity.entity(testEnvironmentComponent, MediaType.APPLICATION_JSON));
    }
}
