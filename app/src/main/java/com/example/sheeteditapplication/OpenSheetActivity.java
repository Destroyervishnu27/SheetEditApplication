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

    private TableLayout tableLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_open_sheet);


        tableLayout = findViewById(R.id.tableLayout);

        // Load Excel data
        int rawFileId = R.raw.one; // Replace with your actual file
        List<List<String>> excelData = ExcelReader.readExcelFile(this, rawFileId);

        if (excelData.isEmpty()) {
            return;
        }

        createTable(excelData);
    }

    private void createTable(List<List<String>> data) {
        for (int i = 0; i < data.size(); i++) {
            TableRow tableRow = new TableRow(this);
            tableRow.setLayoutParams(new TableRow.LayoutParams(
                    TableRow.LayoutParams.MATCH_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT));
            tableRow.setPadding(4, 4, 4, 4);

            List<String> rowData = data.get(i);
            for (int j = 0; j < rowData.size(); j++) {
                EditText cell = createCell(rowData.get(j), i == 0); // Header or regular cell
                tableRow.addView(cell);
            }
            tableLayout.addView(tableRow);
        }
    }

    private EditText createCell(String value, boolean isHeader) {
        EditText editText = new EditText(this);
        editText.setText(value);
        editText.setPadding(8, 8, 8, 8);
        editText.setGravity(Gravity.CENTER);

        if (isHeader) {
            editText.setBackgroundColor(Color.LTGRAY);
            editText.setTextColor(Color.BLACK);
            editText.setEnabled(false); // Headers are not editable
        } else {
            editText.setBackgroundResource(R.drawable.cell_background);
        }

        editText.setLayoutParams(new TableRow.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        return editText;
    }
}