package sk.peterrendek.beginer;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ZjavenieTest {
    private WebDriver driver;
    private static final String BASE_URL = "http://localhost:8888/zjavenie.php";

    @BeforeEach
    public void setUp() {
        driver = new FirefoxDriver();
        driver.get(BASE_URL);
    }

    @Test
    public void test() {
        driver.findElement(By.id("showHim")).click();
        // Thread.sleep(2_500); -- bad habbit
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@class='brano']")));

        assertTrue(driver.findElement(By.xpath("//img[@class='brano']")).isDisplayed(),"Picture should be displayed");
    }

    @AfterEach
    public void tearDown() {
//        driver.close();
        driver.quit();
    }

}
