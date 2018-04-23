package tests.ui;

import base.core.Browser;
import base.core.Listner;
import base.core.ResourceUtil;
import base.core.TestBaseTNG;
import com.google.inject.Inject;
import net.bytebuddy.utility.RandomString;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ui.pages.CatalogPage;
import ui.pages.CheckoutPage;
import ui.pages.LeftMenu;
import ui.pages.LoginPage;
import ui.pages.MainPage;

import java.io.File;
import java.util.Map;

/**
 * Created by @v.matviichenko
 */
public class TestHomeWork extends TestBaseTNG {
    private static final File IMAGE_PATH = ResourceUtil.getResourceFile("files/automation.png");
    private static String PROJECT_URL;
    private static String PRODUCT_NAME;
    private static EventFiringWebDriver driver;
    private static LeftMenu leftMenu;
    private static CatalogPage catalogMainPage;
    private static MainPage mainPage;

    @Inject
    private Browser browser;

    @BeforeClass(alwaysRun = true)
    public void beforeClass() {
        PROJECT_URL = properties.getServerProperty("ui.admin.url");
        PRODUCT_NAME = new RandomString(8).nextString();

        driver = new EventFiringWebDriver(browser.getChromeDriver());
        driver.register(new Listner());

        driver.get(PROJECT_URL + "/admin");
        leftMenu = new LeftMenu(driver);
        mainPage = new MainPage(driver);
    }

    @Test(groups = {"ui"})
    public void testLoginLogoutUser() {
        new LoginPage(driver).loginAsAdmin(
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

    @Test(groups = {"ui"}, dependsOnMethods = "testIsPresentElementOnPage")
    public void testCreateNewProduct() {
        String productDescription = "Automation" + "-" + PRODUCT_NAME;

        catalogMainPage = leftMenu.clickOnCatalog("Catalog").clickOnAddNewProduct()
                .addProductInfo(PRODUCT_NAME, IMAGE_PATH, productDescription, 35);

        Assert.assertTrue(catalogMainPage.isProductAdd(PRODUCT_NAME), "Can not find product");
    }

    @Test(groups = {"ui"}, dependsOnMethods = "testCreateNewProduct")
    public void testAddProductToCart() {
        driver.get(PROJECT_URL);

        mainPage.clickOnProduct(PRODUCT_NAME)
                .clickOnAddToCart(1)
                .clickOnRandomProduct()
                .clickOnAddToCart(2)
                .clickOnCart()
                .removeProductFromCart();

        Assert.assertTrue(new CheckoutPage(driver).emptyCart(), "Products are not deleted from cart");
    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        driver.quit();
    }
}
