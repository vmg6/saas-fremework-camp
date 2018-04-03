package tests.api;

import base.controller.ContactsAPI;
import base.core.TestBaseTNG;
import base.listners.ReportAllureListenerImpl;
import com.google.inject.Inject;
import org.apache.http.HttpStatus;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static org.hamcrest.core.IsEqual.equalTo;

/**
 * Created by @v.matviichenko
 */
@Listeners({ReportAllureListenerImpl.class})
public class TestHealthCheck extends TestBaseTNG {
    @Inject
    private ContactsAPI apiEndpoints;

    @Test(groups = {"rest-api"})
    public void testHealthCheck() {
        apiEndpoints.getHealthCheck()
                .statusCode(HttpStatus.SC_OK)
                .assertThat()
                .body(equalTo("live"));
    }
}
