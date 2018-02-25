package base;

import com.jayway.restassured.response.ValidatableResponse;

import static com.jayway.restassured.RestAssured.given;

/**
 * Created by @v.matviichenko
 */
public class APIEndpoints {
    private final String API_ENDPOINT_PATH = "http://172.18.0.1:";

    public ValidatableResponse getHealthCheck(Integer port) {
        return  given().get(API_ENDPOINT_PATH + port + "/healthcheck").then();
    }

}
