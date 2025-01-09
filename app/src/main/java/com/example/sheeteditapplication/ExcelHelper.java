package com.example.sheeteditapplication;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.*;

public class ExcelHelper {

    public void readWriteExcelFile(String filePath) {
        try {
            // Open the Excel file
            FileInputStream fis = new FileInputStream(new File(filePath));
            Workbook workbook = new XSSFWorkbook(fis);
            Sheet sheet = workbook.getSheetAt(0); // Access first sheet

            // Read an existing cell value
            Row row = sheet.getRow(0); // Access first row
            Cell cell = row.getCell(0); // Access first cell
            String cellValue = cell.getStringCellValue();
            System.out.println("Original Value: " + cellValue);

            // Modify the cell value
            cell.setCellValue("Modified Value");

            // Save the changes to the file
            fis.close();
            FileOutputStream fos = new FileOutputStream(new File(filePath));
            workbook.write(fos);
            fos.close();

            System.out.println("File saved successfully!");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
