package ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.File;
import java.util.List;

/**
 * Created by @v.matviichenko
 */
public class AddProductPage {
    private WebDriver driver;

    public AddProductPage(WebDriver driver) {
        this.driver = driver;
    }

    public void setProductStatus(String productStatus) {
        List<WebElement> labels = driver.findElements(By.xpath("//label"));
        for (WebElement status : labels) {
            if (new String(status.getAttribute("textContent").trim()).equals(productStatus)) {
                status.click();
            }
        }
    }

    public void clickOnInformationTab() {
        driver.findElement(By.xpath("//a[@href='#tab-information']")).click();
    }

    public void clickOnPriceTab() {
        driver.findElement(By.xpath("//a[@href='#tab-prices']")).click();
    }

    public void setProductName(String productName) {
        driver.findElement(By.xpath("//input[@name='name[en]']")).sendKeys(productName);
    }

    public void clickOnSaveButton() {
        driver.findElement(By.xpath("//button[@name='save']")).click();
    }

    public void uploadImage(File imagePath) {
        driver.findElement(By.xpath("//input[@name='new_images[]']")).sendKeys(imagePath.getAbsolutePath());
    }

    public void setDescription(String value) {
        driver.findElement(By.className("trumbowyg-editor")).sendKeys(value);
    }

    public void setHeadTitle(String value) {
        driver.findElement(By.xpath("//input[@name='head_title[en]']")).sendKeys(value);
    }

    public void setProductPrice(double value) {
        driver.findElement(By.xpath("//input[@name='prices[USD]']")).sendKeys(String.valueOf(value));
    }

    public CatalogPage addProductInfo(String productName, File imagePath, String productDescription, double productPrice) {
        setProductStatus("Enabled");
        setProductName(productName);
        uploadImage(imagePath);
        clickOnInformationTab();
        setDescription(productDescription);
        setHeadTitle(productDescription);
        clickOnPriceTab();
        setProductPrice(productPrice);

        clickOnSaveButton();
        return new CatalogPage(driver);
    }
}
