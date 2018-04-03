package tests.api;

import base.controller.ContactsAPI;
import base.core.TestBaseTNG;
import base.listners.ReportAllureListenerImpl;
import com.github.javafaker.Faker;
import com.google.inject.Inject;
import com.jayway.restassured.response.ValidatableResponse;
import helpers.ContactData;
import helpers.ContactService;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

/**
 * Created by @v.matviichenko
 */
@Listeners({ReportAllureListenerImpl.class})
public class TestCreateContact extends TestBaseTNG {
    private Faker faker = new Faker();
    private ContactData contactData;
    private ValidatableResponse validatableResponse;
    private int contactsNumber;

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

        contactsNumber = contactService.getContactsNumber();
        validatableResponse = apiEndpoints.createContact(contactData.getRequestBody());
    }

    @Test(groups = {"rest-api"})
    public void testCreateContactPositive() {
        // Assert
        contactService.verifyContactID(validatableResponse);
        contactService.verifyContactBody(validatableResponse, HttpStatus.SC_CREATED, contactData);
    }

    @Test(groups = {"rest-api"})
    public void testIsContactCreatedPositive() {
        //Assert
        Assert.assertEquals(contactsNumber + 1, contactService.getContactsNumber());
    }

    @Test(groups = {"rest-api"}, enabled = false)
    public void  testCreateContactWithoutEmailNegative() {
        // Assert
        apiEndpoints.createContact(contactData.getRequestBodyWithoutEmail())
                .statusCode(HttpStatus.SC_BAD_REQUEST);
    }

    @Test(groups = {"rest-api"}, enabled = false)
    public void  testCreateContactWithoutFirstNameNegative() {
        //Assert
        apiEndpoints.createContact(contactData.getRequestBodyWithoutFirstName())
                .statusCode(HttpStatus.SC_BAD_REQUEST);
    }

    @Test(groups = {"rest-api"}, enabled = false)
    public void  testCreateContactWithoutLastNameNegative() {
        // Assert
        apiEndpoints.createContact(contactData.getRequestBodyWithoutLastName())
                .statusCode(HttpStatus.SC_BAD_REQUEST);
    }

    @Test(groups = {"rest-api"}, enabled = false)
    public void  testCreateContactWithEmptyBodyNegative() {
        // Assert
        apiEndpoints.createContact(contactData.getRequestWithEmptyBody())
                .statusCode(HttpStatus.SC_BAD_REQUEST);
    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        apiEndpoints.deleteContact(contactService.getContactId(validatableResponse)).statusCode(HttpStatus.SC_OK);
    }
}
