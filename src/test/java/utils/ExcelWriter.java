package utils;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbookFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

public class ExcelWriter {

    private static ExcelWriter excelWriter = new ExcelWriter();

    private ExcelWriter() {}

    public static ExcelWriter getInstance()
    {
        return excelWriter;
    }

    public void writeToExcel(List<List<String>> databaseData, String fileName, String sheetName) throws IOException, InvalidFormatException {
        //Blank workbook
//        XSSFWorkbook workbook = new XSSFWorkbook();
        String filePath = System.getProperty("user.dir") + "/src/test/resources/data-files/" + fileName + ".xlsx";
        FileInputStream inputStream = new FileInputStream(filePath);
        XSSFWorkbook workbook = XSSFWorkbookFactory.createWorkbook(OPCPackage.open(inputStream));
        //Create a blank sheet
//        XSSFSheet sheet = workbook.createSheet(sheetName);
        XSSFSheet sheet = workbook.getSheet(sheetName);

        for ( int rownum = 0;rownum<databaseData.size();rownum++)
        {
            Row row = sheet.createRow(rownum);
            for (int cellnum = 0;cellnum<databaseData.get(rownum).size();cellnum++)
            {
                Cell cell = row.createCell(cellnum);
                cell.setCellValue(databaseData.get(rownum).get(cellnum));
            }
        }
        try
        {
            //Write the workbook in file system
            FileOutputStream out = new FileOutputStream(filePath);
            workbook.write(out);
            out.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
