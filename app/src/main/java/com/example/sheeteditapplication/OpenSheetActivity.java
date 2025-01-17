package com.example.sheeteditapplication;

import android.graphics.Color;
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

    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_open_sheet);


        recyclerView = findViewById(R.id.recyclerView);

        // Load Excel data
        int rawFileId = R.raw.one; // Replace with your actual file ID
        List<List<String>> excelData = ExcelReader.readExcelFile(this, rawFileId);

        if (excelData.isEmpty()) {
            return; // Handle empty data case
        }

        setupRecyclerView(excelData);
    }

    private void setupRecyclerView(List<List<String>> data) {
        // Flatten 2D list into a 1D list
        List<String> tableData = new ArrayList<>();
        int columnCount = 0;

        for (List<String> row : data) {
            columnCount = Math.max(columnCount, row.size());
            tableData.addAll(row);
        }

        // Setup RecyclerView with GridLayoutManager
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, columnCount);
        recyclerView.setLayoutManager(gridLayoutManager);

        TableAdapter adapter = new TableAdapter(this, tableData, columnCount);
        recyclerView.setAdapter(adapter);
    }
}