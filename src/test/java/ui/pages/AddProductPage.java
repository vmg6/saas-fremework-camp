package ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.io.File;

/**
 * Created by @v.matviichenko
 */
public class AddProductPage {
    private WebDriver driver;

    public AddProductPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickOnInformationTab() {
        driver.findElement(By.xpath("//a[@href='#tab-information']")).click();
    }

    public void clickOnPriceTab() {
        driver.findElement(By.xpath("//a[@href='#tab-price']")).click();
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

    public CatalogMainPage addProductInfo(String productName, File imagePath) {
        setProductName(productName);
        uploadImage(imagePath);
        clickOnSaveButton();
        return new CatalogMainPage(driver);
    }
}
