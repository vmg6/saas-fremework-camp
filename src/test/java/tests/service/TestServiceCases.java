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
    }

    @Test(groups = {"service"})
    public void testServiceCaseFirst() {
        int listContactIds = contactService.getContactsNumber();

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
        Assert.assertEquals(contactService.getContactsNumber(), listContactIds + 1);

        //delete contact
        apiEndpoints.deleteContact(contactId).statusCode(HttpStatus.SC_OK);
        Assert.assertEquals(contactService.getContactsNumber(), listContactIds);

        //verify contact is not available
        apiEndpoints.getContactById(contactId).statusCode(HttpStatus.SC_NOT_FOUND);
    }

    @Test(groups = {"service"})
    public void testServiceCaseSecond() {
        int listContactIds = contactService.getContactsNumber();

        //obtain contact ID
        int contactId = apiEndpoints.createContact(contactData.getRequestBody())
                .statusCode(HttpStatus.SC_CREATED)
                .extract()
                .jsonPath()
                .get("data.id[0]");

        MatcherAssert.assertThat(contactId, is(greaterThanOrEqualTo(0)));

        //verify contacts list length is +1
        Assert.assertEquals(listContactIds + 1, contactService.getContactsNumber(), "Number of contacts are not equal");

        //delete contact
        apiEndpoints.deleteContact(contactId).statusCode(200);

        //verify contacts list length is -1
        Assert.assertEquals(contactId, contactService.getContactsNumber());
    }

}
