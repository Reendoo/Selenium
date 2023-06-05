package sk.peterrendek.beginer;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.Color;

public class CSSTest {
    private WebDriver driver;
    private static final String BASE_URL = "http://localhost:8888/stroopeffect.php";

    @BeforeEach
    public void setUp() {
        driver = new FirefoxDriver();
        driver.get(BASE_URL);
    }

    @Test
    public void test() {
        WebElement testElementCSS = driver.findElement(By.xpath("//div[contains(@class,'colours')]/h1[1]"));
        System.out.println("text: " + testElementCSS.getText());
        System.out.println("color rgb: " + testElementCSS.getCssValue("color"));
        System.out.println("color HEXA: " + Color.fromString(testElementCSS.getCssValue("color")).asHex());
        System.out.println("font-size+ " + testElementCSS.getCssValue("font-size"));
        System.out.println("font-family :" + testElementCSS.getCssValue("font-family"));
        System.out.println("font-style: " + testElementCSS.getCssValue("font-style"));
        System.out.println("background-color: " + testElementCSS.getCssValue("background-color"));
    }

    @AfterEach
    public void tearDown() {
//        driver.close();
        driver.quit();
    }

}
