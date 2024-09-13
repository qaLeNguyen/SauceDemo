package objects;

import org.openqa.selenium.By;

public enum CheckoutOverviewPageLocator {

    TEXT_OVERVIEW(Type.XPATH, "//span[@class='title' and contains(text(),'Checkout: Overview')]"),
    FIELD_QUANTITY(Type.CSS, ".cart_quantity"),
    DYNAMIC_PRODUCT_NAMES(Type.XPATH,"//div[@class='inventory_item_name' and contains(text(), '{text}')]"),
    PRODUCT_PRICE(Type.CSS,"div.inventory_item_price"),
    PRODUCT_ITEM_TOTAL(Type.CSS,"div.inventory_item_price"),
    PRODUCT_TAX(Type.CSS,"div.summary_tax_label"),
    PRODUCT_TOTAL(Type.CSS,"div.summary_total_label"),
    BUTTON_FINISH(Type.ID,"finish"),
    BUTTON_CANCEL(Type.ID,"cancel"),


    ;
    private final Type type;
    private final String locatorString;

    CheckoutOverviewPageLocator(Type type, String value) {
        this.type = type;
        this.locatorString = value;
    }

    public By getBy(String text) {
        return type.buildBy(locatorString.replace("{text}", text));
    }

    public By getBy() {
        return type.buildBy(locatorString);
    }
}
