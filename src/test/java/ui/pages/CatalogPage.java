package ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Created by @v.matviichenko
 */
public class CatalogPage {
    private WebDriver driver;

    public CatalogPage(WebDriver driver) {
        this.driver = driver;
    }

    public AddProductPage clickOnAddNewProduct() {
        driver.findElement(By.cssSelector(".button:nth-of-type(2)")).click();
        return new AddProductPage(driver);
    }

    public boolean isProductAdd(String productName) {
        WebElement tableCatalog  = driver.findElement(By.className("dataTable"));
        List<WebElement> catalogItems = tableCatalog.findElements(By.className("row"));
        for (WebElement catalogItem : catalogItems) {
            if (new String(catalogItem.getAttribute("outerText").trim()).equals(productName)) {
                return true;
            }
        }
        return false;
    }
}
