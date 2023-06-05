package sk.peterrendek.advanced.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import sk.peterrendek.advanced.enums.SinType;
import sk.peterrendek.advanced.model.Sin;

import java.time.Duration;
import java.util.List;


import static sk.peterrendek.advanced.base.TestBase.BASE_URL;


public class SinCityPage {

    public static final String PAGE_URL = "sincity.php";
    public static final String FORGIVEN = "forgiven";
    private final WebDriver driver;
    @FindBy(name = "title")
    private WebElement titleInput;
    @FindBy(name = "author")
    private WebElement authorInput;
    @FindBy(name = "message")
    private WebElement messageInput;
    @FindBy(xpath = "//button[@type='submit']")
    private WebElement confessButton;
    @FindBy(css = "div.sinsListArea")
    private WebElement sinListSection;

    @FindBy(xpath = "//h3[@class='sin-header']/span")
    private WebElement sinCount;

    private WebElement detailOfSin;

    public SinCityPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void openPage() {
        driver.get(BASE_URL + PAGE_URL);
    }

    public void filSinInformation(Sin sin) {
        titleInput.clear();
        titleInput.sendKeys(sin.getTitle());
        authorInput.clear();
        authorInput.sendKeys(sin.getAuthor());
        messageInput.clear();
        messageInput.sendKeys(sin.getMessage());
        markTag(sin.getTags());
    }

    private void markTag(List<SinType> tags) {
        tags.forEach(s -> {
            if (s != SinType.NO_TAGS) {
                driver.findElement(By.xpath("//input[@value='" + s.getValue() + "']")).click();
            }
        });
    }

    public void confessSin() {
        confessButton.click();
    }

    public void eraseSins() {

        String s = sinCount.getText();
        int count = Integer.parseInt(s.substring(1, s.length() - 1));
        if (count == 0) {
            return;
        }
        driver.findElement(By.xpath("//button[@data-toggle='modal']")).click();
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .withMessage("Button didn't appeared")
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("confirm")));
        driver.findElement(By.id("confirm")).click();
    }

    public void openSinDetail(Sin sin) {
        WebElement listOfSins = sinListSection.findElement(By.cssSelector("ul.list-of-sins"));
        listOfSins.findElement(By.xpath("./li[contains(text(),'" + sin.getTitle() + "')]")).click();
    }


    public boolean isForgiven(Sin sin) {
        WebElement listOfSins = sinListSection.findElement(By.cssSelector("ul.list-of-sins"));
        String text = listOfSins.findElement(By.xpath("./li[contains(text(),'" + sin.getTitle() + "')]"))
                .findElement(By.xpath("//div[@class='description']/p")).getText();
        return text.trim().equals(FORGIVEN);
    }

    public Sin getRequiredSinFromPage(Sin sin) {
        openSinDetail(sin);
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .withMessage("Detail Of Sin didn't loaded")
                .until(ExpectedConditions.textToBePresentInElement(driver.findElement(By.xpath("//article/p")), sin.getMessage()));
        detailOfSin = driver.findElement(By.className("detail"));
        return getSinFromPage();
    }

    private Sin getSinFromPage() {
        String s = detailOfSin.findElement(By.xpath("./article/h4")).getText();
        String author = s.substring(0, s.indexOf(" : "));
        String tittle = s.substring(s.indexOf(" : ") + 3);
        String message = detailOfSin.findElement(By.xpath("./article/p")).getText();
        List<SinType> tags = detailOfSin.findElements(By.xpath("//article/div[@class='tags']/ul/li"))
                .stream()
                .map(webElement -> SinType.getValue(webElement.getText())
                        .orElseThrow(() -> new RuntimeException("Invalid Sin.type")))
                .toList();
        return new Sin(tittle, author, message, tags);
    }
}
