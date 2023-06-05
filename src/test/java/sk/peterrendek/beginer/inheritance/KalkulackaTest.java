package sk.peterrendek.beginer.inheritance;


import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import sk.peterrendek.beginer.inheritance.MainTest;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class KalkulackaTest extends MainTest {
    public static final String HAS_ERROR = "has-error";

    @BeforeEach
    void beforeEach() {
        driver.get(BASE_URL+"kalkulacka.php");
    }

    @Test
    void testSum() {
        Random rnd = new Random();
        long a;
        long b;
        for (int i = 0; i < 11; i++) {
            a = rnd.nextInt();
            b = rnd.nextInt();
            checkSum(a + b, a, b);
        }
    }

    @Test
    void testDeduct() {
        Random rnd = new Random();
        long a;
        long b;
        for (int i = 0; i < 11; i++) {
            a = rnd.nextInt();
            b = rnd.nextInt();
            checkDeduct(a - b, a, b);
        }
    }


    @Test
    void testReset() {
        for (int i = 0; i < 11; i++) {
            enterInput(5,6);
            driver.findElement(By.id("reset")).click();
            assertTrue(driver.findElement(By.id("firstInput")).getAttribute("value").isEmpty());
            assertTrue(driver.findElement(By.id("secondInput")).getAttribute("value").isEmpty());
            assertTrue(driver.findElement(By.id("result")).getText().isEmpty());
        }

    }

    @Test
    void testInvalidInput() {
        driver.findElement(By.id("firstInput")).sendKeys("fsdfsdf");
        driver.findElement(By.id("secondInput")).sendKeys("fsdfsdf");
        driver.findElement(By.id("count")).click();
        assertTrue(driver.findElement(By.xpath("//div[input[@id='firstInput']]"))
                .getAttribute("class").contains(HAS_ERROR));
        assertTrue(driver.findElement(By.xpath("//div[input[@id='secondInput']]"))
                .getAttribute("class").contains(HAS_ERROR));

    }


    private void checkSum(long result, long a, long b) {
        clearInput();
        enterInput(a, b);
        driver.findElement(By.id("count")).click();
        assertEquals(String.valueOf(result), driver.findElement(By.id("result")).getText(), "should be same");
    }

    private void enterInput(long a, long b) {
        driver.findElement(By.id("firstInput")).sendKeys(String.valueOf(a));
        driver.findElement(By.id("secondInput")).sendKeys(String.valueOf(b));
    }

    private void checkDeduct(long result, long a, long b) {
        clearInput();
        enterInput(a, b);
        driver.findElement(By.id("deduct")).click();
        assertEquals(String.valueOf(result), driver.findElement(By.id("result")).getText(), "should be same");
    }

    private void clearInput() {
        driver.findElement(By.id("firstInput")).clear();
        driver.findElement(By.id("secondInput")).clear();
    }

}
