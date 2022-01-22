package tests.api;

import api.clients.web.WebClient;
import api.clients.web.auth_providers.AuthProvider;
import api.clients.web.auth_providers.BasicAuth;
import models.User;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import providers.users.Users;
import providers.users.UsersFromJson;
import providers.users.UsersProvider;

import static config.Config.config;

public class SmokeTest {

    private AuthProvider authProvider;
    private Users users;
    private UsersProvider usersProvider;
    private WebClient webClient;
    private User globalLogicUser;

    @BeforeEach
    public void setUp(){
        authProvider = new BasicAuth();
        usersProvider = new UsersFromJson();
        users = new Users();
        webClient = new WebClient(authProvider);
        globalLogicUser = users.getDefault(usersProvider);
    }

    @Test
    public void fullConfigTest() throws Exception {
        System.out.println(config().get("BASE_URL"));
        System.out.println(config().get("_OS"));
    }

    @Test
    public void test() throws Exception {
        webClient.login(globalLogicUser).getDashboardItems();
    }
}
