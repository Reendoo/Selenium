package sk.peterrendek.beginer;

import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class MouseActionsTest {
    private WebDriver driver;
    private static final String BASE_URL = "http://localhost:8888/semafor.php";

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.get(BASE_URL);
    }

    @Test
    public void test() throws InterruptedException {
        String expectedGreen;
        String expectedRed;
        String expectedOrange;
        if (driver instanceof ChromeDriver) {
            expectedGreen = "rgba(10, 129, 0, 1)";
            expectedRed = "rgba(205, 58, 63, 1)";
            expectedOrange = "rgba(191, 111, 7, 1)";
            System.out.println("Chrome");
        } else if (driver instanceof FirefoxDriver) {
            expectedGreen = "rgb(10, 129, 0)";
            expectedRed = "rgb(205, 58, 63)";
            expectedOrange = "rgb(191, 111, 7)";
            System.out.println("Firefox");
        } else {
            expectedGreen = "rgb(10, 129, 0)";
            expectedRed = "rgb(205, 58, 63)";
            expectedOrange = "rgb(191, 111, 7)";
            System.out.println("cosi ine");
        }

        WebElement element = driver.findElement(By.className("light"));
        assertEquals(expectedRed, element.getCssValue("background-color"), "Initial color should be red)");

        Actions action = new Actions(driver);
        action.moveToElement(element).build().perform();
        assertEquals(expectedGreen, element.getCssValue("background-color"), " hover should be green)");

        action.clickAndHold(element).build().perform();
        assertEquals(expectedOrange, element.getCssValue("background-color"), " hold(active) color should be orange)");

        driver.manage().window().maximize();
        driver.findElement(By.xpath("//a[@href='moveme.php']")).click();
        WebElement donald = driver.findElement(By.id("donald"));
        WebElement tree = driver.findElement(By.id("tree"));
        action.dragAndDrop(donald, tree).build().perform();

        String xpath = "//div[contains(@class,'success')]/h2";

        assertTrue(driver.findElement(By.xpath(xpath)).isDisplayed());
        assertEquals("HOOO HOOOOO !!!!", driver.findElement(By.xpath(xpath)).getText());
        for (int i = 0; i < 10; i++) {
            action.clickAndHold(donald).moveByOffset(-100, 0).release().build().perform();
            Thread.sleep(100);
        }

    }


    @AfterEach
    public void tearDown() throws IOException {
        driver.quit();
    }

}
