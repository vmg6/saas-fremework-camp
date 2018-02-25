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
    private static Integer USER_ID;

    @Inject
    private APIEndpoints apiEndpoints;

    @Test
    public void testBaseAPIDescription() {
        apiEndpoints.getHealthCheck().statusCode(200).assertThat().body(equalTo("live"));
    }

    @Test
    public void testCRUDOperation() {
        User user = new User(FIRST_NAME, LAST_NAME);

        USER_ID = apiEndpoints.createContact(user.getCreateBodyRequest()).extract().jsonPath().getInt("data.id[0]");

        apiEndpoints.getContactById(USER_ID)
                .statusCode(200)
                .body("data.id[0]", is(USER_ID))
                .body("data.info.email[0]", is(FIRST_NAME + "." + LAST_NAME + "@gmail.com"))
                .body("data.info.firstName[0]", is(FIRST_NAME))
                .body("data.info.lastName[0]", is(LAST_NAME));
    }
}
