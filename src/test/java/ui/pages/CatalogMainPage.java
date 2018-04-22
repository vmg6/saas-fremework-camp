package ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by @v.matviichenko
 */
public class CatalogMainPage {
    private WebDriver driver;

    public CatalogMainPage(WebDriver driver) {
        this.driver = driver;
    }

    public AddProductPage clickOnAddNewProduct() {
        driver.findElement(By.cssSelector(".button:nth-of-type(2)")).click();
        return new AddProductPage(driver);
    }
}
