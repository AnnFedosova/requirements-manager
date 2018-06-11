package api;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import dto.UserDTO;

import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class UserAPI {
    private final static String URL = ServerConnection.API_URL + "users/";

    public static UserDTO getUser(String userLogin) throws Exception {
        String json = JSONHelper.getJson(URL + "getUserByLogin/" + userLogin);
        return new Gson().fromJson(json, new TypeToken<UserDTO>() {
        }.getType());
    }

    public static UserDTO getUser(long userId) throws Exception {
        String json = JSONHelper.getJson(URL + "getUser/" + userId);
        return new Gson().fromJson(json, new TypeToken<UserDTO>() {}.getType());
    }

    public static Response addUser(UserDTO user) {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(URL + "addUser");
        Invocation.Builder builder = target.request();
        return builder.post(Entity.entity(user, MediaType.APPLICATION_JSON));
    }

    public static boolean isAdmin(String userLogin) throws Exception {
        String json = JSONHelper.getJson(URL + "isAdmin/" + userLogin);
        return new Gson().fromJson(json, new TypeToken<Boolean>(){}.getType());
    }
}
