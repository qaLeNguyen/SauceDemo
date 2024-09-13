package test;

import common.Constant;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utilisation.DataProviderService;

import java.util.List;

public class TestFindProducts extends TestBase{
    private static final Logger logger = LogManager.getLogger(TestLogin.class);

    @BeforeMethod
    @Override
    public void setUpMethod() {
        super.setUpMethod();

        loginPage.loginSauceDemo(
                Constant.Credentials.STANDARD_USER.getUsername(),
                Constant.Credentials.STANDARD_USER.getPwd());
        homePage.clickButtonOKOfTheLoggedInAlert();
        verify.urlOfLoginSuccessful();
        verify.homepageLogo();
        logger.info("Override setUpMethod is completed");
    }

    @Story("Find some products")
    @Test(dataProvider = "productNames", dataProviderClass = DataProviderService.class)
    public void findProduct(String name) {
        homePage.findProducts(List.of(name));
    }

    @Story("Find some products and add to cart")
    @Test(dataProvider = "productNames", dataProviderClass = DataProviderService.class)
    public void findProductsAndAddToCart(String name) {
        homePage.findProductsAndAddToCart(List.of(name));
    }





}
