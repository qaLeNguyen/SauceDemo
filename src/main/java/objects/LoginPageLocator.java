package objects;

import org.openqa.selenium.By;

public enum LoginPageLocator {

    FIELD_INPUT_USERNAME(Type.ID,"user-name"),
    FIELD_INPUT_PWD(Type.ID,"password"),
    BUTTON_LOGIN(Type.ID,"login-button"),
    ERROR_MSG(Type.CSS, ".error-message-container"),


    ;

    private final Type type;
    private final String locatorString;

    LoginPageLocator(Type type, String value) {
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
