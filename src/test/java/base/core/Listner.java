package base.core;

import com.google.common.io.Files;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;

import java.io.File;
import java.io.IOException;

/**
 * Created by @v.matviichenko
 */
public class Listner extends AbstractWebDriverEventListener {
    @Override
    public void beforeFindBy(By by, WebElement element, WebDriver driver) {
        System.out.println("Start search for: " + by);
    }

    @Override
    public void afterFindBy(By by, WebElement element, WebDriver driver) {
        System.out.println(by + " has been found");
    }

    @Override
    public void onException(Throwable throwable, WebDriver driver) {

        System.out.println("Shit Happened: "+ throwable.getMessage());

        File tempFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            Files.copy(tempFile, new File(System.currentTimeMillis()+ "screen.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
