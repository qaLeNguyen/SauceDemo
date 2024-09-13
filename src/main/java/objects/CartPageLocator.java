package objects;

import org.openqa.selenium.By;

public enum CartPageLocator {

    DYNAMIC_PRODUCT_NAMES(Type.XPATH,"//div[@class='inventory_item_name' and contains(text(), '{text}')]"),



    ;
    private final Type type;
    private final String locatorString;

    CartPageLocator(Type type, String value) {
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
