package sk.peterrendek.beginer;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.JavascriptExecutor;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class VerifyTest {
    private WebDriver driver;
    private Map<String, Object> vars;
//    private JavascriptExecutor js;

    @BeforeEach
    public void setUp() {
        driver = new FirefoxDriver();
        driver.get("http://localhost:8888/tabulka.php");
//        js = (JavascriptExecutor) driver;
        vars = new HashMap<>();

    }

    @AfterEach
    public void tearDown() {
        driver.quit();
        if (vars.isEmpty()) {
            System.out.println("Success!");
            return;
        }
        System.out.println("problems ===>");
        vars.forEach((key, value) -> System.out.println(key + "\n" + value));
        fail();
    }

    @Test
    void test1() {
        try {
//            assertTrue(false);
            assertTrue(true);
        } catch (Error e) {
            vars.put("test1", e);
        }

    }

    @Test
    public void test2() {
        try {
            assertEquals("1", driver.findElement(By.cssSelector("tr:nth-child(1) > td:nth-child(1)")).getText());
            assertEquals("2", driver.findElement(By.cssSelector("tr:nth-child(2) > td:nth-child(1)")).getText());
        } catch (Error e) {
            vars.put("test2", e);
        }
    }
}
