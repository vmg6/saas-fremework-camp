package ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

/**
 * Created by @v.matviichenko
 */
public class CheckoutPage {
    private WebDriver driver;

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
    }

    public CheckoutPage removeProductFromCart() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        List<WebElement> row = driver.findElements(By.cssSelector("#order_confirmation-wrapper tr td.item"));
        for (int i = 1; i <= row.size(); i++)
        {
            List<WebElement> shortcuts = driver.findElements(By.cssSelector("#box-checkout-cart .shortcuts li"));
            if (shortcuts.size() > 0)
            {
                WebElement shortcut = driver.findElement(By.cssSelector("#box-checkout-cart .shortcuts li:nth-child(1)"));
                shortcut.click();
                WebElement btn = new WebDriverWait(driver, 10)
                        .until(ExpectedConditions.elementToBeClickable(By.cssSelector("[name='remove_cart_item']")));
                btn.click();
                wait.until(ExpectedConditions.stalenessOf(row.get(0)));
            }
            else
            {
                WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[name='remove_cart_item']")));
                btn.click();
                wait.until(ExpectedConditions.stalenessOf(row.get(0)));
            }
        }
        wait.until(ExpectedConditions.numberOfElementsToBe(By.cssSelector("#checkout-cart-wrapper p"), 2));

        return this;
    }

    public boolean emptyCart() {
        return driver.findElement(By.cssSelector("#checkout-cart-wrapper p")).getText().equals("There are no items in your cart.");
    }
}
