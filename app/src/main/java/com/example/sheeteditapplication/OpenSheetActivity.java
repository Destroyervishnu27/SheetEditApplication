package com.example.sheeteditapplication;

import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


public class OpenSheetActivity extends AppCompatActivity {

    private TableLayout tableLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_open_sheet);


        tableLayout = findViewById(R.id.tableLayout);

        // Access the Excel file (update with your raw file ID)
        int rawFileId = R.raw.one; // Replace 'sample' with your file name
        List<List<String>> excelData = ExcelReader.readExcelFile(this, rawFileId);

        if (excelData.isEmpty()) {
            return; // Handle empty data case
        }

        createTable(excelData);
    }

    private void createTable(List<List<String>> data) {
        for (List<String> rowData : data) {
            TableRow tableRow = new TableRow(this);
            tableRow.setLayoutParams(new TableRow.LayoutParams(
                    TableRow.LayoutParams.MATCH_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT));

            for (String cellValue : rowData) {
                EditText cell = new EditText(this);
                cell.setText(cellValue);
                cell.setGravity(Gravity.CENTER);
                cell.setPadding(8, 8, 8, 8);
                cell.setBackgroundResource(R.drawable.cell_background);
                cell.setLayoutParams(new TableRow.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT));
                tableRow.addView(cell);
            }

            tableLayout.addView(tableRow);
        }
    }
}