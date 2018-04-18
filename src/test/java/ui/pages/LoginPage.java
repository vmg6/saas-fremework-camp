package ui.pages;

import base.core.TreeNode;
import base.core.UIRepository;
import base.ui.components.Body;
import base.ui.components.Form;
import org.openqa.selenium.WebDriver;

/**
 * Created by @v.matviichenko
 */
public class LoginPage extends UIRepository {

    public LoginPage(WebDriver driver) {
        super(driver);
        TreeNode body =
                addElement((TreeNode) null, "body", "xpath=/html/body", Body.class);
        TreeNode form =
                addElement(body, "Form", "id='box-login'", Form.class);
//        addElement(form, "Username", "id='Username'", Edit.class);
//        addElement(form, "Password", "id='Password'", Edit.class);
    }

//    public boolean isErrorPresent() {
//
//        try {
//            waitForElement("ErrorAlert");
//        } catch (TimeoutException e){
//            return false;
//        }
//        return getWebElementByName("ErrorAlert").isDisplayed();
//    }

//    public boolean isTitleLoginPresent() {
//        return isElementPresent("Form");
//    }
//
//    public LoginPage setUserName(final String username) {
//        getWebComponent("Username").setValue(username);
//        return this;
//    }
//
//    public LoginPage setPassword(final String password) {
//        getWebComponent("Password").setValue(password);
//        return this;
//    }
//
//    public MainPage loginAs(final String login, final String password) {
//
//        LOGGER.info("Enter as " + login);
//        waitForElement("Form");
//        if (!isTitleLoginPresent())
//            new RuntimeException("Login page not found!!!");
//
//        setUserName(login);
//        setPassword(password);
//        clickOnSignIn();
//
//        return new MainPage(driver).waitForMainPage();
//
//    }
//
//    public void clickOnSignIn(){
//        Helper.sleep(500);
//        getWebComponent("SignIn").click();
//    }
}
