package base.controller;

import base.core.TestBaseTNG;
import com.google.inject.Inject;
import com.jayway.restassured.response.ValidatableResponse;

import static com.jayway.restassured.RestAssured.given;

/**
 * Created by @v.matviichenko
 */
public class ContactsAPI extends TestBaseTNG {
    private final String API_DOMAIN_URL = properties.getServerProperty("api_domain_url");
    private final String HEALTH_CHECK_ENDPOINT = properties.getCommonProperty("healthcheck_endpoint");
    private final String CONTACT_URL = API_DOMAIN_URL + properties.getCommonProperty("api_version") + properties.getCommonProperty("contacts_endpoint");

    @Inject
    public ContactsAPI() {

    }

    public ValidatableResponse getHealthCheck() {
        return  given().get(API_DOMAIN_URL + HEALTH_CHECK_ENDPOINT).then();
    }

    public ValidatableResponse createContact(String requestBody) {
        return given().contentType("application/json").body(requestBody).post(CONTACT_URL).then();
    }

    public ValidatableResponse getContactById(Integer userId) {
        return given().get(CONTACT_URL + "/" + userId).then();
    }

    public ValidatableResponse getContacts() {
        return given().get(CONTACT_URL).then();
    }

    public ValidatableResponse findContact(String firstName, String email) {
        return given().get(CONTACT_URL + "?firstName=" + firstName + "&email=" + email).then();
    }

    public ValidatableResponse updateContact(String requestBody, Integer userId) {
        return given().contentType("application/json").body(requestBody).put(CONTACT_URL + "/" + userId).then();
    }

    public ValidatableResponse deleteContact(Integer userId) {
        return given().delete(CONTACT_URL + "/" + userId).then();
    }

    public ValidatableResponse patchContact(String requestBody, Integer userId) {
        return given().contentType("application/json").body(requestBody).patch(CONTACT_URL + "/" + userId).then();
    }
}
