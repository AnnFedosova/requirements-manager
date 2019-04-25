package api;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import dto.TestCaseResultDTO;
import dto.TestPlanDTO;

import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

public class TestCaseResultAPI {
    private final static String URL = ServerConnection.API_URL + "testCaseResult/";

    public static TestCaseResultDTO getTestCaseResult(long testCaseResultId) throws Exception {
        String json = JSONHelper.getJson(URL + "getTestCaseResult/" + testCaseResultId);
        return new Gson().fromJson(json, new TypeToken<TestCaseResultDTO>(){}.getType());
    }

    public static Response addTestCaseResult(TestCaseResultDTO testCaseResult) {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(URL + "addTestCaseResult");
        Invocation.Builder builder = target.request();
        return builder.post(Entity.entity(testCaseResult, "application/json; charset=UTF-8"));
    }

    public static List<TestCaseResultDTO> getTestCaseResultsByTestCaseId(long testCaseId) throws Exception {
        String json = JSONHelper.getJson(URL + "getTestCaseResultsByTestCaseId" + testCaseId);
        return new Gson().fromJson(json, new TypeToken<List<TestCaseResultDTO>>(){}.getType());
    }

}
