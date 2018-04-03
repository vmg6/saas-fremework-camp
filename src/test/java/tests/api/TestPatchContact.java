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
import org.hamcrest.MatcherAssert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.HashMap;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.core.Is.is;

/**
 * Created by @v.matviichenko
 */
@Listeners({ReportAllureListenerImpl.class})
public class TestPatchContact extends TestBaseTNG {
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

        validatableResponse = apiEndpoints.createContact(contactData.getRequestBody()).statusCode(HttpStatus.SC_CREATED);
        contactId = contactService.getContactId(validatableResponse);
    }

    @Test(groups = {"rest-api"})
    public void testPatchEmail() {
        // Arrange
        String email = faker.internet().emailAddress();

        // Act
        HashMap<String, String> response = apiEndpoints.patchContact("email", email, contactId)
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .jsonPath()
                .get("data[0].info");

        // Assert
        MatcherAssert.assertThat(email, is(response.get("email")));
        MatcherAssert.assertThat(response.get("firstName"), is(notNullValue()));
        MatcherAssert.assertThat(response.get("lastName"), is(notNullValue()));
    }

    @Test(groups = {"rest-api"})
    public void testPatchFirstName() {
        // Arrange
        String firstName = faker.name().firstName();

        // Act
        HashMap<String, String> response = apiEndpoints.patchContact("firstName", firstName, contactId)
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .jsonPath()
                .get("data[0].info");

        // Assert
        MatcherAssert.assertThat(firstName, is(response.get("firstName")));
        MatcherAssert.assertThat(response.get("email"), is(notNullValue()));
        MatcherAssert.assertThat(response.get("lastName"), is(notNullValue()));
    }

    @Test(groups = {"rest-api"})
    public void testPatchLastName() {
        // Arrange
        String lastName = faker.name().lastName();

        // Act
        HashMap<String, String> response = apiEndpoints.patchContact("lastName", lastName, contactId)
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .jsonPath()
                .get("data[0].info");

        // Assert
        MatcherAssert.assertThat(lastName, is(response.get("lastName")));
        MatcherAssert.assertThat(response.get("email"), is(notNullValue()));
        MatcherAssert.assertThat(response.get("firstName"), is(notNullValue()));
    }

    @Test(groups = {"rest-api"}, enabled = false)
    public void testPatchEmailNegativeCase() {
        // Arrange
        String email = faker.name().firstName() + faker.name().lastName();

        // Assert
        apiEndpoints.patchContact("email", email, contactId)
                .statusCode(HttpStatus.SC_NOT_FOUND);
    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        apiEndpoints.deleteContact(contactId).statusCode(HttpStatus.SC_OK);
    }
}
