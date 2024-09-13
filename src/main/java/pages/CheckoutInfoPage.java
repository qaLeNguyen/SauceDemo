package pages;

import common.ActionBase;
import io.qameta.allure.Step;
import objects.CheckoutInfoPageLocator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CheckoutInfoPage extends ActionBase {
    private static final Logger logger = LogManager.getLogger(CheckoutInfoPage.class);

    public CheckoutInfoPage() {
        super();
    }

    @Step("Input firstname")
    public void inputFirstname(String firstname) {
        try {
            input(CheckoutInfoPageLocator.FIELD_FIRSTNAME.getBy(), firstname);
            logger.info("Inputted firstname '{}'", firstname);
        } catch (Exception e) {
            logger.error("Exception occurred. Failed to input firstname '{}'", e.getMessage());
        }
    }

    @Step("Input lastname")
    public void inputLastname(String lastname) {
        try {
            input(CheckoutInfoPageLocator.FIELD_LASTNAME.getBy(), lastname);
            logger.info("Inputted lastname '{}'", lastname);
        } catch (Exception e) {
            logger.error("Exception occurred. Failed to input lastname '{}'", e.getMessage());
        }
    }

    @Step("Input Zip/ Postal Code")
    public void inputZipCode(String zipCode) {
        try {
            input(CheckoutInfoPageLocator.FIELD_ZIPCODE.getBy(), zipCode);
            logger.info("Inputted Zip Code '{}'", zipCode);
        } catch (Exception e) {
            logger.error("Exception occurred. Failed to input Zip Code '{}'", e.getMessage());
        }
    }

    @Step("Fill out all required information to checkout")
    public void fillOutRequiredInformation(String firstname,
                                           String lastname,
                                           String zipCode) {
        try {
            inputFirstname(firstname);
            inputLastname(lastname);
            inputZipCode(zipCode);
            logger.info("Fill out all required information to checkout");
        } catch (Exception e) {
            logger.error("Exception occurred. Failed to fill out all required information '{}'", e.getMessage());
        }
    }

    @Step("Click the button 'Cancel'")
    public void clickButtonCancel() {
        try {
            click(CheckoutInfoPageLocator.BUTTON_CANCEL.getBy());
            logger.info("Clicked the button 'Cancel'");
        } catch (Exception e) {
            logger.error("Exception occurred. Failed to click the button 'Cancel'");
        }
    }

    @Step("Click the button 'Continue'")
    public void clickButtonContinue() {
        try {
            click(CheckoutInfoPageLocator.BUTTON_CONTINUE.getBy());
            logger.info("Clicked the button 'Continue'");
        } catch (Exception e) {
            logger.error("Exception occurred. Failed to click the button 'Continue'");
        }
    }


}
