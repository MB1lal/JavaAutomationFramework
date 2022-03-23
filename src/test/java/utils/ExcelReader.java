package utils;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class ExcelReader
{
    private static ExcelReader excelReader = new ExcelReader();

    private ExcelReader() {}

    public static ExcelReader getInstance()
    {
        return excelReader;
    }

    /**
     * This class deals with reading the excel file of product upload list and store into a two dimensional array list.
     */
    public  List<List<String>> readExcel(String sheetName) throws IOException
    {
        List<List<String>> excelData = new ArrayList<>();

        String filePath = System.getProperty("user.dir") + "/src/test/resources/data-files/testData.xlsx";

        int r = 0;
        int c = 0;
        int maxCells;
        //obtaining input bytes from a file
        FileInputStream fis = new FileInputStream(new File(filePath));
        //creating workbook instance that refers to .xls file
        XSSFWorkbook wb = new XSSFWorkbook(fis);
        //creating a Sheet object to retrieve the object
        XSSFSheet xssfSheet = wb.getSheet(sheetName);
        //XSSFSheet itemSheet = wb.getSheet("Item");
        maxCells = xssfSheet.getRow(0).getLastCellNum();
        //evaluating cell type
        FormulaEvaluator formulaEvaluator = wb.getCreationHelper().createFormulaEvaluator();
        for (Row row : xssfSheet)     //iteration over row using for each loop
        {
            excelData.add(new ArrayList<>());

            for(int cell = 0; cell<maxCells;cell++)
            {
                try
                {

                    switch (formulaEvaluator.evaluateInCell(row.getCell(cell)).getCellType()) {
                        case NUMERIC:   //field that represents numeric cell type
                            //getting the value of the cell as a number

                            row.getCell(cell).setCellType(CellType.STRING);
                            String s = "" + row.getCell(cell).getStringCellValue();
                            //s = s.contains(".") ? s.replaceAll("0*$","").replaceAll("\\.$","") : s;
                            excelData.get(r).add(c, s);
                            c++;
                            break;

                        case STRING:    //field that represents string cell type
                            //getting the value of the cell as a string
                            excelData.get(r).add(c,  row.getCell(cell).getStringCellValue());
                            c++;
                            //System.out.print(cell.getStringCellValue() + "\t\t");
                            break;
                        default:
                            excelData.get(r).add(c,"");
                            c++;
                            break;
                    }
                }
                catch (NullPointerException isNUll)
                {
                    excelData.get(r).add(c,"");
                    c++;
                }
            }
            r++;
            c=0;
            //System.out.println();
        }

        return excelData;
    }

    public int getColumnIndex(String sheetIndex) {
        int columnIndex;
        switch (sheetIndex.toUpperCase().charAt(0)) {
            case 'A':
                columnIndex = 0;
                break;

            case 'B':
                columnIndex = 1;
                break;

            default:
                throw new IllegalArgumentException("Incorrect specified index");
        }
        return columnIndex;
    }
}
