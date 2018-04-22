package ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by @v.matviichenko
 */
public class ProductInfoCard {
    private WebDriver driver;
    public ProductInfoCard(WebDriver driver) {
        this.driver = driver;
    }

    public void clickOnHomeButton() {
        driver.findElement(By.className("general-0")).click();
    }

    public MainPage clickOnAddToCart(Integer numberElement) {
        driver.findElement(By.xpath("//button[@name='add_cart_product']")).click();
        new WebDriverWait(driver,10)
                .until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("//span[@class='quantity']"), "" + numberElement + ""));
        clickOnHomeButton();
        return new MainPage(driver);
    }
}
