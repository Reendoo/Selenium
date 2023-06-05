package sk.peterrendek.beginer;


import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class MenuTest {
    private WebDriver driver;
    private static final String BASE_URL = "http://localhost:8888/index.php";

    @BeforeEach
    public void setUp() {
        driver = new FirefoxDriver();
        driver.get(BASE_URL);
    }

    @Test
    public void test() {
        List<String> hrefs = driver.findElements(By.xpath("//nav/div/ul/li/a"))
                .stream()
                .map(webElement -> {
                    String s = webElement.getAttribute("href");
                    return s.substring(s.lastIndexOf("/") + 1);
                }).toList();
        String url = BASE_URL.substring(0, BASE_URL.lastIndexOf("/") + 1);
        for (var v : hrefs) {
            driver.get(url + v);
            assertEquals("active", driver.findElement(By.xpath("//li[a/@href='" + v + "']")).getAttribute("class"));
        }
    }

    @AfterEach
    public void tearDown() {
//        driver.close();
        driver.quit();
    }

}
