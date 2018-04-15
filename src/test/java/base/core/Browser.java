package base.core;

import com.google.inject.Inject;
import org.openqa.selenium.WebDriver;

/**
 * Created by @v.matviichenko
 */
public class Browser {
    private WebDriver driver;

    @Inject
    public Browser() {

    }

    public WebDriver getDriver() {
        return driver;
    }
}
