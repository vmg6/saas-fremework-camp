package tests.ui;

import base.core.Browser;
import base.core.TestBaseTNG;
import com.google.inject.Inject;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ui.pages.AdminPage;
import ui.pages.LoginPage;

/**
 * Created by @v.matviichenko
 */
public class TestLogin extends TestBaseTNG {
    private static WebDriver driver;
    private static LoginPage loginPage;
    private static AdminPage adminPage;

    @Inject
    private Browser browser;

    @BeforeClass
    public void beforeClass() {
        driver = browser.getChromeDriver();
        driver.get(properties.getServerProperty("ui.admin.url"));
        loginPage = new LoginPage(driver);
        adminPage = new AdminPage(driver);
    }

    @Test
    public void testLoginLogoutUser() {
        loginPage.waitUntilFormAppear();
        loginPage.setUsername(properties.getServerProperty("username"));
        loginPage.setPassword(properties.getServerProperty("password"));
        loginPage.clickOnLogin();

        Assert.assertTrue(adminPage.isMenuPresent());
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
