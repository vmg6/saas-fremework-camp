package ui.pages;

import base.core.UIRepository;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

/**
 * Created by @v.matviichenko
 */
public class LoginPage extends UIRepository {

    private static final Logger LOGGER = Logger.getLogger(LoginPage.class);

    public LoginPage(WebDriver driver) {
        super(driver);
//        TreeNode body =
//                addElement((TreeNode) null, "body", "xpath=/html/body", Body.class);
//        TreeNode form =
//                addElement(body, "Form", "id='login'", Form.class);
//        addElement(form, "Username", "id='Username'", Edit.class);
//        addElement(form, "Password", "id='Password'", Edit.class);
//        addElement(form, "SignIn", "xpath=//a[@type='submit']", Button.class);
//        addElement(body, "ErrorAlert", "class='error'", Text.class);

//        waitForElement("Form");
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
