package common;

public class Constant {

    public enum Credentials {

        STANDARD_USER("standard_user","secret_sauce"),
        LOCKOUT_USER("locked_out_user","secret_sauce"),
        PROBLEM_USER("problem_user","secret_sauce"),
        PERFORMANCE_GLITCH_USER("performance_glitch_user","secret_sauce"),
        ERROR_USER("error_user","secret_sauce"),
        VISUAL_USER("visual_user","secret_sauce"),
        INVALID_USER("leNguyen","GreySecret"),

        ;
        private final String username;
        private final String password;

        Credentials(String name, String pwd) {
            this.username = name;
            this.password = pwd;
        }

        public String getUsername() {
            return username;
        }

        public String getPwd() {
            return password;
        }
    }

    public enum Url{

        HOME_PAGE("https://www.saucedemo.com/inventory.html"),
        CART_PAGE("https://www.saucedemo.com/cart.html"),
        CHECKOUT_INFO_PAGE("https://www.saucedemo.com/checkout-step-one.html"),
        CHECKOUT_OVERVIEW_PAGE("https://www.saucedemo.com/checkout-step-two.html"),

        ;
        private final String url;

        Url(String urlValue) {
            this.url = urlValue;
        }

        public String getUrl() {
            return url;
        }
    }

    public enum Title {

        LOGIN_PAGE("Swag Labs");

        ;
        private final String title;

        Title(String titleValue) {
            this.title = titleValue;
        }

        public String getTitle() {
            return title;
        }
    }

}
