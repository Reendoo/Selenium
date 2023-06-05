package sk.peterrendek.advanced.tests;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import sk.peterrendek.advanced.base.TestBase;


import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

public class ExpectedExceptionTest extends TestBase {
    @BeforeEach
    void beforeEach() {
        getDriver().get(BASE_URL+"waitforit.php");
    }

    @Test
    void waitForItTest() {
        getDriver().findElement(By.id("startWaitForText")).click();
        WebElement waitInput = getDriver().findElement(By.id("waitForTextInput"));
        new WebDriverWait(getDriver(), Duration.ofSeconds(10)).until(ExpectedConditions.attributeToBe(waitInput, "value", "dary !!!"));
        Exception e = assertThrows(RuntimeException.class,() -> {
            if(!waitInput.getAttribute("value").contains("!!! dary !!!")){
                throw new RuntimeException("Neobsahuje");
            }
        });
        assertEquals("Neobsahuje",e.getMessage(),"exception didn't occurred");
    }

}
