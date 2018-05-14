package api.services;

import dBService.DBException;
import dBService.DBService;
import dBService.dto.UserDTO;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("/api/users")
@Produces({"application/json"})
public class UserService {
    private DBService dbService = DBService.getInstance();

    @GET
    @Path("getUser/{userId}")
    public UserDTO getUser(@PathParam("userId") long id) {
        return new UserDTO(dbService.getUser(id));
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
