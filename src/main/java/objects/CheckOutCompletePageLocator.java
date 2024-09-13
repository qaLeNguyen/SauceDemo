package objects;

import org.openqa.selenium.By;

public enum CheckOutCompletePageLocator {

    BUTTON_BACK_HOME(Type.ID,"back-to-products"),
    TEXT_COMPLETE(Type.CSS,".title"),
    TEXT_THANK_YOU_FOR_YOUR_ORDER(Type.CSS,".complete-header"),


    ;
    private final Type type;
    private final String locatorString;

    CheckOutCompletePageLocator(Type type, String value) {
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
