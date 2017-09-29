package objectRepository;

import enums.commonEnum;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;

public class TestDataProviders {

    public TestDataProviders(){

    }

    public Object[][] getDataUserManagement(String ParameterFile) throws IOException
    {
        Object[][] tempData = null;

        tempData = readExcelSheet(ParameterFile, commonEnum.xFormDataProviders.XFormDPUserManagement.toString(),"1");

        return tempData;
    }
    // read an excel file looking for all rows with a number defined by idRowGet in
    // the first cell
    // return an array
    public Object[][] readExcelSheet(String fileName, String sheetName, String idRowGetStatus) throws IOException {
        FileInputStream inputStream = new FileInputStream(fileName);

        Workbook myWorkbook = null;

        myWorkbook = new XSSFWorkbook(inputStream);

        // Read sheet inside the workbook by its name
        org.apache.poi.ss.usermodel.Sheet mySheet = myWorkbook.getSheet(sheetName);

        // Find number of rows in excel file
        int rowCount = mySheet.getLastRowNum() - mySheet.getFirstRowNum();
        // Find number of columns in excel file
        Row tempRow = mySheet.getRow(0);
        int colCount = tempRow.getLastCellNum();

        Object[][] tempHeader = new Object[rowCount][colCount];

        // Create a loop over all the rows of excel file to read it
        for (int i = 1; i < rowCount + 1; i++) {

            Row row = mySheet.getRow(i);

            // Create a loop to print cell values in a row
            for (int j = 0; j < row.getLastCellNum(); j++) {

                // Print Excel data in console
                System.out.print(row.getCell(j).getStringCellValue());

                //start reading at second line avoiding headers
                if (row.getCell(0).getStringCellValue().compareTo(idRowGetStatus) == 0 && j > 0) {
                    tempHeader[i-1][j - 1] = row.getCell(j).getStringCellValue();
                }
            }
            System.out.println();
        }
        return tempHeader;
    }
}
