package api.services;

import dBService.DBService;
import dBService.dto.UserDTO;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

@Path("/api/users")
@Produces({"application/json"})
public class UserService {
    private DBService dbService = DBService.getInstance();

    @GET
    @Path("getUser/{userId}")
    public UserDTO getUser(@PathParam("userId") long id) {
        return new UserDTO(dbService.getUser(id));
    }
}
