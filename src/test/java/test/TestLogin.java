package test;

import common.Constant;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestLogin extends TestBase{
    private static final Logger logger = LogManager.getLogger(TestLogin.class);

    public TestLogin() {
        super();
    }

    @BeforeMethod
    @Override
    public void setUpMethod() {
        super.setUpMethod();

        driver.navigate().refresh();
        logger.info("Override setUpMethod is completed");
    }

    @Feature("Login Feature")
    @Story("Login 'SauceDemo' with valid credential")
    @Test
    public void loginWithValidCredential() {
        String username = Constant.Credentials.STANDARD_USER.getUsername();
        String pwd = Constant.Credentials.STANDARD_USER.getPwd();
        loginPage.loginSauceDemo(username, pwd);
    }

    @Feature("Login Feature")
    @Story("Login 'SauceDemo' with locked_out_user")
    @Test
    public void loginWithLockedOutUser() {
        String username = Constant.Credentials.LOCKOUT_USER.getUsername();
        String pwd = Constant.Credentials.LOCKOUT_USER.getPwd();
        loginPage.loginSauceDemo(username, pwd);
        verify.urlOfLoginSuccessful();
    }

    @Feature("Login Feature")
    @Story("Login 'SauceDemo' with problem_user")
    @Test
    public void loginWithProblemUser() {
        String username = Constant.Credentials.PROBLEM_USER.getUsername();
        String pwd = Constant.Credentials.PROBLEM_USER.getPwd();
        loginPage.loginSauceDemo(username, pwd);
    }

    @Feature("Login Feature")
    @Story("Login 'SauceDemo' with performance_glitch_user")
    @Test
    public void loginWithPerformanceGlitchUser() {
        String username = Constant.Credentials.PERFORMANCE_GLITCH_USER.getUsername();
        String pwd = Constant.Credentials.PERFORMANCE_GLITCH_USER.getPwd();
        loginPage.loginSauceDemo(username, pwd);
    }

    @Feature("Login Feature")
    @Story("Login 'SauceDemo' with error_user")
    @Test
    public void loginWithErrorUser() {
        String username = Constant.Credentials.ERROR_USER.getUsername();
        String pwd = Constant.Credentials.ERROR_USER.getPwd();
        loginPage.loginSauceDemo(username, pwd);
    }

    @Feature("Login Feature")
    @Story("Login 'SauceDemo' with visual_user")
    @Test
    public void loginWithVisualUser() {
        String username = Constant.Credentials.VISUAL_USER.getUsername();
        String pwd = Constant.Credentials.VISUAL_USER.getPwd();
        loginPage.loginSauceDemo(username, pwd);
    }

    @Feature("Login Feature")
    @Story("Login 'SauceDemo' with empty username")
    @Test
    public void loginWithEmptyUsername() {
        String pwd = Constant.Credentials.STANDARD_USER.getPwd();
        if (loginPage.isUserNameFieldEmpty()) {
            loginPage.inputPwd(pwd);
        }
        verify.errorUserIsRequiredDisplayed();
    }

    @Feature("Login Feature")
    @Story("Login 'SauceDemo' with empty password")
    @Test
    public void loginWithEmptyPwd() {
        String username = Constant.Credentials.STANDARD_USER.getUsername();
        if (loginPage.isPwdFieldEmpty()) {
            loginPage.inputUsername(username);
        }
        verify.errorPwdIsRequiredDisplayed();
    }

    @Feature("Login Feature")
    @Story("Login 'SauceDemo' with invalid credential")
    @Test
    public void loginWithInvalidCredential() {
        String username = Constant.Credentials.INVALID_USER.getPwd();
        String pwd = Constant.Credentials.INVALID_USER.getPwd();
        loginPage.loginSauceDemo(username, pwd);
        verify.errorInvalidCredentialsDisplayed();
    }


}
