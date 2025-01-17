package com.example.sheeteditapplication;

import android.content.Context;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ExcelReader {

    public static List<List<String>> readExcelFile(Context context, int rawResourceId) {
        List<List<String>> data = new ArrayList<>();
        try {
            // Access the file from the raw folder
            InputStream inputStream = context.getResources().openRawResource(rawResourceId);
            Workbook workbook = new XSSFWorkbook(inputStream);
            Sheet sheet = workbook.getSheetAt(0); // Read the first sheet

            for (Row row : sheet) {
                List<String> rowData = new ArrayList<>();
                row.forEach(cell -> rowData.add(cell.toString())); // Read each cell
                data.add(rowData);
            }
            workbook.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }
}
