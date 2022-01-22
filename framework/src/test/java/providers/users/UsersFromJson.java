package providers.users;

import models.User;
import org.json.simple.JSONObject;

import static config.Config.config;
import static tools.helpers.Readers.*;

public class UsersFromJson extends UsersProvider{
    Object usersObjFromJson = null;

    public void parseUsers() throws Exception {
        usersObjFromJson = getObjFromJsonFile(config().get("USERS_JSON_PATH"));

    }

    @Override
    public User getUserByName(String name) {
        try {
            parseUsers();
        } catch (Exception e) {
            e.printStackTrace();
        }
        JSONObject jsonObject = (JSONObject) usersObjFromJson;

        User user = new User();
        user.setEmail(name);
        user.setPassword((String) jsonObject.get(name));
        return user;
    }

    @Override
    public User getDefaultUser() {
        User user = new User();
        try {
            parseUsers();
            JSONObject jsonObject = (JSONObject) usersObjFromJson;
            user.setEmail(config().get("EMAIL"));
            user.setPassword((String) jsonObject.get(config().get("EMAIL")));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }
}
