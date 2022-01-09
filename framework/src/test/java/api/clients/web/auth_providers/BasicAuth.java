package api.clients.web.auth_providers;

import api.clients.web.urls.CIDEndpoints;
import config.Config;
import enums.CfgParams;
import http_session.HttpSession;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import models.User;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class BasicAuth implements AuthProvider {
    String DEFAULT_TOKEN_EXPIRE = CfgParams.DEFAULT_TOKEN_EXPIRE.toString();
    String token = "X-Token";
    HttpSession httpSession;
    String url;
    JsonPath jsonString;
    PrintStream log;
    RequestSpecification requestSpecification;

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

    @Override
    public HttpSession login(User user, String expires) throws Exception {
        /*Default login.
        :param user: User-type object based on models.User
        :param expires: seconds after which token is expired, int
        :return: response*/
        String _expires = "";
        if (!expires.isEmpty()){
            try{
                _expires = DEFAULT_TOKEN_EXPIRE;
            }catch (Exception exception){
                System.out.println(exception.getStackTrace());
            }
        }
        //Setting RestAssured base URI
        baseURI = Config.getInstance().get(CfgParams.BASE_URL.toString());
        //Setting app login url
        url = baseURI + CIDEndpoints.valueOf("login").getResource();

        //Getting http session with basic auth (sending login request and save session data (token))
        Response response = given().spec(reqSpec()).auth().preemptive().basic(user.getEmail(),user.getPassword())
                .header("Accept", ContentType.JSON.getAcceptHeader())
                .contentType(ContentType.JSON)
                .body("")
                .post(url);

        //Parsing response JSON and saving token and expiration time to session object
        httpSession = new HttpSession();
        httpSession.setUser(user);
        jsonString = new JsonPath(response.asString());
        httpSession.setExpires(jsonString.getString("expires"));
        httpSession.setToken(jsonString.getString("token"));

        requestSpecification = reqSpec().header(token, httpSession.getToken())
                .header("ContentType",ContentType.JSON);
        httpSession.setRequestSpecification(requestSpecification);
        return httpSession;
    }
}