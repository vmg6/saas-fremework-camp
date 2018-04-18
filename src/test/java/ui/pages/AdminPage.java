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
public class AdminPage {
    private WebDriver driver;

    public AdminPage(WebDriver driver) {
        this.driver = driver;
    }

    public void waitAdminPageAppear() {
        new WebDriverWait(driver,10).until(ExpectedConditions.visibilityOfElementLocated(By.className("logotype")));
    }

    public boolean isMenuPresent() {
        waitAdminPageAppear();
        WebElement menu = driver.findElement(By.id("box-apps-menu"));
        List<WebElement> menuItems = menu.findElements(By.tagName("li"));
        return menuItems.size() > 1;
    }
}
