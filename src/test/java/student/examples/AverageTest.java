package student.examples;

import com.github.pjfanning.xlsx.StreamingReader;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Listeners(AverageListener.class)

public class AverageTest {
    private Average average = new Average();
    @BeforeTest
    public void setup (){
        Average average;
    }
    @DataProvider(name = "calcAv")
    public Iterator<List<Integer>> calcAv() throws IOException {
           List<List<Integer>> values1 = new ArrayList<>();
        try (
                InputStream is = new FileInputStream("C:\\Users\\olegh\\git\\ahoo-avarage\\src\\test\\resources\\average1.xlsx");
                Workbook workbook = StreamingReader.builder()
                        .rowCacheSize(100)
                        .bufferSize(4096)
                        .open(is)
        ){
            for (Sheet sheet : workbook){
                System.out.println(sheet.getSheetName());
                for (Row r : sheet) {
//                    int rowRes = 0;
                    List<Integer> valuesRow = new ArrayList<>();
                    for (Cell c  : r) {
//                        System.out.println("inex_column: " + c.getColumnIndex());

                            valuesRow.add(Integer.valueOf(c.getStringCellValue()));



//                        System.out.println(c.getStringCellValue());
                    }
//                    System.out.println("values row REs --->" + valuesRow);
                    values1.add(valuesRow);
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return values1.iterator();
    }

    @Test(dataProvider = "calcAv")
    public void averageTest (List<Integer> values){

        int res = values.get(0);
        System.out.println("Values======> " + values);
        System.out.println("0 result "+ res);
        values.remove(0);
        System.out.println("Average function---->" + average.calcAverage(values));
        Assert.assertEquals(average.calcAverage(values), res);

    }
    @AfterClass
    public void resExcel (){

    }
}
