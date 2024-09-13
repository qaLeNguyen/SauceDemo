package objects;

import org.openqa.selenium.By;

public enum CheckoutInfoPageLocator {

    FIELD_FIRSTNAME(Type.ID, "first-name"),
    FIELD_LASTNAME(Type.ID, "last-name"),
    FIELD_ZIPCODE(Type.ID, "postal-code"),
    BUTTON_CANCEL(Type.ID, "cancel"),
    BUTTON_CONTINUE(Type.ID, "continue"),

    ;
    private final Type type;
    private final String locatorString;

    CheckoutInfoPageLocator(Type type, String value) {
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
