package tests;

import base.controller.APIEndpoints;
import base.core.TestBaseTNG;
import com.google.inject.Inject;
import maintanance_objects.User;
import org.testng.annotations.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 * Created by @v.matviichenko
 */
public class TestCoverAPIEndpoints extends TestBaseTNG{
    private static final String FIRST_NAME = "Homer";
    private static final String LAST_NAME = "Simpson";
    private static final String EMAIL = FIRST_NAME + "." + LAST_NAME + "@gmail.com";
    private static final String ENDPOINTS_SOURCE = "http://host:port/api/v1/contacts/";

    @Inject
    private APIEndpoints apiEndpoints;

    @Test
    public void testBaseAPIDescription() {
        apiEndpoints.getHealthCheck().statusCode(200).assertThat().body(equalTo("live"));
    }

    @Test
    public void testCreateContact() {
        User user = new User(FIRST_NAME, LAST_NAME);
        Integer userId = apiEndpoints.createContact(user.getCreateBodyRequest())
                .statusCode(201)
                .extract().jsonPath()
                .getInt("data.id[0]");

        apiEndpoints.getContactById(userId)
                .statusCode(200)
                .body("data.id[0]", is(userId))
                .body("data.info.email[0]", is(EMAIL))
                .body("data.info.firstName[0]", is(FIRST_NAME))
                .body("data.info.lastName[0]", is(LAST_NAME))
                .body("data.refs.patch[0]", is(ENDPOINTS_SOURCE + userId))
                .body("data.refs.get[0]", is(ENDPOINTS_SOURCE + userId))
                .body("data.refs.delete[0]", is(ENDPOINTS_SOURCE + userId))
                .body("data.refs.put[0]", is(ENDPOINTS_SOURCE + userId))
                .body("status", is(200));

        apiEndpoints.deleteContact(userId)
                .statusCode(200)
                .body("data.id[0]", is(userId))
                .body("data.info.email[0]", is(EMAIL))
                .body("data.info.firstName[0]", is(FIRST_NAME))
                .body("data.info.lastName[0]", is(LAST_NAME))
                .body("data.refs.patch[0]", is(ENDPOINTS_SOURCE + userId))
                .body("data.refs.get[0]", is(ENDPOINTS_SOURCE + userId))
                .body("data.refs.delete[0]", is(ENDPOINTS_SOURCE + userId))
                .body("data.refs.put[0]", is(ENDPOINTS_SOURCE + userId))
                .body("status", is(200));

        apiEndpoints.getContactById(userId).statusCode(404);
    }

}
