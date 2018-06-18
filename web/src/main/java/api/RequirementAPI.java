package api;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import dto.RequirementDTO;

import java.util.List;

public class RequirementAPI {
    private final static String URL = ServerConnection.API_URL + "requirements/";

    public static List<RequirementDTO> getRequirementsList() throws Exception {
        String json = JSONHelper.getJson(URL + "getRequirementsList");
        return new Gson().fromJson(json, new TypeToken<List<RequirementDTO>>(){}.getType());
    }

    public static RequirementDTO getRequirement(long requirementId) throws Exception {
        String json = JSONHelper.getJson(URL + "getRequirement/" + requirementId);
        return new Gson().fromJson(json, new TypeToken<RequirementDTO>(){}.getType());
    }

    public static List<RequirementDTO> getRequirementsByProject(long projectId) throws Exception {
        String json = JSONHelper.getJson(URL + "getRequirementsByProject");
        return new Gson().fromJson(json, new TypeToken<List<RequirementDTO>>(){}.getType());
    }


}
