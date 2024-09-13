package test;

import common.Constant;
import io.qameta.allure.Story;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestLogout extends TestBase{
    private static final Logger logger = LogManager.getLogger(TestLogin.class);

    @BeforeMethod
    @Override
    public void setUpMethod() {
        super.setUpMethod();

        loginPage.loginSauceDemo(
                Constant.Credentials.STANDARD_USER.getUsername(),
                Constant.Credentials.STANDARD_USER.getPwd());
        homePage.clickButtonOKOfTheLoggedInAlert();
        verify.urlOfLoginSuccessful();
        verify.homepageLogo();
        logger.info("Override setUpMethod is completed");
    }

    @Story("Logout")
    @Test
    public void logout() {
        homePage.clickBurgerMenu();
        verify.homepageSidebarMenuDisplayed();
        homePage.clickButtonLogout();
    }

}
