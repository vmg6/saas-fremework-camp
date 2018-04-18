package tests.ui;

import base.core.Browser;
import base.core.TestBaseTNG;
import com.google.inject.Inject;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ui.pages.LoginPage;

/**
 * Created by @v.matviichenko
 */
public class TestLogin extends TestBaseTNG {
    private static WebDriver driver;

    @Inject
    private Browser browser;

    @BeforeClass
    public void beforeClass() {
        driver = browser.getChromeDriver();
        driver.get("http://localhost/litecart/admin/login.php");
    }

    @Test
    public void testLoginLogoutUser() {
        new LoginPage(driver);
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
