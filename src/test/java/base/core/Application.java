package base.core;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import net.bytebuddy.utility.RandomString;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import ui.pages.AddProductPage;
import ui.pages.CatalogPage;
import ui.pages.CheckoutPage;
import ui.pages.LeftMenu;
import ui.pages.LoginPage;
import ui.pages.MainPage;
import ui.pages.ProductInfoCard;

import java.util.HashMap;

/**
 * Created by @v.matviichenko
 */
public class Application {
    private final String productName = new RandomString(8).nextString();
    private EventFiringWebDriver driver;

    private LoginPage loginPage;
    private LeftMenu leftMenu;
    private CatalogPage catalogPage;
    private AddProductPage addProductPage;
    private MainPage mainPage;
    private ProductInfoCard productInfoCard;
    private CheckoutPage checkoutPage;

    public Application() {
        ChromeDriverManager.getInstance().setup();
        driver = new EventFiringWebDriver(new ChromeDriver());
        driver.register(new Listner());

        driver.manage().window().maximize();

        loginPage = new LoginPage(driver);
        leftMenu = new LeftMenu(driver);
        catalogPage = new CatalogPage(driver);
        addProductPage = new AddProductPage(driver);
        mainPage = new MainPage(driver);
        checkoutPage = new CheckoutPage(driver);
        productInfoCard = new ProductInfoCard(driver);
    }

    public void quit() {
        driver.quit();
    }

    public void openAdminPage() {
        loginPage.openPage();
        loginPage.waitUntilFormAppear();
        loginPage.setUsername();
        loginPage.setPassword();
        loginPage.clickOnLogin();
    }

    public void addNewProductToCatalog() {
        leftMenu.clickOnCatalog();
        catalogPage.clickOnAddNewProduct();
        addProductPage.setProductStatus();
        addProductPage.setProductName(productName);
        addProductPage.uploadImage();
        addProductPage.clickOnInformationTab();
        addProductPage.setDescription(productName);
        addProductPage.setHeadTitle(productName);
        addProductPage.clickOnPriceTab();
        addProductPage.setProductPrice();
        addProductPage.clickOnSaveButton();
    }

    public void openMainPage() {
        mainPage.openMainPage();
    }

    public void addNewProductToCart() {
        mainPage.clickOnProduct(productName);
        productInfoCard.clickOnAddToCart(1);
    }

    public void addRandomProductToCart() {
        mainPage.clickOnRandomProduct();
        productInfoCard.clickOnAddToCart(2);
    }

    public void openCart() {
        mainPage.clickOnCart();
    }

    public void removeAllProductsFromCart() {
        checkoutPage.removeProductFromCart();
    }

    public boolean isEmptyCart() {
        return checkoutPage.emptyCart();
    }

    public boolean isProductAdd() {
        return catalogPage.isProductAdd(productName);
    }

    public HashMap<String, Boolean> getElementOnPage() {
        return leftMenu.checkTagOnPage();
    }
}
