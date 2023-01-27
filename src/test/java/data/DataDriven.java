package data;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class DataDriven {

    private static Iterator<Row> rowIterator;
    private static Iterator<Cell> cellIterator;
    private static Row firstRow;
    private static ArrayList<String> testData = new ArrayList<>();

    public ArrayList<String> exportData() throws IOException {
        FileInputStream inputStream = new FileInputStream("src/resources/test_data.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
        XSSFSheet sheet = workbook.getSheetAt(0);

        rowIterator = sheet.iterator();
        firstRow =  rowIterator.next();
        cellIterator = firstRow.cellIterator();

        fillTestDataArray();
        return testData;

    }

    public void fillTestDataArray(){
        while(cellIterator.hasNext()){
            testData.add(cellIterator.next().getStringCellValue());
        }
    }




}
