package sk.peterrendek.beginer;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


public class RegistraciaTest {
    private WebDriver driver;
    private static final String BASE_URL = "http://localhost:8888/registracia.php";

    @BeforeEach
    public void setUp() {
        driver = new FirefoxDriver();
        driver.get(BASE_URL);
    }

    @Test
    public void test() {
        driver.findElement(By.name("email")).sendKeys("fsdjsfdh@gmail.com");
        driver.findElement(By.name("name")).sendKeys("Peter");
        driver.findElement(By.xpath("/html/body/div/div/form/div[3]/input")).sendKeys("rendek");
        driver.findElement(By.xpath("//form/div[4]/input")).sendKeys("heslo");
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

}
