package base.controller;

import base.core.TestBaseTNG;
import com.google.inject.Inject;
import com.jayway.restassured.response.ValidatableResponse;

import static com.jayway.restassured.RestAssured.given;

/**
 * Created by @v.matviichenko
 */
public class ContactAPI extends TestBaseTNG {
    private final String API_DOMAIN_URL = properties.getServerProperty("api_domain_url");
    private final String API_VERSION = properties.getCommonProperty("api_version");
    private final String CONTACT_ENDPOINT = properties.getCommonProperty("contacts_endpoint");
    private final String HEALTH_CHECK_ENDPOINT = properties.getCommonProperty("healthcheck_endpoint");

    @Inject
    public ContactAPI() {

    }

    public ValidatableResponse getHealthCheck() {
        return  given().get(API_DOMAIN_URL + HEALTH_CHECK_ENDPOINT).then();
    }

    public ValidatableResponse createContact(String requestBody) {
        return given().contentType("application/json").body(requestBody).post(API_DOMAIN_URL + API_VERSION + CONTACT_ENDPOINT).then();
    }

    public ValidatableResponse getContactById(Integer userId) {
        return given().get(API_DOMAIN_URL + API_VERSION + CONTACT_ENDPOINT + "/" + userId).then();
    }

    public ValidatableResponse getContacts() {
        return given().get(API_DOMAIN_URL + API_VERSION + CONTACT_ENDPOINT).then();
    }

    public ValidatableResponse findContact(String firstName, String email) {
        return given().get(API_DOMAIN_URL + API_VERSION + CONTACT_ENDPOINT + "?firstName=" + firstName + "&email=" + email).then();
    }

    public ValidatableResponse updateContact(String requestBody, Integer userId) {
        return given().contentType("application/json").body(requestBody).put(API_DOMAIN_URL + API_VERSION + CONTACT_ENDPOINT + "/" + userId).then();
    }

    public ValidatableResponse deleteContact(Integer userId) {
        return given().delete(API_DOMAIN_URL + API_VERSION + CONTACT_ENDPOINT + "/" + userId).then();
    }

    public ValidatableResponse patchContact(String requestBody, Integer userId) {
        return given().contentType("application/json").body(requestBody).patch(API_DOMAIN_URL + API_VERSION + CONTACT_ENDPOINT + "/" + userId).then();
    }
}
