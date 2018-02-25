package tests;

import base.APIEndpoints;
import org.testng.annotations.Test;

import static org.hamcrest.core.IsEqual.equalTo;

/**
 * Created by @v.matviichenko
 */
public class TestCoverAPIEndpoints {
    APIEndpoints apiEndpoints = new APIEndpoints();

    @Test
    public void testBaseAPIDescription() {
        apiEndpoints.getHealthCheck(8182).statusCode(200).assertThat().body(equalTo("live"));
    }
}
