package sk.peterrendek.advanced.tests;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import sk.peterrendek.advanced.base.TestBase;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

public class WaitingForSMNTHTest extends TestBase {
    @BeforeEach
    void beforeEach() {
        getDriver().get(BASE_URL+"waitforit.php");
    }

    @Test
    void waitForItTest() {
        getDriver().findElement(By.id("startWaitForText")).click();
        WebElement waitInput = getDriver().findElement(By.id("waitForTextInput"));
        new WebDriverWait(getDriver(), Duration.ofSeconds(10)).until(ExpectedConditions.attributeToBe(waitInput, "value", "dary !!!"));
        assertTrue(waitInput.getAttribute("value").contains("dary !!!"), "should be true");
    }

    @Test
    void waitForGetPropertyTest() {
        WebElement button = getDriver().findElement(By.id("startWaitForProperty"));
        button.click();
        WebElement waitForPropertyInput = getDriver().findElement(By.id("waitForProperty"));

        new WebDriverWait(getDriver(), Duration.ofSeconds(10)).until(ExpectedConditions.attributeContains(waitForPropertyInput, "class", "form-control error"));

        assertFalse(button.isEnabled(), "should button should be disabled");
    }

    @Test
    void waitForGetPropertyTest2() {
        WebElement button = getDriver().findElement(By.id("startWaitForProperty"));
        button.click();
        WebElement waitForPropertyInput = getDriver().findElement(By.id("waitForProperty"));

        new WebDriverWait(getDriver(), Duration.ofSeconds(10)).until(ExpectedConditions.attributeToBe(waitForPropertyInput, "class", "form-control error"));

        assertFalse(button.isEnabled(), "should button should be disabled");
    }

    @Test
    void waitForGetPropertyTest3() {
        WebElement button = getDriver().findElement(By.id("startWaitForProperty"));
        button.click();

        new WebDriverWait(getDriver(), Duration.ofSeconds(10))
                .until(ExpectedConditions.attributeToBeNotEmpty(button, "disabled"));
        assertFalse(button.isEnabled(), "should button should be disabled");
    }

    @Test
    void waitForNumberElements() {
        getDriver().findElement(By.xpath("//a[@href='minions.php']")).click();
        WebElement input = getDriver().findElement(By.xpath("//input[@class='form-control']"));
        int inputValue = 6;
        input.sendKeys(String.valueOf(inputValue));
        getDriver().findElement(By.className("btn-warning")).click();
        new WebDriverWait(getDriver(), Duration.ofSeconds(10))
                .withMessage("Mimons should be: " + inputValue)
                .until(ExpectedConditions.numberOfElementsToBe(
                        By.xpath("//div[@class='minions']//li"),
                        inputValue));
        assertEquals(String.valueOf(inputValue), input.getAttribute("value"));
        assertEquals(inputValue, getDriver().findElements(By.xpath("//div[@class='minions']//li")).size());
        assertEquals(inputValue, getDriver().findElements(By.xpath("//div[@class='minions']//img")).size());
    }

    @Test
    @Disabled
    void testingVisibility() {
        getDriver().findElement(By.xpath("//a[@href='prestige.php']")).click();
        WebElement hat = getDriver().findElement(By.cssSelector("div.hat img"));
        hat.click();

        new WebDriverWait(getDriver(), Duration.ofSeconds(20))
                .withMessage("Hat shouldn't be: visible ")
                .until(ExpectedConditions.invisibilityOf(hat));

        new WebDriverWait(getDriver(), Duration.ofSeconds(20))
                .withMessage("Hat should be: visible ")
                .until(ExpectedConditions.visibilityOf(hat));

        System.out.println(hat.getAttribute("style"));
        assertTrue(hat.getAttribute("style").isEmpty());
    }
}
