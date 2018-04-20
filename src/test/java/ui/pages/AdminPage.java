package ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.HashMap;
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

    public List<WebElement> getMenuItemsList() {
        waitAdminPageAppear();
        WebElement menu = driver.findElement(By.id("box-apps-menu"));
        List<WebElement> menuItems = menu.findElements(By.id("app-"));
        return menuItems;
    }

    public HashMap<String, Boolean> checkTagOnPage() {
        HashMap<String, Boolean> menuItems = new HashMap<>();
        for (int i = 0; i < getMenuItemsList().size(); i ++) {
            getMenuItemsList().get(i).click();
            menuItems.put(getMenuItemsList().get(i).getText(), areElementPresetOnPage());
        }
        return menuItems;
    }

    private boolean areElementPresetOnPage() {
        return driver.findElements(By.tagName("h1")).size() > 0;
    }
}
