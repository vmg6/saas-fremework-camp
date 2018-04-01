package helpers;

import base.controller.ContactsAPI;
import base.references.HttpStatusCodes;
import com.google.inject.Inject;
import com.jayway.restassured.response.ValidatableResponse;
import org.hamcrest.MatcherAssert;

import java.util.HashMap;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.number.OrderingComparison.greaterThanOrEqualTo;

/**
 * Created by @v.matviichenko
 */
public class ContactService {
    ContactsAPI contactAPI = new ContactsAPI();

    @Inject
    public ContactService() {

    }

    public void verifyContactBody(ValidatableResponse response, Integer statusCode, ContactData contact) {
        HashMap<String, String> data = response.statusCode(statusCode)
                .extract()
                .jsonPath()
                .get("data[0].info");

        MatcherAssert.assertThat(contact.getFirstName(), is(data.get("firstName")));
        MatcherAssert.assertThat(contact.getLastName(), is(data.get("lastName")));
        MatcherAssert.assertThat(contact.getEmail(), is(data.get("email")));
    }

    public void verifyContactID(ValidatableResponse response) {
        MatcherAssert.assertThat(getContactId(response), is(greaterThanOrEqualTo(0)));
    }

    public Integer getContactId(ValidatableResponse response) {
        return response.statusCode(HttpStatusCodes.SUCCESS_201.getCode()).extract().jsonPath().get("data.id[0]");
    }

    public int getContactsNumber() {
        return contactAPI.getContacts().statusCode(HttpStatusCodes.SUCCESS_200.getCode())
                .extract()
                .jsonPath()
                .getList("data.id").size();
    }

    public Integer createNewContactGetId(ContactData contact) {
        return contactAPI.createContact(contact.getRequestBody())
                .statusCode(HttpStatusCodes.SUCCESS_201.getCode())
                .extract().jsonPath()
                .getInt("data.id[0]");
    }
}
