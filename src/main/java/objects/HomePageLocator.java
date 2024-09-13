package objects;

import org.openqa.selenium.By;

public enum HomePageLocator {

    BURGER_MENU(Type.ID,"react-burger-menu-btn"),
    SIDEBAR_BUTTON_LOG_OUT(Type.ID,"logout_sidebar_link"),
    SIDEBAR_BUTTON_ALL_ITEMS(Type.ID,"inventory_sidebar_link"),
    SIDEBAR_BUTTON_ABOUT(Type.ID,"about_sidebar_link"),
    SIDEBAR_BUTTON_RESET(Type.ID,"reset_sidebar_link"),
    LOGO(Type.CSS,".app_logo"),
    MENU_SHOPPING(Type.CSS,".shopping_cart_link"),
    BUTTON_CHECKOUT(Type.ID,"checkout"),
    BUTTON_CONTINUE_SHOPPING(Type.ID,"continue-shopping"),
    DYNAMIC_PRODUCT_NAMES(Type.XPATH,"//div[@class='inventory_item']//div[@class='inventory_item_name' and text()='{text}']"),
    PRODUCT_NAME(Type.XPATH,"//div[@class='inventory_list']/div[contains(.,'{text}')]"),
    PRODUCT_PRICE(Type.XPATH,"//div[@class='inventory_list']/div[contains(.,'{text}')]"),


    ;
    private final Type type;
    private final String locatorString;

    HomePageLocator(Type type, String value) {
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
