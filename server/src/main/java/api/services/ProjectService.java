package api.services;

import dBService.DBException;
import dBService.DBService;
import dBService.dto.ProjectDTO;
import dBService.dto.UserDTO;
import dBService.dto.UserProjectRoleDTO;
import dBService.entities.ProjectEntity;
import dBService.entities.UserEntity;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.LinkedList;
import java.util.List;

@Path("/api/projects")
@Produces("text/json; charset=UTF-8")
@Consumes("text/json; charset=UTF-8")
public class ProjectService {
    private DBService dbService = DBService.getInstance();

    @GET
    @Path("getProject/{projectId}")
    public ProjectDTO getProject(@PathParam("projectId") long id) {
        return new ProjectDTO(dbService.getProject(id));
    }

    @GET
    @Path("getProjectsList")
    public List<ProjectDTO> getProjectsList() {
        List<ProjectDTO> projects = new LinkedList<>();
        List<ProjectEntity> projectEntities = dbService.getProjectsList();
        for (ProjectEntity projectEntity : projectEntities) {
            projects.add(new ProjectDTO(projectEntity));
        }
        return projects;
    }

    @POST
    @Path("addProject")
    public Response addProject(ProjectDTO project) {
        try {
            long projectId = dbService.addProject(project.getName(), project.getDescription());
            String result = "Project added with id = " + projectId;
            return Response.ok().entity(result).build();
        } catch (DBException e) {
            e.printStackTrace();
            String result = "Error :(";
            return Response.serverError().entity(result).build();
        }
    }

    @POST
    @Path("editProject")
    public Response editRequest(ProjectDTO project) {
        ProjectEntity projectEntity = dbService.getProject(project.getId());
        projectEntity.setName(project.getName());
        projectEntity.setDescription(project.getDescription());
        dbService.updateProject(projectEntity);
        String result = "Project updated with id = " + project.getId();
        return Response.ok().entity(result).build();
    }

    @POST
    @Path("addUserToProject")
    public void addUserToProject(UserProjectRoleDTO userProjectRoleDTO){
            dbService.addUserToProject(userProjectRoleDTO.getUserId(), userProjectRoleDTO.getProjectId(),userProjectRoleDTO.getProjectRoleId());
    }

    @GET
    @Path("getUsersByProjectId/{projectId}")
    public List<UserDTO> getUsersByProjectId(@PathParam("projectId") long id) {
        List<UserEntity> userEntities = dbService.getUsersByProjectId(id);
        List<UserDTO> users = new LinkedList<>();
        for (UserEntity userEntity : userEntities) {
            users.add(new UserDTO(userEntity));
        }
        return users;
    }
}
