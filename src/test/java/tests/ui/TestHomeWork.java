package tests.ui;

import base.core.Browser;
import base.core.ResourceUtil;
import base.core.TestBaseTNG;
import com.google.inject.Inject;
import net.bytebuddy.utility.RandomString;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ui.pages.LeftMenu;
import ui.pages.LoginPage;

import java.io.File;
import java.util.Map;

/**
 * Created by @v.matviichenko
 */
public class TestHomeWork extends TestBaseTNG {
    private static final File IMAGE_PATH = ResourceUtil.getResourceFile("files/automation.png");
    private static final String PRODUCT_NAME = new RandomString(8).nextString();
    private static WebDriver driver;
    private static LoginPage loginPage;
    private static LeftMenu leftMenu;

    @Inject
    private Browser browser;

    @BeforeClass(alwaysRun = true)
    public void beforeClass() {
        driver = browser.getChromeDriver();
        driver.get(properties.getServerProperty("ui.admin.url"));
        loginPage = new LoginPage(driver);
        leftMenu = new LeftMenu(driver);
    }

    @Test(groups = {"ui"})
    public void testLoginLogoutUser() {
        loginPage.loginAsAdmin(
                properties.getServerProperty("username"),
                properties.getServerProperty("password"));

        Assert.assertFalse(leftMenu.getMenuItemsList().isEmpty(), "Can not login into");
    }

    @Test(groups = {"ui"}, dependsOnMethods = "testLoginLogoutUser")
    public void testIsPresentElementOnPage() {
        for (Map.Entry<String, Boolean> elements : leftMenu.checkTagOnPage().entrySet()) {
            Assert.assertTrue(elements.getValue(), "Can not find element on page: "+ elements.getKey() +"");
        }
    }

    @Test(groups = {"ui"}, dependsOnMethods = "testLoginLogoutUser")
    public void testCartOperation() {

        leftMenu.clickOnCatalog("Catalog")
                .clickOnAddNewProduct()
                .addProductInfo(PRODUCT_NAME, IMAGE_PATH);
    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        driver.quit();
    }
}
