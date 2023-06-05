package sk.peterrendek.beginer;


import org.junit.jupiter.api.*;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.junit.jupiter.api.Assertions.*;


public class RedAlertTest {
    private WebDriver driver;
    private static final String BASE_URL = "http://localhost:8888/redalert.php";

    @BeforeEach
    public void setUp() {
        driver = new FirefoxDriver();
        driver.get(BASE_URL);
    }


    @Test
    public void test() {
        String xPath = "//div[@class='result']/h1";

        driver.findElement(By.id("alert1")).click();
        Alert alert = driver.switchTo().alert();
        alert.accept();
        driver.switchTo().defaultContent();
        assertEquals("Kirov Reporting", driver.findElement(By.xpath(xPath)).getText(), "Should be >>> Kirov Reporting");

        driver.findElement(By.id("alert2")).click();
        alert = driver.switchTo().alert();
        alert.accept();
        driver.switchTo().defaultContent();
        assertEquals("Helium mix optimal", driver.findElement(By.xpath(xPath)).getText(), "Should be >>> Helium mix optimal");

        driver.findElement(By.id("alert2")).click();
        alert = driver.switchTo().alert();
        alert.dismiss();
        assertEquals("Negative", driver.findElement(By.xpath(xPath)).getText(), "Should be >>> Negative");

        String commander = "George S. Patton";
        driver.findElement(By.id("alert3")).click();
        alert = driver.switchTo().alert();
        alert.sendKeys(commander);
        alert.dismiss();
        driver.switchTo().defaultContent();
        System.out.println();
        assertEquals("Negative", driver.findElement(By.xpath(xPath)).getText(), "Should be >>> Negative");

        driver.findElement(By.id("alert3")).click();
        alert = driver.switchTo().alert();
        alert.sendKeys(commander);
        alert.accept();
        driver.switchTo().defaultContent();
        assertTrue(driver.findElement(By.xpath(xPath)).getText().contains(commander), "Should be >>> shouldn't contains" + commander);
        assertEquals("Waiting for your commands " + commander, driver.findElement(By.xpath(xPath)).getText(), "Should be >>> Waiting for your commands " + commander);
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

}
