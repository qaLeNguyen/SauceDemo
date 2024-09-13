package pages;

import common.ActionBase;
import io.qameta.allure.Step;
import objects.CartPageLocator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CartPage extends ActionBase {
    private static final Logger logger = LogManager.getLogger(CartPage.class);

    public CartPage() {
        super();
    }

    @Step("Find products by name")
    public boolean checkAllAddedProductsDisplayed(List<String> productNames) {
        boolean allProductsFound = true;

        try {
            for (String productName : productNames) {
                List<WebElement> productElements = driver.findElements
                        (CartPageLocator.DYNAMIC_PRODUCT_NAMES.getBy(productName));

                if (!productElements.isEmpty()) {
                    logger.info("Product '{}' found", productName);
                } else {
                    logger.warn("Product '{}' not found", productName);
                    allProductsFound = false;
                }
            }
        } catch (Exception e) {
            logger.error("Exception occurred while finding products: '{}'", e.getMessage());
            return false;
        }

        return allProductsFound;
    }



}
