package api;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import dto.ProjectDTO;
import dto.UserProjectRoleDTO;

import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

public class ProjectAPI {
    private final static String URL = ServerConnection.API_URL + "projects/";

    public static List<ProjectDTO> getProjectsList() throws Exception {
        String json = JSONHelper.getJson(URL + "getProjectsList");
        return new Gson().fromJson(json, new TypeToken<List<ProjectDTO>>(){}.getType());
    }

    public static ProjectDTO getProject(long projectId) throws Exception {
        String json = JSONHelper.getJson(URL + "getProject/" + projectId);
        return new Gson().fromJson(json, new TypeToken<ProjectDTO>(){}.getType());
    }

    public static List<UserProjectRoleDTO> getProjectRoles(long projectId) throws Exception {
        String json = JSONHelper.getJson(URL + "getUsersByProjectId/" + projectId);
        return new Gson().fromJson(json, new TypeToken<List<UserProjectRoleDTO>>(){}.getType());
    }

    public static Response editProject(ProjectDTO project) {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(URL + "editProject");
        Invocation.Builder builder = target.request();
        return builder.post(Entity.entity(project, MediaType.APPLICATION_JSON));
    }


}