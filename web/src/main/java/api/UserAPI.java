package api;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import dto.UserDTO;

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

}
