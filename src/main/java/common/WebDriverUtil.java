package common;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WebDriverUtil {
    private static final Logger logger = LogManager.getLogger(WebDriverUtil.class);

    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private static final ThreadLocal<WebDriverWait> wait = new ThreadLocal<>();

    private WebDriverUtil() {
    }

    static String browser = System.getProperty("browser", "CHROME").toUpperCase();

    public static WebDriver getDriver() {
        if (driver.get() == null) {
            switch (browser) {
                case "FIREFOX":
                    System.setProperty("webdriver.gecko.driver",
                            "D:\\5. Webdriver\\geckodriver-v0.34.0-win-aarch64\\geckodriver.exe");
                    driver.set(new FirefoxDriver());
                    break;
                case "CHROME":
                    System.setProperty("webdriver.chrome.driver",
                            "D:\\5. Webdriver\\chromedriver-win64\\chromedriver.exe");
                    driver.set(new ChromeDriver());
                    break;
                case "EDGE":
                    System.setProperty("webdriver.edge.driver",
                            "D:\\5. Webdriver\\edgedriver_win64\\msedgedriver.exe");
                    driver.set(new EdgeDriver());
                    break;
                default:
                    throw new IllegalArgumentException("Unsupported browser: " + browser);
            }
            driver.get().manage().window().maximize();
            logger.info("WebDriver initialized for browser: {}", browser);

        }
        return driver.get();
    }

    public static WebDriverWait getWait() {
        if (wait.get() == null && driver.get() != null) {
            wait.set(new WebDriverWait(driver.get(), Duration.ofSeconds(20)));
        }
        return wait.get();
    }

    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
            wait.remove();
            logger.info("WebDriver quit for thread: {}", Thread.currentThread().threadId());
        }
    }
}
