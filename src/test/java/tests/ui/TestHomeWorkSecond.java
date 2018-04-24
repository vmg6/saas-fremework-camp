package tests.ui;

import base.core.TestBaseTNG;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import java.util.Map;

/**
 * Created by @v.matviichenko
 */
public class TestHomeWorkSecond extends TestBaseTNG {

    @Test(groups = {"ui"})
    public void testIsPresentElementOnPage() {
        app.openAdminPage();
        for (Map.Entry<String, Boolean> elements : app.getElementOnPage().entrySet()) {
            Assert.assertTrue(elements.getValue(), "Can not find element on page: "+ elements.getKey() +"");
        }
    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        app.quit();
    }
}
