package com.example.sheeteditapplication;

import android.content.Context;

import org.apache.poi.ss.usermodel.Cell;
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
        List<List<String>> tableData = new ArrayList<>();
        try {
            InputStream inputStream = context.getResources().openRawResource(rawResourceId);
            Workbook workbook = new XSSFWorkbook(inputStream);
            Sheet sheet = workbook.getSheetAt(0); // Read the first sheet

            for (Row row : sheet) {
                List<String> rowData = new ArrayList<>();
                for (Cell cell : row) {
                    // Add cell value as a string
                    rowData.add(cell.toString());
                }
                tableData.add(rowData);
            }
            workbook.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tableData;
    }
}
