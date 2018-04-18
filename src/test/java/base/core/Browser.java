package base.core;

import com.google.inject.Inject;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.EdgeDriverManager;
import io.github.bonigarcia.wdm.FirefoxDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by @v.matviichenko
 */
public class Browser {
    @Inject
    public Browser() {

    }

    public WebDriver getChromeDriver() {
        ChromeDriverManager.getInstance().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        return driver;
    }

    public WebDriver getFirefoxDriver() {
        FirefoxDriverManager.getInstance().setup();
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().maximize();
        return driver;
    }

    public WebDriver getEdgeDriver() {
        EdgeDriverManager.getInstance().setup();
        WebDriver driver = new EdgeDriver();
        return driver;
    }
}
