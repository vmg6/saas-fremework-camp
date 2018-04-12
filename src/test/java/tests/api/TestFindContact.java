package tests.api;

import base.controller.ContactsAPI;
import base.core.TestBaseTNG;
import com.github.javafaker.Faker;
import com.google.inject.Inject;
import com.jayway.restassured.response.ValidatableResponse;
import helpers.ContactData;
import helpers.ContactService;
import org.apache.http.HttpStatus;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Created by @v.matviichenko
 */
public class TestFindContact extends TestBaseTNG {
    private Faker faker = new Faker();
    private ContactData contactData;

    @Inject
    private ContactsAPI apiEndpoints;

    @Inject
    private ContactService contactService;

    @BeforeClass(alwaysRun = true)
    public void beforeClass() {
        contactData = new ContactData(
                faker.name().firstName(),
                faker.name().lastName(),
                faker.internet().emailAddress()
        );

        apiEndpoints.createContact(contactData.getRequestBody()).statusCode(HttpStatus.SC_CREATED);
    }

    @Test(groups = {"rest-api"})
    public void testFindContact() {
        ValidatableResponse response = apiEndpoints.findContact(contactData.getFirstName(), contactData.getEmail());
        contactService.verifyContactBody(response, HttpStatus.SC_OK, contactData);
    }
}
