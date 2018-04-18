package ui.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by @v.matviichenko
 */
public class LoginPage {
    private WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void waitUntilFormAppear() {
        new WebDriverWait(driver,10).until(ExpectedConditions.visibilityOfElementLocated(By.id("box-login")));
    }

    public void setUsername(String username) {
        driver.findElement(By.cssSelector("input[name='username']")).sendKeys(username);
    }

    public void setPassword(String password) {
        driver.findElement(By.cssSelector("input[name='password']")).sendKeys(password);
    }

    public void clickOnLogin() {
        driver.findElement(By.cssSelector("button[name='login']")).click();
    }

}
