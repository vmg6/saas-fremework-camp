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

import java.util.Map;

/**
 * Created by @v.matviichenko
 */
public class TestAdmin extends TestBaseTNG {
    private static WebDriver driver;
    private static LoginPage loginPage;
    private static AdminPage adminPage;

    @Inject
    private Browser browser;

    @BeforeClass(alwaysRun = true)
    public void beforeClass() {
        driver = browser.getChromeDriver();
        driver.get(properties.getServerProperty("ui.admin.url"));
        loginPage = new LoginPage(driver);
        adminPage = new AdminPage(driver);
    }

    @Test(groups = {"ui"})
    public void testLoginLogoutUser() {
        loginPage.loginAsAdmin(
                properties.getServerProperty("username"),
                properties.getServerProperty("password"));

        Assert.assertFalse(adminPage.getMenuItemsList().isEmpty(), "Can not login into");
    }

    @Test(groups = {"ui"}, dependsOnMethods = "testLoginLogoutUser")
    public void testIsPresentElementOnPage() {
        for (Map.Entry<String, Boolean> elements : adminPage.checkTagOnPage().entrySet()) {
            Assert.assertTrue(elements.getValue(), ""+ elements.getKey() +"");
        }
    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        driver.quit();
    }
}
