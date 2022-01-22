package api.clients.web.auth_providers;

import static config.Config.config;

import http_session.HttpSession;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import models.User;

import java.io.*;

public abstract class AuthProvider {

    protected PrintStream log;
    protected RequestSpecification requestSpecification;
    protected String DEFAULT_TOKEN_EXPIRE;
    protected String token = "X-Token";
    protected HttpSession httpSession;
    protected String url;
    protected JsonPath jsonString;

    public String getDEFAULT_TOKEN_EXPIRE() {
        try {
            DEFAULT_TOKEN_EXPIRE = config().get("DEFAULT_TOKEN_EXPIRE");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return DEFAULT_TOKEN_EXPIRE;
    }

    public abstract HttpSession login(User user, String expires) throws Exception;

    public RequestSpecification reqSpec(){
        try {
            log = new PrintStream(new FileOutputStream("requests_logs.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return new RequestSpecBuilder()
                .addFilter(RequestLoggingFilter.logRequestTo(log))
                .addFilter(RequestLoggingFilter.logRequestTo(log)).build();
    }

}
