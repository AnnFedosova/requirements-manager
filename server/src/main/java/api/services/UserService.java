package api.services;

import dBService.DBException;
import dBService.DBService;
import dBService.dto.UserDTO;
import dBService.entities.UserEntity;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.LinkedList;
import java.util.List;

@Path("/api/users")
@Produces("application/json; charset=UTF-8")
@Consumes("application/json; charset=UTF-8")
public class UserService {
    private DBService dbService = DBService.getInstance();

    @GET
    @Path("getUser/{userId}")
    public UserDTO getUser(@PathParam("userId") long id) {
        return new UserDTO(dbService.getUser(id));
    }

    @GET
    @Path("isAdmin/{userLogin}")
    public boolean isAdmin(@PathParam("userLogin") String userLogin) {
        return dbService.isAdmin(userLogin);
    }

    @GET
    @Path("getUserByLogin/{userLogin}")
    public UserDTO getUserByLogin (@PathParam("userLogin") String userLogin){
        return new UserDTO (dbService.getUser(userLogin));
    }

    @GET
    @Path("getAllUsers")
    public List<UserDTO> gerAllUsers(){
        List<UserEntity> userEntities = dbService.getAllUsers();
        List<UserDTO> users = new LinkedList<>();
        for (UserEntity userEntity : userEntities) {
            users.add(new UserDTO(userEntity));
        }
        return users;
    }

    @GET
    @Path("getUsersByProject/{projectId}")
    public List<UserDTO> getUsersByProjectId(@PathParam("projectId") long id) {
        List<UserEntity> userEntities = dbService.getUsersByProjectId(id);
        List<UserDTO> users = new LinkedList<>();
        for (UserEntity userEntity : userEntities) {
            users.add(new UserDTO(userEntity));
        }
        return users;
    }

    @POST
    @Path("addUser")
    public Response addUser(UserDTO user) {
        try {
            long userId = dbService.addUser(user.getLogin(), user.getPassword(), user.getFirstName(), user.getLastName(), user.getMiddleName());
            String result = "User added with id = " + userId;
            return Response.ok().entity(result).build();
        } catch (DBException e) {
            e.printStackTrace();
            String result = "Error :(";
            return Response.serverError().entity(result).build();
        }
    }
}
