package sk.peterrendek.beginer;


import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.junit.jupiter.api.Assertions.*;


public class ClickMeBabyTest {
    private WebDriver driver;
    private static final String BASE_URL = "http://localhost:8888/clickmebaby.php";

    @BeforeEach
    public void setUp() {
        driver = new FirefoxDriver();
        driver.get(BASE_URL);
    }

    @Test
    public void test() {
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
