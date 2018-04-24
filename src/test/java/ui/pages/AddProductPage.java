package ui.pages;

import base.core.ResourceUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.File;
import java.util.List;

/**
 * Created by @v.matviichenko
 */
public class AddProductPage {
    private final File imagePath = ResourceUtil.getResourceFile("files/automation.png");
    private WebDriver driver;

    By informationTab = By.xpath("//a[@href='#tab-information']");
    By priceTab = By.xpath("//a[@href='#tab-prices']");
    By productName = By.xpath("//input[@name='name[en]']");
    By saveButton = By.xpath("//button[@name='save']");
    By image = By.xpath("//input[@name='new_images[]']");
    By description = By.className("trumbowyg-editor");
    By title = By.xpath("//input[@name='head_title[en]']");
    By price = By.xpath("//input[@name='prices[USD]']");

    public AddProductPage(WebDriver driver) {
        this.driver = driver;
    }

    public void setProductStatus() {
        List<WebElement> labels = driver.findElements(By.xpath("//label"));
        for (WebElement status : labels) {
            if (new String(status.getAttribute("textContent").trim()).equals("Enabled")) {
                status.click();
            }
        }
    }

    public void clickOnInformationTab() {
        driver.findElement(informationTab).click();
    }

    public void clickOnPriceTab() {
        driver.findElement(priceTab).click();
    }

    public void setProductName(String value) {
        driver.findElement(productName).sendKeys(value);
    }

    public void clickOnSaveButton() {
        driver.findElement(saveButton).click();
    }

    public void uploadImage() {
        driver.findElement(image).sendKeys(imagePath.getAbsolutePath());
    }

    public void setDescription(String value) {
        driver.findElement(description).sendKeys(value);
    }

    public void setHeadTitle(String value) {
        driver.findElement(title).sendKeys(value);
    }

    public void setProductPrice() {
        driver.findElement(price).sendKeys(String.valueOf(35));
    }
}
