package api.clients.web;

import api.clients.web.auth_providers.AuthProvider;
import api.clients.web.urls.CIDEndpoints;
import http_session.HttpSession;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import models.Dashboard;
import models.User;
import org.json.JSONArray;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class WebClient {
    AuthProvider authProvider;
    HttpSession httpSession;
    public WebClient(AuthProvider authProvider) {
           this.authProvider = authProvider;
           this.httpSession = null;
    }

    public WebClient login(User user) throws Exception {
        this.httpSession = authProvider.login(user, "60 * 60 * 24");
        return this;
    }

    public void getDashboardItems() throws JsonProcessingException {
        /*Get full folder content for Layer Cake implementation
        :param folder_id: if None return root folder content
        :return: respond json*/
        Response response = this.httpSession.get(CIDEndpoints.valueOf("dashboard").getResource(),new HashMap<>());
        /*System.out.println(response.body().asString());
        JSONArray jsonArray = new JSONArray(response.body().toString());
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(String.valueOf(jsonArray), new TypeReference<>(){});*/
    }

}
