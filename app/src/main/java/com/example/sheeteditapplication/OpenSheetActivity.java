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

    private RecyclerView verticalRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_open_sheet);


        verticalRecyclerView = findViewById(R.id.verticalRecyclerView);

        // Load Excel data
        int rawFileId = R.raw.one; // Replace with your raw file ID
        List<List<String>> excelData = ExcelReader.readExcelFile(this, rawFileId);

        if (excelData.isEmpty()) {
            return;
        }

        setupTable(excelData);
    }

    private void setupTable(List<List<String>> data) {
        TableAdapter tableAdapter = new TableAdapter(this, data);
        verticalRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        verticalRecyclerView.setAdapter(tableAdapter);
    }
}