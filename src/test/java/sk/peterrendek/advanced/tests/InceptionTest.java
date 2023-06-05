package sk.peterrendek.advanced.tests;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import sk.peterrendek.advanced.base.TestBase;

import java.time.Duration;


public class InceptionTest extends TestBase {
    @BeforeEach
    void beforeEach() {
        getDriver().get(BASE_URL + "inception.php");
    }

    @Test
    void scrollTest() throws InterruptedException {
        String parentWindow = getDriver().getWindowHandle();
        for (int i = 1; i <= 10; i++) {
            getDriver().findElement(By.id("letsGoDeeper")).click();
            new WebDriverWait(getDriver(), Duration.ofSeconds(10)).until(ExpectedConditions.numberOfWindowsToBe(2));
            getDriver().getWindowHandles().forEach(s -> getDriver().switchTo().window(s));
            getDriver().findElement(By.xpath("//input[1]")).sendKeys("Sangala je nevinny!");
            Thread.sleep(3_000);
            getDriver().close();
            getDriver().switchTo().window(parentWindow);
        }
    }
}
