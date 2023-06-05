package sk.peterrendek.advanced.tests;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import sk.peterrendek.advanced.base.TestBase;

import java.time.Duration;


public class UseJavaScriptInTest extends TestBase {
    @BeforeEach
    void beforeEach() {
        getDriver().get(BASE_URL + "tabulka.php");
    }

    @Test
    void selectFlorianRowsTest() {
        getDriver().findElements(By.xpath("//table//tbody//tr"))
                .forEach(webElement -> {
                    JavascriptExecutor je = ((JavascriptExecutor) getDriver());
                    if (webElement.getText().contains("Florian")) {
                        highlightRow(webElement, je);
                    }
                });

    }

    @Tag("SmokeTest")
    @Test
    void blurTest() {
        getDriver().findElement(By.xpath("//a[@href='waitforit.php']")).click();
        WebElement inputElement = getDriver().findElement(By.id("waitForBlur"));
        inputElement.sendKeys("type something");
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].blur()", inputElement);
        new WebDriverWait(getDriver(), Duration.ofSeconds(10))
                .withMessage("blured ! didnt appear")
                .until(ExpectedConditions.attributeToBe(inputElement, "value", "blured!"));

        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].click()", getDriver().findElement(By.id("startWaitForText")));
    }

    private static void highlightRow(WebElement webElement, JavascriptExecutor je) {
        je.executeScript("arguments[0].style.border='3px solid red'", webElement);
    }
}
