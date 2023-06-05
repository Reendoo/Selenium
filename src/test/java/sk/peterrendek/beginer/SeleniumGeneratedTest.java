package sk.peterrendek.beginer;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
//import java.util.HashMap;
//import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SeleniumGeneratedTest {
    private WebDriver driver;
    //    private Map<String, Object> vars;
    JavascriptExecutor js;


    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        js = (JavascriptExecutor) driver;
//        vars = new HashMap<String, Object>();
        driver.get("http://localhost:8888/");
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void clickMe1X() {
        driver.findElement(By.linkText("Click me baby")).click();
        driver.findElement(By.id("clickMe")).click();
        assertEquals(driver.findElement(By.id("clicks")).getText(), "1");
        assertEquals(driver.findElement(By.cssSelector(".description")).getText(), "klik");
    }
}
