package base.ui.components;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.InvalidElementStateException;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by @v.matviichenko
 */
public abstract class BaseComponent implements IUIComponent {
    private static final Logger LOGGER = Logger.getLogger(BaseComponent.class);

    protected SearchContext element;

    public BaseComponent() {
    }

    public BaseComponent(SearchContext element) {
        this.element = element;
    }

    @Override
    public BaseComponent setWebElement(SearchContext element) {
        this.element = element;
        return this;
    }

    @Override
    public Object getValue() {
        Object value = ((WebElement) element).getText();
        LOGGER.info("getValue: " + String.valueOf(value));
        return value;
    }

    @Override
    public <T> T getValue(Class<T> type) {
        return valueToType((String) getValue(), type);
    }

    @Override
    public List<Object> getAllValues() {
        LOGGER.info("getAllValue: ");
        List<Object> result = new ArrayList<>();
        List<WebElement> webElements = element.findElements(By.xpath("descendant::*"));
        for (WebElement webelement : webElements) {
            result.add(webelement.getText());
        }
        return result;
    }

    @Override
    public void setValue(final Object value) {
        String valueString = String.valueOf(value);
        LOGGER.info("setValue: " + valueString);
        ((WebElement) element).sendKeys(valueString);
    }

    @Override
    public void click() {
        LOGGER.info("click");
        ((WebElement) element).click();
    }

    @Override
    public void doubleClick() {
        LOGGER.info("doubleClick");
    }

    @Override
    public void check() {
        LOGGER.info("check");
        if (!((WebElement) element).isSelected()) {
            click();
        }
    }

    @Override
    public void unCheck() {
        LOGGER.info("unCheck");
        if (((WebElement) element).isSelected()) {
            click();
        }
    }

    @Override
    public void moveMouseToMe() {
        LOGGER.info("moveTo");
        new Actions(getWebdriver()).moveToElement((WebElement) element).release().build().perform();
    }

    @Override
    public void clear() {
        LOGGER.info("clear");
        long maxTimeWait = System.currentTimeMillis() + 30000;
        while (true) {
            try {
                if (System.currentTimeMillis() > maxTimeWait) {
                    throw new RuntimeException("can not clear element");
                }

                ((WebElement) element).clear();
                return;
            } catch (InvalidElementStateException ignored) {
            }
        }
    }

    @Override
    public By getBy(String locator) {
        Pattern p = Pattern.compile("='(.*?)'");
        Matcher matcher = p.matcher(locator);

        if (locator.startsWith("id") && matcher.find()) {
            return By.id(matcher.group(1));
        }

        if (locator.startsWith("name") && matcher.find()) {
            return By.name(matcher.group(1));
        }

        if (locator.startsWith("title") && matcher.find()) {
            return By.xpath("descendant::*[@title='" + matcher.group(1) + "']");
        }

        if (locator.startsWith("class") && matcher.find()) {
            return By.className(matcher.group(1));
        }

        if (locator.startsWith("xpath")) {
            String xpath = locator.replace("xpath=", "");
            return By.xpath(xpath);
        }

        if (locator.startsWith("css")) {
            String css = locator.replace("css=", "");
            return By.cssSelector(css);
        }

        if (locator.startsWith("linktext=")) {
            String linktext = locator.replace("linktext=", "");
            return By.linkText(linktext);
        }

        if (locator.startsWith("linktext~")) {
            String linktext = locator.replace("linktext~", "");
            return By.partialLinkText(linktext);
        }

        if (locator.startsWith("tag")) {
            String tag = locator.replace("tag=", "");
            return By.tagName(tag);
        }

        throw new RuntimeException("Locator type not found: " + locator);
    }

    protected WebDriver getWebdriver() {
        Field f;
        try {
            f = element.getClass().getDeclaredField("parent");

            f.setAccessible(true);
            Object o = f.get(element);
            if (o instanceof WebDriver) {
                return (WebDriver) o;
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    protected <T extends Object> T valueToType(String value, Class<T> type) {
        LOGGER.info("convert value: " + value);
        if (type.isAssignableFrom(String.class)) {
            return (T) value;
        } else if (type.isAssignableFrom(Integer.class)) {
            return (T) Integer.valueOf(value);
        } else if (type.isAssignableFrom(Boolean.class)) {
            return (T) Boolean.valueOf(value);
        } else if (type.isAssignableFrom(Double.class)) {
            return (T) Double.valueOf(value);
        } else if (type.isAssignableFrom(Float.class)) {
            return (T) Float.valueOf(value);
        } else {
            throw new IllegalArgumentException("Bad type.");
        }
    }

}
