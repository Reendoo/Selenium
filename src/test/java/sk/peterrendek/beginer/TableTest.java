package sk.peterrendek.beginer;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.junit.jupiter.api.Assertions.*;

public class TableTest {
    private WebDriver driver;
    private static final String BASE_URL = "http://localhost:8888/tabulka.php";

    @BeforeAll
    static void beforeAll() {
    }

    @BeforeEach
    void setUp() {
        driver = new FirefoxDriver();
        driver.get(BASE_URL);
    }

    @Test
    public void testFirstWay() {
        int rowCount = Integer.parseInt(driver.findElement(By.xpath("//table/tbody/tr[last()]/td[1]")).getText());
        for (int i = 1; i <= rowCount; i++) {
            for (int j = 2; j <= 4; j++) {
                assertFalse(driver.findElement(By.xpath("//table/tbody/tr[" + i + "]/td[" + j + "]")).getText().isEmpty(), "Should not be empty");
            }
        }
    }

    @Test
    void testSecondWay() {
        driver.findElements(By.xpath("//table/tbody/tr"))
                .forEach(s -> {
                    assertFalse(s.findElement(By.xpath("./td[2]")).getText().isEmpty(), "name, Should not be empty");
                    assertFalse(s.findElement(By.xpath("./td[3]")).getText().isEmpty(), "surname, Should not be empty");
                    assertFalse(s.findElement(By.xpath("./td[4]")).getText().isEmpty(), "id, Should not be empty");
                });
    }

    @AfterEach
    public void afterEach() {
        driver.quit();
    }


}
