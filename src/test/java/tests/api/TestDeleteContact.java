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
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

/**
 * Created by @v.matviichenko
 */
@Listeners({ReportAllureListenerImpl.class})
public class TestDeleteContact extends TestBaseTNG {
    private Faker faker = new Faker();
    private ContactData contactData;
    private ValidatableResponse validatableResponse;
    private Integer contactId;

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

        validatableResponse = apiEndpoints.createContact(contactData.getRequestBody());
        contactId = contactService.getContactId(validatableResponse);
    }

    @Test(groups = {"rest-api"})
    public void testDeleteContact() {
        // Act
        ValidatableResponse response = apiEndpoints.deleteContact(contactId);

        // Assert
        contactService.verifyContactBody(response, HttpStatusCodes.SUCCESS_200.getCode(), contactData);
    }

    @Test(groups = {"rest-api"})
    public void testIsContactDeleted() {
        // Assert
        apiEndpoints.getContactById(contactId).statusCode(HttpStatusCodes.CLIENT_ERROR_404.getCode());
    }
}
