package verification;

import common.ActionBase;
import common.Constant;
import io.qameta.allure.Step;
import objects.CheckOutCompletePageLocator;
import objects.HomePageLocator;
import objects.LoginPageLocator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.testng.Assert;


public class Verify extends ActionBase {
    private static final Logger logger = LogManager.getLogger(Verify.class);

    public Verify() {
        super();
    }

    @Step("Verify login page title")
    public void titleOfLoginPage() {
        String actual = driver.getTitle();
        Assert.assertEquals(actual, "Swag Labs",
                "Title should be " + Constant.Title.LOGIN_PAGE.getTitle() + " but got " + actual);
        logger.info("Login page title: '{}' is correct", actual);
    }

    @Step("Verify the current url of logged in page")
    public void urlOfLoginSuccessful() {
        String actual = driver.getCurrentUrl();
        Assert.assertEquals(actual, Constant.Url.HOME_PAGE.getUrl(),
                "The url should be " + Constant.Url.HOME_PAGE.getUrl() + " but got" + actual);
        logger.info("Login successful. The url 'Logged in' is: '{}'", actual);
    }

    @Step("Verify the current url of 'Cart' page")
    public void urlOfCartPage() {
        String actual = driver.getCurrentUrl();
        Assert.assertEquals(actual, Constant.Url.CART_PAGE.getUrl(),
                "The url should be " + Constant.Url.CART_PAGE.getUrl() + " but got" + actual);
        logger.info("Login successful. The url 'Cart' page is: '{}'", actual);
    }

    @Step("Verify the current url of 'Checkout Info' page")
    public void urlOfCheckoutInfoPage() {
        String actual = driver.getCurrentUrl();
        Assert.assertEquals(actual, Constant.Url.CHECKOUT_INFO_PAGE.getUrl(),
                "The url should be " + Constant.Url.CHECKOUT_INFO_PAGE.getUrl() + " but got" + actual);
        logger.info("Login successful. The url 'Checkout Info' page is: '{}'", actual);
    }

    @Step("Verify the current url of 'Checkout Overview' page")
    public void urlOfCheckoutOverviewPage() {
        String actual = driver.getCurrentUrl();
        Assert.assertEquals(actual, Constant.Url.CHECKOUT_OVERVIEW_PAGE.getUrl(),
                "The url should be " + Constant.Url.CHECKOUT_OVERVIEW_PAGE.getUrl() + " but got" + actual);
        logger.info("Login successful. The url 'Checkout Overview' page is: '{}'", actual);
    }

    @Step("Verify the error message 'User is required' displays")
    public void errorUserIsRequiredDisplayed() {
        WebElement errorMsg = getElementVisible(LoginPageLocator.ERROR_MSG.getBy());
        boolean isErrorMsgDisplayed = isElementPresent(LoginPageLocator.ERROR_MSG.getBy());
        Assert.assertTrue(isErrorMsgDisplayed,
                "The error message does not display");

        String expectedText = "Epic sadface: Username is required";
        String actualText = errorMsg.getText();
        Assert.assertEquals(actualText, expectedText,
                "The error message text does not match");
        logger.info("Missing username error is '{}'", actualText);
    }

    @Step("Verify the error message 'User is required' displays")
    public void errorPwdIsRequiredDisplayed() {
        WebElement errorMsg = getElementVisible(LoginPageLocator.ERROR_MSG.getBy());
        boolean isErrorMsgDisplayed = isElementPresent(LoginPageLocator.ERROR_MSG.getBy());
        Assert.assertTrue(isErrorMsgDisplayed,
                "The error message does not display");

        String expectedText = "Epic sadface: Password is required";
        String actualText = errorMsg.getText();
        Assert.assertEquals(actualText, expectedText,
                "The error message text does not match");
        logger.info("Missing password error is '{}'", actualText);
    }

    @Step("Verify the error message 'Username and password do not match any user in this service' displays")
    public void errorInvalidCredentialsDisplayed() {
        WebElement errorMsg = getElementVisible(LoginPageLocator.ERROR_MSG.getBy());
        boolean isErrorMsgDisplayed = isElementPresent(LoginPageLocator.ERROR_MSG.getBy());
        Assert.assertTrue(isErrorMsgDisplayed,
                "The error message does not display");

        String expectedText = "Epic sadface: Username and password do not match any user in this service";
        String actualText = errorMsg.getText();
        Assert.assertEquals(actualText, expectedText,
                "The error message text does not match");
        logger.info("Invalid credentials error is '{}'", actualText);
    }

    @Step("Verify the sidebar menu displays")
    public void homepageSidebarMenuDisplayed() {
        try {
            WebElement buttonAllItems = getElementVisible(HomePageLocator.SIDEBAR_BUTTON_ALL_ITEMS.getBy());
            WebElement buttonAbout = getElementVisible(HomePageLocator.SIDEBAR_BUTTON_ABOUT.getBy());
            WebElement buttonLogout = getElementVisible(HomePageLocator.SIDEBAR_BUTTON_LOG_OUT.getBy());
            WebElement buttonResetAppState = getElementVisible(HomePageLocator.SIDEBAR_BUTTON_RESET.getBy());

            boolean isAllItems = buttonAllItems.isDisplayed();
            boolean isAbout = buttonAbout.isDisplayed();
            boolean isLogout = buttonLogout.isDisplayed();
            boolean isResetAppState = buttonResetAppState.isDisplayed();

            boolean result = isAllItems && isAbout && isLogout && isResetAppState;
            Assert.assertTrue(result, "Sidebar menu is not displayed fully");
            logger.info("Sidebar menu is displayed");
        } catch (Exception e) {
            logger.error("Exception occurred while checking sidebar menu display");
        }
    }

    @Step("Verify 'Swag Labs' logo")
    public void homepageLogo() {
        boolean isLogo = isElementPresent(HomePageLocator.LOGO.getBy());
        Assert.assertTrue(isLogo, "The logo does not display");
        logger.info("The logo 'Swag Labs' displays");
    }

    @Step("Verify 'Thank you for your order!' displays")
    public void verifyOrderSuccessful() {
        boolean orderSuccessful = isElementPresent
                (CheckOutCompletePageLocator.TEXT_THANK_YOU_FOR_YOUR_ORDER.getBy());
        Assert.assertTrue(orderSuccessful,
                "The successful text does not display");
        logger.info("The text 'Thank you for your order!' displays");
    }






}
