package ui.pages;

import base.core.TestProperties;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Random;

/**
 * Created by @v.matviichenko
 */
public class MainPage {
    private WebDriver driver;
    private TestProperties properties = TestProperties.getInstance();

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public void waitMainPageAppear() {
        new WebDriverWait(driver,10).until(ExpectedConditions.visibilityOfElementLocated(By.className("content")));
    }

    public CheckoutPage clickOnCart() {
        driver.findElement(By.id("cart-wrapper")).click();
        return new CheckoutPage(driver);
    }

    public List<WebElement> getProductList() {
        WebElement productContent  = driver.findElement(By.className("content"));
        List<WebElement> listProduct = productContent.findElements(By.xpath("//ul[@class='listing-wrapper products']/li"));
        return listProduct;
    }

    public ProductInfoCard clickOnProduct(String productName) {
        waitMainPageAppear();
        for (WebElement product : getProductList()) {
            String name = product.findElement(By.className("name")).getText();
            if(name.equals(productName)) {
                product.click();
                return new ProductInfoCard(driver);
            }
        }
        return null;
    }

    public void openMainPage() {
        driver.get(properties.getServerProperty("ui.admin.url"));
    }

    public ProductInfoCard clickOnRandomProduct() {
        waitMainPageAppear();
        WebElement randomWebElement = getProductList().get(new Random().nextInt(getProductList().size()));
        randomWebElement.click();
        return new ProductInfoCard(driver);
    }
}
