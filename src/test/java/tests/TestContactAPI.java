package tests;

import base.controller.ContactAPI;
import base.core.ReportAllureListenerImpl;
import base.core.TestBaseTNG;
import com.github.javafaker.Faker;
import com.google.inject.Inject;
import com.jayway.restassured.response.ValidatableResponse;
import helpers.ContactObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.ArrayList;

import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 * Created by @v.matviichenko
 */
@Listeners({ReportAllureListenerImpl.class})
public class TestContactAPI extends TestBaseTNG {
    private final String ENDPOINTS_SOURCE = "http://host:port/api/v1/contacts/";
    private Faker faker = new Faker();

    @Inject
    private ContactAPI apiEndpoints;

    @BeforeClass
    public void setUp() {
        clearSetUp();
    }

    @Test
    public void testHealthCheck() {
        apiEndpoints.getHealthCheck().statusCode(200).assertThat().body(equalTo("live"));
    }

    @Test
    public void testCreateContact() {
        //GIVEN
        ContactObject contact = new ContactObject(faker.name().firstName(), faker.name().lastName());

        //WHEN

        //THEN
        apiEndpoints.createContact(contact.getRequestBody())
                .statusCode(201)
                .body("data.id[0]", is(greaterThanOrEqualTo(0)))
                .body("data.info.email[0]", is(contact.getEmail()))
                .body("data.info.firstName[0]", is(contact.getFirstName()))
                .body("data.info.lastName[0]", is(contact.getLastName()));
    }

    @Test
    public void testGetContactById() {
        //GIVEN
        ContactObject contact = new ContactObject(faker.name().firstName(), faker.name().lastName());

        //WHEN
        Integer contactId = getContactID(contact);
        contact.setId(contactId);

        //THEN
        assertEndpointResponse(apiEndpoints.getContactById(contactId), 200, contact);
    }

    @Test
    public void testFindContact() {
        //GIVEN
        String firstName = faker.name().firstName();
        ContactObject contact = new ContactObject(firstName, faker.name().lastName());

        //WHEN
        Integer contactId = getContactID(contact);
        contact.setId(contactId);

        //THEN
        assertEndpointResponse(apiEndpoints.findContact(firstName, contact.getEmail()), 200, contact);
    }

    @Test
    public void testUpdateContact() {
        //GIVEN
        ContactObject contact = new ContactObject(faker.name().firstName(), faker.name().lastName());

        //WHEN
        Integer contactId = getContactID(contact);
        contact.setId(contactId);

        //THEN
        assertEndpointResponse(apiEndpoints.updateContact(contact.getRequestBody(), contactId), 200, contact);

        apiEndpoints.deleteContact(contactId).statusCode(200);
        apiEndpoints.getContactById(contactId).statusCode(404);
    }

    private void assertEndpointResponse(ValidatableResponse response, Integer status, ContactObject contact) {
        response.statusCode(status)
                .body("data.id[0]", is(contact.getId()))
                .body("data.info.email[0]", is(contact.getEmail()))
                .body("data.info.firstName[0]", is(contact.getFirstName()))
                .body("data.info.lastName[0]", is(contact.getLastName()))
                .body("data.refs.patch[0]", is(ENDPOINTS_SOURCE + contact.getId()))
                .body("data.refs.get[0]", is(ENDPOINTS_SOURCE + contact.getId()))
                .body("data.refs.delete[0]", is(ENDPOINTS_SOURCE + contact.getId()))
                .body("data.refs.put[0]", is(ENDPOINTS_SOURCE + contact.getId()))
                .body("status", is(200));
    }

    private void clearSetUp() {
        ArrayList<Integer> contactIDs = apiEndpoints.getContacts().statusCode(200).extract().jsonPath().get("data.id");
        if(!contactIDs.isEmpty()) {
            for (Integer contactId : contactIDs) {
                apiEndpoints.deleteContact(contactId).statusCode(200);
            }
        }
        LOGGER.info("List contact is empty");
    }

    private Integer getContactID(ContactObject contact) {
        return apiEndpoints.createContact(contact.getRequestBody())
                .statusCode(201)
                .extract().jsonPath()
                .getInt("data.id[0]");
    }
}
