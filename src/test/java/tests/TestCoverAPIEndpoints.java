package tests;

import base.controller.APIEndpoints;
import base.core.TestBaseTNG;
import com.google.inject.Inject;
import com.jayway.restassured.response.ValidatableResponse;
import maintanance_objects.Contact;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;

import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 * Created by @v.matviichenko
 */
public class TestCoverAPIEndpoints extends TestBaseTNG {
    private final String ENDPOINTS_SOURCE = "http://host:port/api/v1/contacts/";
    private final String FIRST_NAME = "Homer";
    private final String LAST_NAME = "Simpson";

    @Inject
    private APIEndpoints apiEndpoints;

    @BeforeClass
    public void setUp() {
        clearSetUp();
    }

    @Test
    public void testBaseAPIDescription() {
        apiEndpoints.getHealthCheck().statusCode(200).assertThat().body(equalTo("live"));
    }

    @Test
    public void testCreateContact() {
        //act
        Contact contact = new Contact(FIRST_NAME, LAST_NAME);

        //assert
        apiEndpoints.createContact(contact.getRequestBody())
                .statusCode(201)
                .body("data.id[0]", is(greaterThanOrEqualTo(0)))
                .body("data.info.email[0]", is(contact.getEmail()))
                .body("data.info.firstName[0]", is(contact.getFirstName()))
                .body("data.info.lastName[0]", is(contact.getLastName()));
    }

    @Test
    public void testGetContactById() {
        //act
        Contact contact = new Contact(FIRST_NAME, LAST_NAME);
        Integer contactId = getContactID(contact);

        //assert
        assertEndpointResponse(apiEndpoints.getContactById(contactId), 200, contact);
    }

    @Test
    public void testFindContact() {
        //act
        Contact contact = new Contact(FIRST_NAME, LAST_NAME);
        apiEndpoints.createContact(contact.getRequestBody());

        //assert
        assertEndpointResponse(apiEndpoints.findContact(FIRST_NAME, contact.getEmail()), 200, contact);
    }

    @Test
    public void testUpdateContact() {
        //act
        Contact contact = new Contact("Homer", "Simpson");
        Integer userId = getContactID(contact);

        //assert
        assertEndpointResponse(apiEndpoints.updateContact(contact.getRequestBody(), userId), 200, contact);

        apiEndpoints.deleteContact(userId).statusCode(200);
        apiEndpoints.getContactById(userId).statusCode(404);
    }

    private void assertEndpointResponse(ValidatableResponse response, Integer status, Contact user) {
        response.statusCode(status)
                .body("data.id[0]", is(user.getId()))
                .body("data.info.email[0]", is(user.getEmail()))
                .body("data.info.firstName[0]", is(user.getFirstName()))
                .body("data.info.lastName[0]", is(user.getLastName()))
                .body("data.refs.patch[0]", is(ENDPOINTS_SOURCE + user.getId()))
                .body("data.refs.get[0]", is(ENDPOINTS_SOURCE + user.getId()))
                .body("data.refs.delete[0]", is(ENDPOINTS_SOURCE + user.getId()))
                .body("data.refs.put[0]", is(ENDPOINTS_SOURCE + user.getId()))
                .body("status", is(200));
    }

    private void clearSetUp() {
        ArrayList<Integer> contactIDs = apiEndpoints.getContacts().statusCode(200).extract().jsonPath().get("data.id");
        for (Integer contactId : contactIDs) {
            apiEndpoints.deleteContact(contactId).statusCode(200);
        }
    }

    private Integer getContactID(Contact contact) {
        return apiEndpoints.createContact(contact.getRequestBody())
                .statusCode(201)
                .extract().jsonPath()
                .getInt("data.id[0]");
    }
}
