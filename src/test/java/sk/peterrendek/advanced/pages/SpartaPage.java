package sk.peterrendek.advanced.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import sk.peterrendek.advanced.model.Sin;

import java.time.Duration;
import java.util.List;

import static sk.peterrendek.advanced.base.TestBase.BASE_URL;


public class SpartaPage {

    public static final String PAGE_URL = "sparta.php";
    private final WebDriver driver;

    @FindBy(className = "sin")
    private List<WebElement> articels;

    public SpartaPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void openPage() {
        driver.get(BASE_URL + PAGE_URL);
    }

    public boolean checkArticle(WebElement element, Sin sin) {
        return (element.findElement(By.xpath("./p")).getText().contains(sin.getMessage())
                && element.findElement(By.xpath("./header/h4")).getText().contains(sin.getTitle())
                && element.findElement(By.xpath("./footer/h5")).getText().equals(sin.getAuthor()));
    }

    public WebElement findArticle(Sin s) {
        return articels.stream()
                .filter(webElement -> checkArticle(webElement,s))
                .findFirst().orElseThrow(() -> new RuntimeException("Article doesn't exists"));
    }

    public void forgiveSin(WebElement article){
        article.findElement(By.xpath("./footer/button")).click();
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .withMessage("Button didn't appeared")
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("confirm")));

        driver.findElement(By.id("confirm")).click();
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .withMessage("Button still show")
                .until(ExpectedConditions.invisibilityOfElementLocated(By.id("confirm")));
    }
}
