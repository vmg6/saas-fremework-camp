package tests.api;

import base.controller.ContactsAPI;
import base.core.TestBaseTNG;
import base.listners.ReportAllureListenerImpl;
import base.references.HttpStatusCodes;
import com.github.javafaker.Faker;
import com.google.inject.Inject;
import com.jayway.restassured.response.ValidatableResponse;
import helpers.ContactData;
import helpers.ContactService;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

/**
 * Created by @v.matviichenko
 */
@Listeners({ReportAllureListenerImpl.class})
public class TestUpdateContact extends TestBaseTNG {
    private Faker faker = new Faker();
    private ContactData contactData;
    private ValidatableResponse validatableResponse;
    private Integer contactId;

    @Inject
    private ContactsAPI apiEndpoints;

    @Inject
    private ContactService contactService;

    @BeforeClass
    public void beforeClass() {
        contactData = new ContactData(
                faker.name().firstName(),
                faker.name().lastName(),
                faker.internet().emailAddress()
        );

        validatableResponse = apiEndpoints.createContact(contactData.getRequestBody());
        contactId = contactService.getContactId(validatableResponse);
    }

    @Test(groups = {"rest-api"})
    public void testUpdateContact() {
        // Arrange
        contactData.setFirstName(faker.name().firstName());
        contactData.setLastName(faker.name().lastName());
        contactData.setEmail(faker.internet().emailAddress());

        // Act
        ValidatableResponse responseUpdate = apiEndpoints.updateContact(contactData.getRequestBody(), contactId);
        ValidatableResponse responseGet = apiEndpoints.getContactById(contactId);

        // Assert
        contactService.verifyContactBody(responseUpdate, HttpStatusCodes.SUCCESS_200.getCode(), contactData);
        contactService.verifyContactBody(responseGet, HttpStatusCodes.SUCCESS_200.getCode(), contactData);
    }

    @AfterClass
    public void afterClass() {
        apiEndpoints.deleteContact(contactId).statusCode(200);
    }
}
