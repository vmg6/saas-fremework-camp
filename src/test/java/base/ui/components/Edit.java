package base.ui.components;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by @v.matviichenko
 */
public class Edit extends BaseComponent implements IUIComponent {
    public Edit() {
    }

    public Edit(SearchContext element) {
        super(element);
    }

    @Override
    public By getBy(String locator) {
        Pattern p = Pattern.compile("='(.*?)'");
        Matcher matcher = p.matcher(locator);

        if (locator.startsWith("labeltext") && matcher.find()) {
            return By.xpath("descendant::label[text()='" + matcher.group(1).replace(":", "") + "']/ancestor::table/descendant::*[@role='textbox']");
        }

        return super.getBy(locator);
    }
}
