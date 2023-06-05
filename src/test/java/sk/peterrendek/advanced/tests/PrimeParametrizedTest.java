package sk.peterrendek.advanced.tests;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import sk.peterrendek.advanced.base.TestBase;
import sk.peterrendek.advanced.helpers.ExcelReader;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;


@Tag("Release")
public class PrimeParametrizedTest extends TestBase {
    private static final String TEST_DATA_PATH = "src/test/resources/data.xlsx";
    private static final String SHEET = "prime";

    @BeforeEach
    void beforeEach() {
        getDriver().get(BASE_URL + "primenumber.php");
    }


    private static Stream<Arguments> getData() throws IOException {
        List<Arguments> arguments = new ArrayList<>();
        ExcelReader primeExcelReader = new ExcelReader(TEST_DATA_PATH);
        Sheet sheet = primeExcelReader.getSheetByName(SHEET);
        for (Row row : sheet) {
            if (row.getRowNum() == 0) {
                continue;
            }
            arguments.add(Arguments.of(
                    String.valueOf((int) row.getCell(0).getNumericCellValue()),
                    row.getCell(1).getBooleanCellValue()));
        }
        return arguments.stream();
    }
    @Tag("Priority")
    @ParameterizedTest
    @MethodSource("getData")
    void excelConfirmationTest(String inputValue, boolean expected) {
        WebElement input = getDriver().findElement(By.xpath("//input[@type='number']"));
        WebElement button = getDriver().findElement(By.className("btn-default"));
        input.clear();
        input.sendKeys(inputValue);
        button.click();
        checkResult(expected);
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
}
