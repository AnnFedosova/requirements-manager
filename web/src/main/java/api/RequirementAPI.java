package api;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import dto.RequirementDTO;
import dto.RequirementPriorityDTO;
import dto.RequirementStateDTO;
import dto.RequirementTypeDTO;

import javax.ws.rs.client.*;
import javax.ws.rs.core.Response;
import java.util.List;

public class RequirementAPI {
    private final static String URL = ServerConnection.API_URL + "requirements/";

    public static Response addRequirement(RequirementDTO requirement) {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(URL + "addRequirement");
        Invocation.Builder builder = target.request();
        return builder.post(Entity.entity(requirement, "application/json; charset=UTF-8"));
    }

    public static Response editRequirement(RequirementDTO requirement) {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(URL + "editRequirement");
        Invocation.Builder builder = target.request();
        return builder.post(Entity.entity(requirement, "application/json; charset=UTF-8"));
    }


    public static List<RequirementDTO> getRequirementsByRelease(long requirementId) throws Exception {
        String json = JSONHelper.getJson(URL + "getRequirementsByRelease/" + requirementId);
        return new Gson().fromJson(json, new TypeToken<List<RequirementDTO>>(){}.getType());
    }

    public static List<RequirementDTO> getRequirementsBySpecification(long requirementId) throws Exception {
        String json = JSONHelper.getJson(URL + "getRequirementsBySpecification/" + requirementId);
        return new Gson().fromJson(json, new TypeToken<List<RequirementDTO>>(){}.getType());
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

    public static RequirementPriorityDTO getRequirementPriority(long requirementId) throws Exception {
        String json = JSONHelper.getJson(URL + "getPriority/" + requirementId);
        return new Gson().fromJson(json, new TypeToken<RequirementPriorityDTO>(){}.getType());
    }


    public static RequirementStateDTO getRequirementState(long requirementId) throws Exception {
        String json = JSONHelper.getJson(URL + "getState/" + requirementId);
        return new Gson().fromJson(json, new TypeToken<RequirementStateDTO>(){}.getType());
    }

    public static RequirementTypeDTO getRequirementType(long requirementId) throws Exception {
        String json = JSONHelper.getJson(URL + "getType/" + requirementId);
        return new Gson().fromJson(json, new TypeToken<RequirementTypeDTO>(){}.getType());
    }

    public static List<RequirementDTO> getRequirementsByProject(long projectId) throws Exception {
        String json = JSONHelper.getJson(URL + "getRequirementsByProject/" + projectId);
        return new Gson().fromJson(json, new TypeToken<List<RequirementDTO>>(){}.getType());
    }


}
