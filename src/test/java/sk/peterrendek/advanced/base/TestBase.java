package sk.peterrendek.advanced.base;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

public class TestBase {
    public static final String BASE_URL = "http://localhost:8888/";

    @BeforeEach
    void beforeEachHelper() {
        WebDriverSingleton.getInstance().initialize();
//        WebDriverSingleton.getWebdriverInstance().manage().window().maximize();
    }

    @AfterEach
    public void afterEachHelper() {
        if (WebDriverSingleton.getInstance().getDriver() instanceof FirefoxDriver) {
            System.out.println("Firefox");
            WebDriverSingleton.getInstance().getDriver().quit();
            return;
        }
        if (WebDriverSingleton.getInstance().getDriver() instanceof ChromeDriver) {
            WebDriverSingleton.getInstance().getDriver().close();
            WebDriverSingleton.getInstance().getDriver().quit();
            System.out.println("Chrome");
            return;
        }
        if (WebDriverSingleton.getInstance().getDriver() instanceof RemoteWebDriver) {
            System.out.println("Remote");
            WebDriverSingleton.getInstance().getDriver().quit();
            return;
        }
        System.out.println("Iny");
    }

    public WebDriver getDriver() {
        return WebDriverSingleton.getInstance().getDriver();
    }

}
