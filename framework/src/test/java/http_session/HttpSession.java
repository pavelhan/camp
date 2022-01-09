package http_session;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import lombok.Data;
import models.User;

import java.util.Map;

import static io.restassured.RestAssured.given;

@Data
public class HttpSession {
    User user;
    String expires;
    String token;
    RequestSpecification requestSpecification;
    ResponseSpecification responseSpecification;

    public Response get(String urlPath, Map<String, String> params){
        if (params.size()>0){
            requestSpecification.basePath(urlPath);
            requestSpecification.pathParams(params);
        }else {
            requestSpecification.basePath(urlPath);
        }

        return given().spec(requestSpecification).when().get();
    }

}
