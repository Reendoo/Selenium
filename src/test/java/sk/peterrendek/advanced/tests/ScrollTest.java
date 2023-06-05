package sk.peterrendek.advanced.tests;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import sk.peterrendek.advanced.base.TestBase;


public class ScrollTest extends TestBase {
    private JavascriptExecutor js;

    @BeforeAll
    static void beforeAll() {

    }

    @BeforeEach
    void beforeEach() {
        getDriver().get(BASE_URL+"tabulka.php");
        js = ((JavascriptExecutor) getDriver());
    }

    @Tag("SmokeTest")
    @Test
    void scrollTest() {/*last()*/
        WebElement element = getDriver().findElement(By.xpath("//table/tbody/tr[5]"));
        js.executeScript("arguments[0].scrollIntoView(true)", element);
    }

    @Test
    void scrollByPixelsTest() {
        int move = 100;
        for (int i = 0; i <= 10; i++) {
            js.executeScript("window.scrollBy(0," + move + ")");
        }
    }

    @Test
    void scrollToEnd() {
        System.out.println("Page hight: " + js.executeScript("return document.body.scrollHeight"));
        for (int i = 0; i <= 10; i++) {
            js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
            js.executeScript("window.scrollBy(0,-1*(document.body.scrollHeight))");
        }
    }
}
