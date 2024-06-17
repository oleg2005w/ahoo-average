package student.examples;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.Listeners;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class AverageListener implements ITestListener {
    private List<Boolean> results;
    @Override
    public void onStart(ITestContext context) {
        results = new ArrayList<>();
    }


    @Override
    public void onTestSuccess(ITestResult result) {
        results.add(true);
    }

    @Override
    public void onTestFailure(ITestResult result) {
        results.add(false);
    }
    @Override
    public void onFinish(ITestContext context) {

        try {
            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet();
            int rowIndex = 0;
            for (Boolean result : results){
                Row row = sheet.createRow(rowIndex);
                Cell cell = row.createCell(0);
                if (result){
                    cell.setCellValue("PASS");
                }else {
                    cell.setCellValue("FAIL");
                }
                ++rowIndex;
            }
            FileOutputStream fos = new FileOutputStream("C:\\Users\\olegh\\git\\ahoo-avarage\\src\\test\\resources\\average_res.xlsx");
            workbook.write(fos);
            fos.close();
            System.out.println(workbook.getSheet("Sheet1"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
