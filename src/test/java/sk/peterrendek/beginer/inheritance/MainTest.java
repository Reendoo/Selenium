package sk.peterrendek.beginer.inheritance;

import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class MainTest {
    protected  WebDriver driver;
    protected  String BASE_URL;

    @BeforeEach
     void beforeEachHelper() {
        BASE_URL = "http://localhost:8888/";
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @AfterEach
     public void afterEachHelper() {
        driver.close();
        driver.quit();
    }

}
