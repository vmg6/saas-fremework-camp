package tests;

import base.controller.ContactAPI;
import base.core.TestBaseTNG;
import base.listners.ReportAllureListenerImpl;
import com.github.javafaker.Faker;
import com.google.inject.Inject;
import com.jayway.restassured.response.ValidatableResponse;
import helpers.ContactObject;
import helpers.ContactService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.ArrayList;

/**
 * Created by @v.matviichenko
 */
@Listeners({ReportAllureListenerImpl.class})
public class TestCreateContact extends TestBaseTNG {
    private Faker faker = new Faker();

    @Inject
    private ContactAPI apiEndpoints;

    @Inject
    private ContactService contactService;

    @BeforeClass
    public void setUp() {
        clearSetUp();
    }

    @Test
    public void testCreateContactPositive() {
        //GIVEN
        ContactObject contact = new ContactObject(faker.name().firstName(), faker.name().lastName());

        //WHEN
        ValidatableResponse validatableResponse = apiEndpoints.createContact(contact.getRequestBody());

        //THEN
        validatableResponse.statusCode(201);
        contactService.verifyContactBody(validatableResponse, contact);
    }

    @Test
    public void testGetContactById() {
        //GIVEN
        ContactObject contact = new ContactObject(faker.name().firstName(), faker.name().lastName());

        //WHEN
        Integer contactId = contactService.createNewContactGetId(contact);
        contact.setId(contactId);
        ValidatableResponse validatableResponse = apiEndpoints.getContactById(contactId);

        //THEN
        validatableResponse.statusCode(200);
        contactService.verifyContactBody(validatableResponse, contact);
    }

    @Test
    public void testFindContact() {
        //GIVEN
        String firstName = faker.name().firstName();
        ContactObject contact = new ContactObject(firstName, faker.name().lastName());

        //WHEN
        Integer contactId = contactService.createNewContactGetId(contact);
        contact.setId(contactId);
        ValidatableResponse validatableResponse = apiEndpoints.findContact(firstName, contact.getEmail());

        //THEN
        validatableResponse.statusCode(200);
        contactService.verifyContactBody(validatableResponse, contact);
    }

    @Test
    public void testUpdateContact() {
        //GIVEN
        ContactObject contact = new ContactObject(faker.name().firstName(), faker.name().lastName());

        //WHEN
        Integer contactId = contactService.createNewContactGetId(contact);
        contact.setId(contactId);
        ValidatableResponse validatableResponse = apiEndpoints.updateContact(contact.getRequestBody(), contactId);

        //THEN
        validatableResponse.statusCode(200);
        contactService.verifyContactBody(validatableResponse, contact);

        apiEndpoints.deleteContact(contactId).statusCode(200);
        apiEndpoints.getContactById(contactId).statusCode(404);
    }

    private void clearSetUp() {
        ArrayList<Integer> contactIDs = apiEndpoints.getContacts().statusCode(200).extract().jsonPath().get("data.id");
        if(!contactIDs.isEmpty()) {
            for (Integer contactId : contactIDs) {
                apiEndpoints.deleteContact(contactId).statusCode(200);
            }
        }
    }
}
