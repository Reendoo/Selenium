package sk.peterrendek.advanced.tests;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import sk.peterrendek.advanced.base.TestBase;
import sk.peterrendek.advanced.helpers.ExcelReader;

import java.io.IOException;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.fail;


public class PrimeTest extends TestBase {
    private static final String TEST_DATA_PATH = "src/test/resources/data.xlsx";
    private static final String SHEET = "prime";

    @BeforeEach
    void beforeEach() {
        getDriver().get(BASE_URL+"primenumber.php");
    }

    @Tag("Priority")
    @Test
    void excelConfirmationTest() throws IOException {
        WebElement input = getDriver().findElement(By.xpath("//input[@type='number']"));
        WebElement button = getDriver().findElement(By.className("btn-default"));

        ExcelReader primeExcelReader = new ExcelReader(TEST_DATA_PATH);
        Sheet sheet = primeExcelReader.getSheetByName(SHEET);

        for (Row row : sheet) {
            if (row.getRowNum() == 0) {
                continue;
            }
            input.clear();
            input.sendKeys(String.valueOf((int) row.getCell(0).getNumericCellValue()));
            button.click();
            checkResult(row.getCell(1).getBooleanCellValue());
        }
    }

    private void checkResult(boolean booleanCellValue) {
        if (booleanCellValue) {
            new WebDriverWait(getDriver(), Duration.ofSeconds(10))
                    .withMessage("Should be approved")
                    .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='Optimus approves']")));
        } else {
            new WebDriverWait(getDriver(), Duration.ofSeconds(10))
                    .withMessage("Should be rejected")
                    .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='Optimus is sad']")));
        }
    }

    @Test
    @Disabled
    void quarantineTest() {
        fail();
    }
}
