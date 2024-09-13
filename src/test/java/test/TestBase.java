package test;

import common.WebDriverUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import pages.HomePage;
import pages.LoginPage;
import verification.Verify;


public class TestBase {
    private static final Logger logger = LogManager.getLogger(TestBase.class);

    protected WebDriver driver;
    protected Verify verify;
    protected LoginPage loginPage;
    protected HomePage homePage;

    @BeforeClass
    public void setUpClass() {
        this.driver = WebDriverUtil.getDriver();
        this.verify = new Verify();
        this.loginPage = new LoginPage();
        this.homePage = new HomePage();

        logger.info("setUpClass is completed. Initialize all Objects");
    }

    @BeforeMethod
    public void setUpMethod() {
        driver.get("https://www.saucedemo.com/");
        verify.titleOfLoginPage();
        logger.info("setUpMethod is completed. Navigated to homepage url");
    }

    @AfterMethod
    public void tearDownMethod() {
        if (driver != null) {
            driver.manage().deleteAllCookies();
            logger.info("tearDownMethod is completed. Driver clear all cookies");
        }
    }

    @AfterClass
    public void tearDownClass() {
        driver.quit();
        logger.info("tearDownClass is completed. Driver quit");
    }

}
