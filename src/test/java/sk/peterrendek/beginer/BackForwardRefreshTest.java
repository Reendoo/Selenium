package sk.peterrendek.beginer;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class BackForwardRefreshTest {
    private WebDriver driver;
    private static final String BASE_URL = "http://localhost:8888/registracia.php";

    @BeforeEach
    public void setUp() {
        driver = new FirefoxDriver();
        driver.get(BASE_URL);
        driver.manage().window().maximize();
    }

    @Test
    public void test() {
        String s = "fsdjsfdh@gmail.com";
        driver.findElement(By.name("email")).sendKeys(s);
        driver.findElement(By.xpath("//a[@href='zenaalebomuz.php']")).click();
        driver.navigate().back();
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.presenceOfElementLocated(By.name("email")));
        driver.navigate().forward();

        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//img[@src='img/conchita.jpg']")));
        assertTrue(driver.findElement(By.xpath("//img[@src='img/conchita.jpg']")).isDisplayed());

        driver.findElement(By.xpath("//a[@href='clickmebaby.php']")).click();
        for (int i = 0; i < 10; i++) {
            clickmeBaby();
            driver.navigate().refresh();
            new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.presenceOfElementLocated(By.id("clicks")));
        }

    }

    private void clickmeBaby() {
        assertEquals("0", driver.findElement(By.id("clicks")).getText(), "Initial should be 0");
        assertEquals("klikov", driver.findElement(By.xpath("/html/body/div/div[2]/p")).getText(), "Initial should be >>> klikov");

        for (int i = 1; i <= 10; i++) {
            driver.findElement(By.id("clickMe")).click();
            assertEquals(String.valueOf(i), driver.findElement(By.id("clicks")).getText(), "test incrementation");

            if (i == 1) {
                assertEquals("klik", driver.findElement(By.xpath("/html/body/div/div[2]/p")).getText(), "should be >>> 1 klik");
                continue;
            }
            if (i < 5) {
                assertEquals("kliky", driver.findElement(By.xpath("/html/body/div/div[2]/p")).getText(), "should be >>> 2,3,4 kliky");
                continue;
            }

            assertEquals("klikov", driver.findElement(By.xpath("/html/body/div/div[2]/p")).getText(), "should be >>> 5,6,...,n klikov");
        }

    }


    @AfterEach
    public void tearDown() {
//        driver.close();
        driver.quit();
    }

}
