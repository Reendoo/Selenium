package sk.peterrendek.beginer;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.junit.jupiter.api.Assertions.*;

public class ZenaAleboMuzTest {
    private WebDriver driver;
    private static final String BASE_URL = "http://localhost:8888/zenaalebomuz.php";

    @BeforeEach
    public void setUp() {
        driver = new FirefoxDriver();
        driver.get(BASE_URL);
    }

    @Test
    public void test() {
        String text = "//h1[@class='description text-center']";
        String wurst = "//input[@value='wurst']";
        String conchita = "//input[@value='conchita']";
        assertTrue(driver.findElement(By.xpath(text)).getText().isEmpty(), "Initial Text should be empty");
        assertFalse(driver.findElement(By.xpath(wurst)).isSelected(), "Initial Wurst should ne selected");
        assertFalse(driver.findElement(By.xpath(conchita)).isSelected(), "Initial Conchita should be selected");

        driver.findElement(By.xpath(wurst)).click();
        assertEquals("It's wurst", driver.findElement(By.xpath(text)).getText(), "Should be => It's wurst");
        assertTrue(driver.findElement(By.xpath(wurst)).isSelected(), " should true, Wurst is selected");
        assertFalse(driver.findElement(By.xpath(conchita)).isSelected(), "should false, Wurst is selected");

        driver.findElement(By.xpath(conchita)).click();
        assertEquals("It's conchita", driver.findElement(By.xpath(text)).getText(), "Should be => It's conchita");
        assertFalse(driver.findElement(By.xpath(wurst)).isSelected(), " should false, Conchita is selected");
        assertTrue(driver.findElement(By.xpath(conchita)).isSelected(), "should true, Wurst is selected");

    }

    @AfterEach
    public void tearDown() {
//        driver.close();
        driver.quit();
    }

}
