package sk.peterrendek.advanced.base;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.RemoteWebDriver;
import sk.peterrendek.advanced.enums.BrowserType;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;


public class WebDriverSingleton {
    private static WebDriverSingleton instance;
    public final int Options = 4;
    private WebDriver driver;
    void initialize() {
        String browser = System.getProperty("varBrowserType");
        String varParalelCount = System.getProperty("varParalelCount");
        System.out.println("Browser: " + browser + " threadsCount:" + varParalelCount);
        browser = "chrome";
        getCapabilities(browser);

        switch (Options) {
            case 1 -> initializeSeleniumServerViaChrome(); // dojebane asi verzie
            case 2 -> initializeFirefoxDriver();
            case 3 -> initializeVisibleChromeDriver();
            case 4 -> initializeInvisibleChromeDriver();
        }
    }

    public WebDriver getDriver() {
        return driver;
    }

    public static WebDriverSingleton getInstance() {
        if (instance == null) {
            instance = new WebDriverSingleton();
        }
        return instance;
    }

    private void initializeSeleniumServerViaChrome() {
        URL url;
        try {
            url = URI.create("http://localhost:4444/wd/hub").toURL();
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--headless");
        chromeOptions.setCapability("browserVersion", "112");
        chromeOptions.setCapability("platformName", "Windows 10");
        driver = new RemoteWebDriver(url, chromeOptions);
    }

    private static Capabilities getCapabilities(String browserName) {
        Capabilities cap;
        switch (BrowserType.getValue(browserName)) {
            case CHROME -> cap = new ChromeOptions();   // dojebane asi verzie
            case FIREFOX -> cap = new FirefoxOptions();
            default -> {
                cap = null;
                throw new RuntimeException("Unidentified browser");
            }
        }
        return cap;
    }

    private void initializeFirefoxDriver() {
        FirefoxProfile profile = new FirefoxProfile();
        profile.setPreference("browser.download.folderList", 2);
        profile.setPreference("browser.download.dir", "C:\\Users\\rendo\\OneDrive\\Plocha\\Testing");
        profile.setPreference("browser.helperApps.neverAsk.saveToDisk", "application/zip");
        profile.setPreference("general.useragent.override", "Mozilla/5.0 (iPhone; CPU iPhone OS 13_3_1 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) FxiOS/112.0 Mobile/15E148 Safari/605.1.15");
        FirefoxOptions options = new FirefoxOptions();
        options.setProfile(profile);
        driver = new FirefoxDriver(options);
    }

    private void initializeVisibleChromeDriver() {
        driver = new ChromeDriver();
    }

    private void initializeInvisibleChromeDriver() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
    }
}
