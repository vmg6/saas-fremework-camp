package helpers;

import base.controller.ContactAPI;
import com.google.inject.Inject;
import com.jayway.restassured.response.ValidatableResponse;

import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.core.Is.is;

/**
 * Created by @v.matviichenko
 */
public class ContactService {
    ContactAPI contactAPI = new ContactAPI();

    @Inject
    public ContactService() {

    }

    public void verifyContactBody(ValidatableResponse response, ContactObject contact) {
        response.body("data.id[0]", is(greaterThanOrEqualTo(0)))
                .body("data.info.email[0]", is(contact.getEmail()))
                .body("data.info.firstName[0]", is(contact.getFirstName()))
                .body("data.info.lastName[0]", is(contact.getLastName()));
    }

    public Integer createNewContactGetId(ContactObject contact) {
        return contactAPI.createContact(contact.getRequestBody())
                .statusCode(201)
                .extract().jsonPath()
                .getInt("data.id[0]");
    }
}
