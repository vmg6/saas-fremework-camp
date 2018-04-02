package tests.service;

import base.controller.ContactsAPI;
import base.core.TestBaseTNG;
import base.listners.ReportAllureListenerImpl;
import com.github.javafaker.Faker;
import com.google.inject.Inject;
import helpers.ContactData;
import helpers.ContactService;
import org.apache.http.HttpStatus;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.number.OrderingComparison.greaterThanOrEqualTo;

/**
 * Created by @v.matviichenko
 */
@Listeners({ReportAllureListenerImpl.class})
public class TestServiceCases extends TestBaseTNG {
    private Faker faker = new Faker();
    private ContactData contactData;
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
    }

    @Test(groups = {"service"})
    public void testServiceCaseFirst() {
        //obtain contact ID
        Integer contactId = apiEndpoints.createContact(contactData.getRequestBody())
                .statusCode(HttpStatus.SC_CREATED)
                .extract()
                .jsonPath()
                .get("data.id[0]");

        MatcherAssert.assertThat(contactId, is(greaterThanOrEqualTo(0)));

        //verify expected contact details returned
        contactService.verifyContactBody(apiEndpoints.getContactById(contactId), HttpStatus.SC_OK, contactData);

        //verify just created contact is listed
        Assert.assertEquals(contactService.getContactsNumber(), contactsNumber + 1);

        //delete contact
        apiEndpoints.deleteContact(contactId).statusCode(HttpStatus.SC_OK);
        Assert.assertEquals(contactService.getContactsNumber(), contactsNumber);

        //verify contact is not available
        apiEndpoints.getContactById(contactId).statusCode(HttpStatus.SC_NOT_FOUND);
    }

}
