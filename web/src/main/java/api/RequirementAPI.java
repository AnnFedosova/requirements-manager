package api;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import dto.RequirementDTO;
import dto.RequirementPriorityDTO;
import dto.RequirementStateDTO;
import dto.RequirementTypeDTO;

import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

public class RequirementAPI {
    private final static String URL = ServerConnection.API_URL + "requirements/";

    public static Response addRequirement(RequirementDTO requirement) {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(URL + "addRequirement");
        Invocation.Builder builder = target.request();
        return builder.post(Entity.entity(requirement, MediaType.APPLICATION_JSON));
    }

    public static List<RequirementStateDTO> getRequirementStates() throws Exception {
        String json = JSONHelper.getJson(URL + "getRequirementStates");
        return new Gson().fromJson(json, new TypeToken<List<RequirementStateDTO>>(){}.getType());
    }

    public static List<RequirementPriorityDTO> getRequirementPriorities() throws Exception {
        String json = JSONHelper.getJson(URL + "getRequirementPriorities");
        return new Gson().fromJson(json, new TypeToken<List<RequirementPriorityDTO>>(){}.getType());
    }

    public static List<RequirementTypeDTO> getRequirementTypes() throws Exception {
        String json = JSONHelper.getJson(URL + "getRequirementTypes" );
        return new Gson().fromJson(json, new TypeToken<List<RequirementTypeDTO>>(){}.getType());
    }

    public static List<RequirementDTO> getRequirementsList() throws Exception {
        String json = JSONHelper.getJson(URL + "getRequirementsList");
        return new Gson().fromJson(json, new TypeToken<List<RequirementDTO>>(){}.getType());
    }

    public static RequirementDTO getRequirement(long requirementId) throws Exception {
        String json = JSONHelper.getJson(URL + "getRequirement/" + requirementId);
        return new Gson().fromJson(json, new TypeToken<RequirementDTO>(){}.getType());
    }

    public static RequirementDTO getRequirementPriority(long requirementId) throws Exception {
        String json = JSONHelper.getJson(URL + "getPriority/" + requirementId);
        return new Gson().fromJson(json, new TypeToken<RequirementDTO>(){}.getType());
    }

    public static RequirementDTO getRequirementState(long requirementId) throws Exception {
        String json = JSONHelper.getJson(URL + "getState/" + requirementId);
        return new Gson().fromJson(json, new TypeToken<RequirementDTO>(){}.getType());
    }

    public static List<RequirementDTO> getRequirementsByProject(long projectId) throws Exception {
        String json = JSONHelper.getJson(URL + "getRequirementsByProject/" + projectId);
        return new Gson().fromJson(json, new TypeToken<List<RequirementDTO>>(){}.getType());
    }


}
