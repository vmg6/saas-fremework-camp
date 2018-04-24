package ui.pages;


import base.core.TestProperties;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by @v.matviichenko
 */
public class LoginPage {
    private TestProperties properties = TestProperties.getInstance();
    private WebDriver driver;

    By userName = By.cssSelector("input[name='username']");
    By userPassword = By.cssSelector("input[name='password']");
    By userLogin = By.cssSelector("button[name='login']");
    By waitForm = By.id("box-login");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void openPage() {
        driver.get(properties.getServerProperty("ui.admin.url") + "/admin");
    }

    public void waitUntilFormAppear() {
        new WebDriverWait(driver,10).until(ExpectedConditions.visibilityOfElementLocated(waitForm));
    }

    public void setUsername() {
        driver.findElement(userName).sendKeys(properties.getServerProperty("username"));
    }

    public void setPassword() {
        driver.findElement(userPassword).sendKeys(properties.getServerProperty("password"));
    }

    public LeftMenu clickOnLogin() {
        driver.findElement(userLogin).click();
        return new LeftMenu(driver);
    }

}
