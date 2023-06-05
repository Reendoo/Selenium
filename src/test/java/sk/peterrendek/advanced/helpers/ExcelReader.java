package sk.peterrendek.advanced.helpers;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;


public class ExcelReader {

    private final Workbook excelData;

    public ExcelReader(String excelPath) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(excelPath);
        this.excelData = new XSSFWorkbook(fileInputStream);
    }

    public Sheet getSheetByName(String name) {
        return excelData.getSheet(name);
    }

}
