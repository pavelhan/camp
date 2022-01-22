package api.clients.web.auth_providers;

import static config.Config.config;
import http_session.HttpSession;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static config.Config.getEndpoint;
import models.User;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class BasicAuth extends AuthProvider {

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
        baseURI = config().get("BASE_URL");
        //Setting app login url
        url = baseURI + getEndpoint("login");

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