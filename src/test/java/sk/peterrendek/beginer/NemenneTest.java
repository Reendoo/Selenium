package sk.peterrendek.beginer;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.junit.jupiter.api.Assertions.*;


public class NemenneTest {
    private WebDriver driver;
    private static final String BASE_URL = "http://localhost:8888/nemenne.php";

    @BeforeEach
    public void setUp() {
        driver = new FirefoxDriver();
        driver.get(BASE_URL);
    }

    @Test
    public void test() {
        assertFalse(driver.findElement(By.id("inputEmail3")).isEnabled(), "should be disabled");
        assertFalse(driver.findElement(By.id("sel1")).isEnabled(), "should be disabled");

        WebElement select = driver.findElement(By.id("sel2"));
        assertTrue(select.isEnabled(), "should be enabled");

        assertFalse(select.findElement(By.xpath("./option[1]")).isEnabled(), "1. option should be disabled");
        assertFalse(select.findElement(By.xpath("./option[2]")).isEnabled(), "2. option should be disabled");
        assertTrue(select.findElement(By.xpath("./option[3]")).isEnabled(), "3. option should be enabled");

        assertFalse(driver.findElement(By.xpath("//button[@class='btn btn-default btn-block btn-danger']")).isEnabled(), "should be disabled");
        driver.findElement(By.xpath("//button[@class='btn btn-default btn-block btn-danger']")).click();
        assertTrue(driver.findElement(By.xpath("//button[@class='btn btn-default btn-block btn-success']")).isEnabled(), "should be enabled");
    }


    @AfterEach
    public void tearDown() {
//        driver.close();
        driver.quit();
    }

}
