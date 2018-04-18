package base.ui.components;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;

import java.util.List;

/**
 * Created by @v.matviichenko
 */
public interface IUIComponent {
    IUIComponent setWebElement(SearchContext element);

    Object getValue();

    <T> T getValue(Class<T> elementType);

    List<Object> getAllValues();

    void setValue(Object value);

    void click();

    void doubleClick();

    void check();

    void unCheck();

    void moveMouseToMe();

    void clear();

    By getBy(String locator);
}
