package base.controller;

import com.google.inject.Inject;
import com.jayway.restassured.response.ValidatableResponse;

import static com.jayway.restassured.RestAssured.given;

/**
 * Created by @v.matviichenko
 */
public class APIEndpoints {
    private final String API_ENDPOINT_PATH = "http://172.18.0.1:8182";

    @Inject
    public APIEndpoints() {

    }

    public ValidatableResponse getHealthCheck() {
        return  given().get(API_ENDPOINT_PATH + "/healthcheck").then();
    }

    public ValidatableResponse createContact(String requestBody) {
        return given().contentType("application/json").body(requestBody).post(API_ENDPOINT_PATH + "/api/v1/contacts").then();
    }

    public ValidatableResponse getContactById(Integer userId) {
        return given().get(API_ENDPOINT_PATH + "/api/v1/contacts/" + userId).then();
    }
}
