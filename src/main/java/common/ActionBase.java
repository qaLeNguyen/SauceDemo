package common;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.Objects;

public class ActionBase {
    private static final Logger logger = LogManager.getLogger(ActionBase.class);

    protected final WebDriver driver;
    protected final WebDriverWait wait;
    protected final Actions actions;
    protected final JavascriptExecutor jsExecutor;

    public ActionBase() {
        this.driver = WebDriverUtil.getDriver();
        this.wait = WebDriverUtil.getWait();
        this.actions = new Actions(driver);
        this.jsExecutor = (JavascriptExecutor) driver;

    }

    /**
     * Attempts to find an element located by the given locator and ensures it is visible.
     *
     * @param locator The locator of the element (can be by CSS selector, XPath, etc.).
     * @return The WebElement if it becomes visible within the timeout period;
     * null if the element is not found or not visible.
     */
    public WebElement getElementVisible(By locator) {
        try {
            logger.debug("Attempting to wait for element '{}' to be visible", locator);
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            logger.debug("Element '{}' is visible", locator);
            return element;
        } catch (TimeoutException e) {
            logger.error("TimeoutException occurred while waiting for element '{}' to be visible", locator);
        } catch (Exception e) {
            logger.error("Exception occurred while waiting for element '{}' to be visible", locator);
        }
        return null;
    }

    public WebElement getElementClickable(By locator) {
        try {
            logger.debug("Attempting to wait for element '{}' to be clickable", locator);
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
            logger.debug("Element '{}' is clickable", locator);
            return element;
        } catch (NoSuchElementException e) {
            logger.error("NoSuchElementException occurred while" +
                    " waiting for element '{}' to be clickable", locator);
        } catch (Exception e) {
            logger.error("Exception occurred while waiting for element '{}' to be clickable", locator);
        }
        return null;
    }

    /**
     * Checks if an element is displayed on the page.
     *
     * @param locator The locator used to find the element.
     * @return true if the element is displayed, false otherwise.
     */
    public boolean isElementPresent(By locator) {
        try {
            logger.debug("Attempting to check if element '{}' is displayed", locator);
            WebElement element = getElementVisible(locator);
            boolean isElementDisplayed = element != null && element.isDisplayed();
            if (isElementDisplayed) {
                logger.debug("Element '{}' is found and displayed", locator);
            } else {
                logger.debug("Element '{}' is not displayed", locator);
            }
            return isElementDisplayed;
        } catch (NoSuchElementException e) {
            logger.error("Element '{}' not found", locator);
            return false;
        } catch (Exception e) {
            logger.error("Exception occurred while checking if element '{}' is displayed", locator, e);
            return false;
        }
    }

    /**
     * Clicks on an element that is expected to be visible and clickable.
     *
     * @param locator Locator for the element, typically identified by CSS or XPath.
     */
    public void click(By locator) {
        try {
            WebElement element = getElementClickable(locator);
            if (element != null) {
                element.click();
                logger.debug("Successfully clicked on element located '{}'", locator);
            } else {
                logger.error("Element located '{}' is not clickable or not found", locator);
            }
        } catch (ElementNotInteractableException e) {
            logger.error("Element '{}' is not interactable", locator);
        } catch (Exception e) {
            logger.error("An unexpected error occurred while clicking element '{}'", locator);
        }
    }

    /**
     * Attempt to click on an element with retries if the first attempt fails.
     *
     * @param locator    Locator for the element, identified by CSS or XPath
     * @param maxRetries Maximum number of retry attempts
     */
    public void clickWithRetry(By locator, int maxRetries) {
        int attempt = 0;
        while (attempt < maxRetries) {
            try {
                click(locator);
                return;
            } catch (StaleElementReferenceException e) {
                logger.error("Stale element reference for locator: {}", locator, e);
            }
            attempt++;
        }
        logger.error("Failed to click on Element located" +
                " '{}' after '{}'", locator, maxRetries);
    }

    /**
     * Checks if the field located by the given locator is empty or contains only whitespace.
     *
     * @param locator The locator used to find the field.
     * @return true if the field is empty or contains only whitespace; false otherwise.
     */
    public boolean isFieldEmpty(By locator) {
        try {
            WebElement element = driver.findElement(locator);
            String fieldText = Objects.requireNonNull(element.getAttribute("value")).trim();
            boolean isEmpty = fieldText.isEmpty();
            if (isEmpty) {
                logger.debug("Field located '{}' is empty or contains only whitespace", locator);
            }
            return isEmpty;
        } catch (Exception e) {
            logger.error("Exception occurred while checking field located '{}'", locator);
            return true;
        }
    }

    /**
     * Inputs value into a specified field after clearing it.
     *
     * @param locator The locator used to find the input field
     * @param text    The text to input into the field
     */
    public void input(By locator, String text) {
        WebElement element = getElementClickable(locator);
        if (element != null) {
            try {
                if (isFieldEmpty(locator)) {
                    logger.debug("Field located by '{}' is already empty", locator);
                } else {
                    element.clear();
                    logger.debug("Cleared the input field located by '{}'", locator);
                }
                element.sendKeys(text);
                logger.debug("Inputted '{}' into the field located by '{}'", text, locator);
            } catch (Exception e) {
                logger.error("Exception occurred while inputting" +
                        " '{}' into field located by '{}'", text, locator);
            }
        }
    }

    /**
     * Inputs value into a specified field after clearing it.
     *
     * @param locator The locator used to find the input field
     * @param number  The number to input into the field
     */

    public void input(By locator, int number) {
        WebElement element = getElementClickable(locator);
        if (element != null) {
            try {
                if (isFieldEmpty(locator)) {
                    logger.debug("Field located by '{}' is already empty..", locator);
                } else {
                    element.clear();
                    logger.debug("Cleared number the input field located by '{}'", locator);
                }
                element.sendKeys(String.valueOf(number));
                logger.debug("Inputted number'{}' into the field located by '{}'", number, locator);
            } catch (Exception e) {
                logger.error("Exception occurred while inputting number" +
                        " '{}' into field located by '{}'", number, locator);
            }
        }
    }

    /**
     * Method to replace text in an input field.
     *
     * @param locator  The locator of the input field.
     * @param newValue The new value to enter into the input field.
     */
    public void replaceValue(By locator, String newValue) {
        WebElement inputField = getElementClickable(locator);
        if (inputField != null) {
            try {
                inputField.click();
                inputField.sendKeys(Keys.chord(Keys.CONTROL, "a"));
                inputField.sendKeys(newValue);
                logger.debug("Replaced '{}' in Element located" +
                        " '{}' successfully", newValue, locator);
            } catch (Exception e) {
                logger.error("Exception occurred while replacing" +
                        " '{}' to element located '{}'", newValue, locator);
            }
        }
    }

    /**
     * Hovers over a specified element to display a dropdown or menu.
     *
     * @param locator The locator used to find the element to hover over
     */
    public void hoverOver(By locator) {
        WebElement element = getElementClickable(locator);
        if (element != null) {
            try {
                actions.moveToElement(element).perform();
                logger.debug("Hovered over element located '{}'", locator);
            } catch (Exception e) {
                logger.error("Exception occurred while" +
                        " hovering over element '{}'", locator);
            }
        }
    }

    /**
     * Scrolls to the specified element and brings it into view.
     *
     * @param locator The locator used to find the element to scroll to.
     */
    public void scrollToTopOfElement(By locator) {
        WebElement element = getElementVisible(locator);
        try {
            jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
            logger.debug("Scrolled to top of element located by '{}'", locator);
        } catch (Exception e) {
            logger.error("Exception occurred while scrolling to top of element located by '{}'", locator);
        }
    }

    /**
     * Scrolls to the specified element and brings it into view.
     *
     * @param locator The locator used to find the element to scroll to.
     */
    public void scrollToCenterOfElement(By locator) {
        WebElement element = getElementVisible(locator);
        String scrollElementIntoMiddle = "var viewPortHeight = Math.max(document.documentElement.clientHeight, window.innerHeight || 0);" +
                "var elementTop = arguments[0].getBoundingClientRect().top;" +
                "window.scrollBy(0, elementTop - (viewPortHeight / 2));";
        try {
            jsExecutor.executeScript(scrollElementIntoMiddle, element);
            logger.debug("Scrolled to center of element located by '{}'", locator);
        } catch (Exception e) {
            logger.error("Exception occurred while scrolling to center of element located by '{}'", locator);
        }
    }

    public void scrollByAmount(int pixels) {
        jsExecutor.executeScript("window.scrollBy(0, " + pixels + ");");
    }

    public void scrollToTop() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, 0);");
    }

    /**
     * Scrolls to bottom
     */
    public void scrollToBottom() {
        jsExecutor.executeScript("window.scrollTo(0, document.body.scrollHeight);");
        logger.info("Scroll to the bottom of page");
    }

    public void acceptAlert() {
        try {
            Alert alert = driver.switchTo().alert();
            alert.accept();
            logger.debug("Click 'OK' button on Alert");
        } catch (Exception e) {
            logger.error("Exception occurred while accepting alert");
        }
    }

    public void dismissAlert() {
        try {
            Alert alert = driver.switchTo().alert();
            alert.dismiss();
            logger.info("Click 'Cancel' button on Alert");
        } catch (Exception e) {
            logger.error("Exception occurred while dismissing alert");
        }
    }



}


