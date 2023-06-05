package sk.peterrendek.beginer;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class RegistraciaAdvancedTest {
    public static final String EMAIL = "peter@rendek.sk";
    public static final String NAME = "Peter";
    public static final String SURNAME = "Rendek";
    public static final String HESLO = "heslo";
    public static final String ALERT_DANGER_PATH = "//div[contains(@class,'alert-danger')]";
    public static final String BUTTON_TYPE_SUBMIT_PATH = "//button[@type='submit']";
    private WebDriver driver;
    private static final String BASE_URL = "http://localhost:8888/registracia.php";

    @BeforeAll
    public static void beforeAll() {

    }

    @BeforeEach
    void beforeEach() {
        driver = new ChromeDriver();
        driver.get(BASE_URL);
        driver.manage().window().maximize();
    }

    @Test
    void testCheckBox() {
        driver.findElement(By.id("checkbox")).click();
        driver.findElement(By.xpath(BUTTON_TYPE_SUBMIT_PATH)).click();
        assertTrue(driver.findElement(By.xpath(ALERT_DANGER_PATH)).isDisplayed());
    }

    @Test
    void testMissingPasswords() {
        driver.manage().window().maximize();
        driver.findElement(By.id("checkbox")).click();
        driver.findElement(By.name("email")).sendKeys(EMAIL);
        driver.findElement(By.name("name")).sendKeys(NAME);
        driver.findElement(By.name("surname")).sendKeys(SURNAME);
        driver.findElement(By.xpath(BUTTON_TYPE_SUBMIT_PATH)).click();
        assertTrue(driver.findElement(By.xpath(ALERT_DANGER_PATH)).isDisplayed());
    }

    @Test
    public void testMismatchedPassword() {
        driver.manage().window().maximize();
        driver.findElement(By.id("checkbox")).click();
        driver.findElement(By.name("email")).sendKeys(EMAIL);
        driver.findElement(By.name("name")).sendKeys(NAME);
        driver.findElement(By.name("surname")).sendKeys(SURNAME);
        driver.findElement(By.name("password")).sendKeys("dfgfdgfd54564");
        driver.findElement(By.name("password-repeat")).sendKeys("werewrew545456");
        driver.findElement(By.xpath(BUTTON_TYPE_SUBMIT_PATH)).click();
        assertTrue(driver.findElement(By.xpath(ALERT_DANGER_PATH)).isDisplayed());
    }

    @Test
    void testMissingRobotCheckbox() {
        driver.findElement(By.name("email")).sendKeys(EMAIL);
        driver.findElement(By.name("name")).sendKeys(NAME);
        driver.findElement(By.name("surname")).sendKeys(SURNAME);
        driver.findElement(By.name("password")).sendKeys(HESLO);
        driver.findElement(By.name("password-repeat")).sendKeys(HESLO);
        driver.findElement(By.xpath(BUTTON_TYPE_SUBMIT_PATH)).click();
        assertTrue(driver.findElement(By.xpath(ALERT_DANGER_PATH)).isDisplayed());
    }

    @Test
    void testSuccessfulRegistration() {
        driver.findElement(By.name("email")).sendKeys(EMAIL);
        driver.findElement(By.name("name")).sendKeys(NAME);
        driver.findElement(By.name("surname")).sendKeys(SURNAME);
        driver.findElement(By.name("password")).sendKeys(HESLO);
        driver.findElement(By.name("password-repeat")).sendKeys(HESLO);
        driver.findElement(By.id("checkbox")).click();
        driver.findElement(By.xpath(BUTTON_TYPE_SUBMIT_PATH)).click();
        assertTrue(driver.findElement(By.xpath("//div[contains(@class,'alert-success')]")).isDisplayed());
    }

    @Test
    void testInputErrorBorder() {
        driver.findElement(By.xpath(BUTTON_TYPE_SUBMIT_PATH)).click();
        driver.findElements(By.xpath("//div[input]"))
                .forEach(webElement ->
                        assertTrue(webElement.getAttribute("class").contains("has-error"))
                );
        assertTrue(driver.findElement(By.xpath("//div[label[input[@id='checkbox']]]")).getAttribute("class").contains("has-error"));
    }

    @AfterEach
    public void tearDown() {
        driver.close();
        driver.quit();
    }

}
