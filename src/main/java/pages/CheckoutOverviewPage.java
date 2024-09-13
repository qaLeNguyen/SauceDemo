package pages;

import common.ActionBase;
import io.qameta.allure.Step;
import objects.CheckoutOverviewPageLocator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CheckoutOverviewPage extends ActionBase {
    private static final Logger logger = LogManager.getLogger(CheckoutOverviewPage.class);

    public CheckoutOverviewPage() {
        super();
    }

    @Step("Input quantity")
    public void editQuantity(int number) {
        try{
            input(CheckoutOverviewPageLocator.FIELD_QUANTITY.getBy(), number);
            logger.info("Inputted quantity '{}'", number);
        } catch (Exception e) {
            logger.error("Exception occurred '{}'. Failed to input number '{}'", e.getMessage(), number);
        }
    }

    @Step("Click the button 'Finish'")
    public void clickButtonFinish() {
        try {
            click(CheckoutOverviewPageLocator.BUTTON_FINISH.getBy());
            logger.info("Click the button 'Finish'");
        } catch (Exception e) {
            logger.error("Exception occurred. Failed to click the button 'Finish'");
        }
    }

    @Step("Click the button 'Cancel'")
    public void clickButtonCancel() {
        try {
            click(CheckoutOverviewPageLocator.BUTTON_CANCEL.getBy());
            logger.info("Click the button 'Cancel'");
        } catch (Exception e) {
            logger.error("Exception occurred. Failed to click the button 'Cancel'");
        }
    }

    @Step("Find products by name")
    public boolean checkAllAddedProductsDisplayed(List<String> productNames) {
        boolean allProductsFound = true;

        try {
            for (String productName : productNames) {
                List<WebElement> productElements = driver.findElements
                        (CheckoutOverviewPageLocator.DYNAMIC_PRODUCT_NAMES.getBy(productName));

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

    public double getSumOfAllProduct() {
        try {
            List<WebElement> priceElements = driver.findElements
                    (CheckoutOverviewPageLocator.PRODUCT_PRICE.getBy());

            if (priceElements.isEmpty()) {
                logger.warn("No product price elements found.");
                return 0.0;
            }

            double total = priceElements.stream()
                    .map(WebElement::getText)
                    .map(price -> price.replace("$", "").trim())
                    .mapToDouble(Double::parseDouble)
                    .sum();

            logger.info("Calculated sum of all product prices: '{}'", total);
            return total;
        } catch (NumberFormatException e) {
            logger.error("Failed to parse one or more price values", e);
        } catch (Exception e) {
            logger.error("Exception occurred while calculating the sum of product prices '{}'", e.getMessage());
        }
        return 0.0;
    }

    public double getItemTotal() {
        try {
            WebElement itemTotalElement = driver.findElement
                    (CheckoutOverviewPageLocator.PRODUCT_ITEM_TOTAL.getBy());
            String itemTotalText = itemTotalElement.getText();
            logger.info("Retrieved item total: '{}'", itemTotalText);
            return Double.parseDouble(itemTotalText.replace("$", "").trim());
        } catch (NoSuchElementException e) {
            logger.error("Item total element not found on the page", e);
        } catch (NumberFormatException e) {
            logger.error("Failed to parse item total value", e);
        } catch (Exception e) {
            logger.error("Exception occurred while getting the item total '{}'", e.getMessage());
        }
        return 0.0;
    }

    public double getTax() {
        try {
            WebElement taxElement = driver.findElement
                    (CheckoutOverviewPageLocator.PRODUCT_TAX.getBy());
            String taxText = taxElement.getText();
            logger.info("Retrieved item tax: '{}'", taxText);
            return Double.parseDouble(taxText.replace("$", "").trim());
        } catch (NoSuchElementException e) {
            logger.error("Tax element not found on the page", e);
        } catch (NumberFormatException e) {
            logger.error("Failed to parse tax value", e);
        } catch (Exception e) {
            logger.error("Exception occurred while getting the 'tax' '{}'", e.getMessage());
        }
        return 0.0;
    }

    public double getTotal() {
        try {
            WebElement totalElement = driver.findElement
                    (CheckoutOverviewPageLocator.PRODUCT_TOTAL.getBy());
            String totalText = totalElement.getText();
            logger.info("Retrieved total: '{}'", totalText);
            return Double.parseDouble(totalText.replace("$", "").trim());
        } catch (NoSuchElementException e) {
            logger.error("Total element not found on the page", e);
        } catch (NumberFormatException e) {
            logger.error("Failed to parse 'total' value", e);
        } catch (Exception e) {
            logger.error("Exception occurred while getting the 'total' '{}'", e.getMessage());
        }
        return 0.0;
    }

    /**
     * Verifies that the item total, tax, and final total are correctly calculated
     * based on the sum of all product prices.
     *
     * @return True if the verification is successful, false otherwise.
     */
    public boolean verifyTotals() {
        try {
            double sumOfAllProducts = getSumOfAllProduct();
            double itemTotal = getItemTotal();
            double tax = getTax();
            double total = getTotal();

            boolean isItemTotalCorrect = Math.abs(sumOfAllProducts - itemTotal) < 0.01; // Allowing a small margin for floating point precision

            boolean isTotalCorrect = Math.abs((itemTotal + tax) - total) < 0.01;

            if (isItemTotalCorrect && isTotalCorrect) {
                logger.info("Verification successful: Item total and final total are correct.");
                return true;
            } else {
                logger.error("Verification failed: Item total or final total is incorrect.");
                logger.info("Expected Item Total: '{}', Found Item Total: '{}'", sumOfAllProducts, itemTotal);
                logger.info("Expected Total: '{}', Found Total: '{}'", (itemTotal + tax), total);
                return false;
            }
        } catch (Exception e) {
            logger.error("Exception occurred during totals verification.", e);
            return false;
        }
    }









}
