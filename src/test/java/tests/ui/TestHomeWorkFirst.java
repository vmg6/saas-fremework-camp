package tests.ui;

import base.core.TestBaseTNG;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.EdgeDriverManager;
import io.github.bonigarcia.wdm.FirefoxDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

/**
 * Created by @v.matviichenko
 */
public class TestHomeWorkFirst extends TestBaseTNG {
    private final String URL =  properties.getServerProperty("url_test");

    @Test(groups = {"ui"})
    public void testChrome() {
        ChromeDriverManager.getInstance().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(URL);
        driver.quit();
    }

    @Test(groups = {"ui"})
    public void testFireFox() {
        FirefoxDriverManager.getInstance().setup();
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.get(URL);
        driver.quit();
    }

    @Test(groups = {"ui"}, enabled = false)
    public void testEdge() {
        EdgeDriverManager.getInstance().setup();
        WebDriver driver = new EdgeDriver();
        driver.get(URL);
        driver.quit();
    }
}
