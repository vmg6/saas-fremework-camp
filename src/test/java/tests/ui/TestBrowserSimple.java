package tests.ui;

import base.core.Browser;
import base.core.TestBaseTNG;
import com.google.inject.Inject;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

/**
 * Created by @v.matviichenko
 */
public class TestBrowserSimple extends TestBaseTNG {
    private final String URL =  properties.getServerProperty("url_test");
    private static WebDriver driver;

    @Inject
    private Browser browser;

    @Test(groups = {"ui"})
    public void testChrome() {
        driver = browser.getChromeDriver();
        driver.get(URL);
        driver.quit();
    }

    @Test(groups = {"ui"})
    public void testFireFox() {
        driver = browser.getFirefoxDriver();
        driver.get(URL);
        driver.quit();
    }

    @Test(groups = {"ui"}, enabled = false)
    public void testEdge() {
        driver = browser.getEdgeDriver();
        driver.get(URL);
        driver.quit();
    }
}
