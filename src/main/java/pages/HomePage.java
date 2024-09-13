package pages;

import com.google.common.base.Verify;
import common.ActionBase;
import io.qameta.allure.Step;
import objects.HomePageLocator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import java.util.List;

public class HomePage extends ActionBase {
    private static final Logger logger = LogManager.getLogger(HomePage.class);

    public HomePage() {
        super();
    }

    @Step("Click the button 'OK' at the alert")
    public void clickButtonOKOfTheLoggedInAlert() {
        try {
            boolean isAlertDisplayed = true;
            if (!isAlertDisplayed) {
                logger.warn("The Alert does not display");
            } else {
                acceptAlert();
                logger.info("Click 'OK' button on 'Logged in' Alert");
            }
        } catch (Exception e) {
            logger.error("Exception occurred. Failed to click 'OK' alert");
        }
    }

    @Step("Click the burger menu")
    public void clickBurgerMenu() {
        try {
            click(HomePageLocator.BURGER_MENU.getBy());
            logger.info("Clicked burger menu");
        } catch (Exception e) {
            logger.error("Exception occurred. Fail to click burger menu");
        }
    }

    @Step("Click the button 'Logout'")
    public void clickButtonLogout() {
        try {
            click(HomePageLocator.SIDEBAR_BUTTON_LOG_OUT.getBy());
            logger.info("Clicked the button 'Logout'");
        } catch (Exception e) {
            logger.error("Exception occurred. Failed to click the button 'Logout'");
        }
    }

    @Step("Click the menu 'Shopping'")
    public void clickMenuShopping() {
        try {
            click(HomePageLocator.MENU_SHOPPING.getBy());
            logger.info("Clicked menu 'Shopping'");
        } catch (Exception e) {
            logger.error("Exception occurred. Failed to click menu 'Shopping'");
        }
    }

    @Step("Click the button 'Checkout'")
    public void clickButtonCheckout() {
        try {
            click(HomePageLocator.BUTTON_CHECKOUT.getBy());
            logger.info("Clicked the button 'Checkout'");
        } catch (Exception e) {
            logger.error("Exception occurred. Failed to click the button 'Checkout'");
        }
    }

    @Step("Click the button 'Continue Shopping'")
    public void clickButtonContinueShopping() {
        try {
            click(HomePageLocator.BUTTON_CONTINUE_SHOPPING.getBy());
            logger.info("Clicked the button 'Continue Shopping'");
        } catch (Exception e) {
            logger.error("Exception occurred. Failed to click the button 'Continue Shopping'");
        }
    }

    @Step("Find a product")
    public void findProduct(String productName) {
        try {
            getElementVisible(HomePageLocator.PRODUCT_NAME.getBy(productName));
            logger.info("'{}' is found", productName);
        } catch (Exception e) {
            logger.error("Exception occurred while finding product named '{}'", productName);
        }
    }

    @Step("Find products by name")
    public void findProducts(List<String> productNames) {
       try {
           for (String productName : productNames) {

               List<WebElement> productElements = driver.findElements
                       (HomePageLocator.DYNAMIC_PRODUCT_NAMES.getBy(productName));

               if (!productElements.isEmpty()) {
                   logger.info("Product '{}' found", productName);
               } else {
                   logger.warn("Product '{}' not found", productName);
               }
           }
        } catch (Exception e) {
           logger.error("Exception occurred while finding products: '{}'", e.getMessage());
       }
    }

    @Step("Find products by name and add to cart")
    public void findProductsAndAddToCart(@NotNull List<String> productNames) {
        for (String productName : productNames) {
            try {
                driver.findElement(HomePageLocator.DYNAMIC_PRODUCT_NAMES.getBy(productName));
                logger.info("Product '{}' found. Ready to 'Add to cart'", productName);

                String buttonId = "add-to-cart-" + productName.toLowerCase().replace(" ", "-");

                WebElement buttonAddToCart = driver.findElement(By.id(buttonId));
                buttonAddToCart.click();
                logger.info("Clicked the button 'Add to cart' for product '{}'", productName);

                boolean isStateChanged = isButtonStateChange(buttonAddToCart, "Remove");
                if (isStateChanged) {
                    logger.info("Button text changed to 'Remove'");
                } else {
                    logger.warn("Button text did not change to 'Remove'");
                }

            } catch (NoSuchElementException e) {
                logger.warn("Product '{}' or 'Add to cart' button not found: {}", productName, e.getMessage());
            } catch (Exception e) {
                logger.error("Exception occurred while finding products and adding to cart: '{}'", e.getMessage());
            }
        }
    }

    @Step("Verify the state of button 'Add to cart' changed to 'Remove' and vice versa")
    public static boolean isButtonStateChange(@NotNull WebElement button,
                                              @NotNull String buttonText) {
        return buttonText.equals(button.getText());
    }




}
