package sk.peterrendek.beginer;


//import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import java.io.File;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class CrashPrintScreenTest {
    private WebDriver driver;
    private static final String BASE_URL = "http://localhost:8888/vybersi.php";

    @BeforeEach
    public void setUp() {
        driver = new FirefoxDriver();
        driver.get(BASE_URL);
    }

    @Test
    public void test1() {
        new Select(driver.findElement(By.className("form-control"))).selectByVisibleText("Pikachu");
        String text = driver.findElement(By.xpath("//div[contains(@class,'pokemon')]/h3")).getText();
        assertTrue(text.contains("Pikachu"));
    }

    @Test
    public void test2() {
        new Select(driver.findElement(By.className("form-control"))).selectByVisibleText("Bulbasaur");
        String text = driver.findElement(By.xpath("//div[contains(@class,'pokemon')]/h3")).getText();
        assertTrue(text.contains("Bulbasaur"));
    }


    @AfterEach
    public void tearDown() throws IOException {
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenshot, new File("C://Users//rendo//OneDrive//Plocha//Testing//sc.png"));
        System.out.println(driver.getPageSource());
//        driver.close();
        driver.quit();
    }

}
