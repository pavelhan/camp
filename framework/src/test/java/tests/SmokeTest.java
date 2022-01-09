package tests;

import api.clients.web.WebClient;
import api.clients.web.auth_providers.AuthProvider;
import api.clients.web.auth_providers.BasicAuth;
import models.Dashboard;
import models.User;
import org.junit.Before;
import org.junit.Test;
import config.*;
import enums.CfgParams;

public class SmokeTest {

    private Config config;
    private AuthProvider authProvider;

    @Before
    public void setUp(){
        config = Config.getInstance();
        authProvider = new BasicAuth();
    }

    @Test
    public void fullConfigTest() throws Exception {
        System.out.println(config.get(CfgParams.BASE_URL.toString()));
        System.out.println(config.get(CfgParams._OS.toString()));
    }

    @Test
    public void test() throws Exception {
        WebClient webClient = new WebClient(authProvider);
        webClient.login(new User(config.get("EMAIL"),config.get("PASSWORD"))).getDashboardItems();
    }
}
