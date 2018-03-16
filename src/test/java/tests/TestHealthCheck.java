package tests;

import base.controller.ContactAPI;
import base.core.TestBaseTNG;
import base.listners.ReportAllureListenerImpl;
import com.google.inject.Inject;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static org.hamcrest.core.IsEqual.equalTo;

/**
 * Created by @v.matviichenko
 */
@Listeners({ReportAllureListenerImpl.class})
public class TestHealthCheck extends TestBaseTNG {
    @Inject
    private ContactAPI apiEndpoints;

    @Test
    public void testHealthCheck() {
        apiEndpoints.getHealthCheck().statusCode(200).assertThat().body(equalTo("live"));
    }
}
