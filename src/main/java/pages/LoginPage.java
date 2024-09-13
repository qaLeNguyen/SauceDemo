package pages;

import common.ActionBase;
import io.qameta.allure.Step;
import objects.LoginPageLocator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class LoginPage extends ActionBase {
    private static final Logger logger = LogManager.getLogger(LoginPage.class);


    public LoginPage() {
        super();
    }

    @Step("Input Username")
    public void inputUsername(String username) {
        input(LoginPageLocator.FIELD_INPUT_USERNAME.getBy(), username);
        logger.info("Inputted Username '{}'", username);
    }

    @Step("Input password")
    public void inputPwd(String pwd) {
        input(LoginPageLocator.FIELD_INPUT_PWD.getBy(), pwd);
        logger.info("Inputted Password '{}'", pwd);
    }

    @Step("Click the button 'Login'")
    public void clickButtonLogin() {
        click(LoginPageLocator.BUTTON_LOGIN.getBy());
        logger.info("Clicked the button 'Login'");
    }

    @Step("Login the Login page")
    public void loginSauceDemo(String username,
                               String pwd) {
        try {
            logger.info("Attempt to login 'SauceDemo' page");
            inputUsername(username);
            inputPwd(pwd);
            clickButtonLogin();
        } catch (Exception e) {
            logger.error("Exception occurred while login 'SauceDemo' page");
        }
    }

    @Step("Leave the username field empty")
    public boolean isUserNameFieldEmpty() {
        logger.info("Username field is empty");
        return isFieldEmpty(LoginPageLocator.FIELD_INPUT_USERNAME.getBy());
    }

    @Step("Leave the password field empty")
    public boolean isPwdFieldEmpty() {
        logger.info("Password field is empty");
        return isFieldEmpty(LoginPageLocator.FIELD_INPUT_PWD.getBy());
    }


}
