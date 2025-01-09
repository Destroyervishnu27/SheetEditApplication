package com.example.sheeteditapplication;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.time.temporal.ValueRange;
import java.util.*;

import com.google.android.gms.auth.api.signin.GoogleSignInClient;

public class OpenSheetActivity extends AppCompatActivity {

    private static final int RC_SIGN_IN = 1001;
    private GoogleSignInClient googleSignInClient;
    private Sheets sheetsService;
    private String SPREADSHEET_ID = "your_spreadsheet_id"; // Replace with your Google Sheets ID
    private Button signInButton, readExcelButton, updateSheetButton;
    private EditText filePathEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_open_sheet);

        signInButton = findViewById(R.id.sign_in_button);
        readExcelButton = findViewById(R.id.read_excel_button);
        updateSheetButton = findViewById(R.id.update_sheet_button);
        filePathEditText = findViewById(R.id.file_path_edit_text);

        // Configure Google Sign-In
        googleSignInClient = GoogleSignIn.getClient(this, GoogleSignIn.getSignInOptions());

        // Sign-In button
        signInButton.setOnClickListener(view -> signIn());

        // Read Excel file button
        readExcelButton.setOnClickListener(view -> {
            String filePath = filePathEditText.getText().toString();
            if (!filePath.isEmpty()) {
                readAndUpdateExcel(filePath);
            } else {
                Toast.makeText(OpenSheetActivity.this, "Please enter a file path", Toast.LENGTH_SHORT).show();
            }
        });

        // Update Google Sheet button
        updateSheetButton.setOnClickListener(view -> updateGoogleSheet());
    }

    private void signIn() {
        Intent signInIntent = googleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                authenticateGoogleSheets(account);
            } catch (ApiException e) {
                Log.w("Google Sign-In", "signInResult:failed code=" + e.getStatusCode());
            }
        }
    }

    private void authenticateGoogleSheets(GoogleSignInAccount account) {
        try {
            GoogleAccountCredential credential = GoogleAccountCredential.usingOAuth2(
                    this, Collections.singleton(SheetsScopes.SPREADSHEETS)
            );
            credential.setSelectedAccount(account.getAccount());
            sheetsService = new Sheets.Builder(
                    AndroidHttp.newCompatibleTransport(), new JacksonFactory(), credential
            ).setApplicationName("ExcelGoogleSheetApp").build();
            Toast.makeText(OpenSheetActivity.this, "Signed in to Google Sheets", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(OpenSheetActivity.this, "Authentication failed", Toast.LENGTH_SHORT).show();
        }
    }

    private void readAndUpdateExcel(String filePath) {
        try {
            FileInputStream fis = new FileInputStream(new File(filePath));
            Workbook workbook = new XSSFWorkbook(fis);
            Sheet sheet = workbook.getSheetAt(0);

            // Read an existing cell value
            Row row = sheet.getRow(0); // Access first row
            Cell cell = row.getCell(0); // Access first cell
            String cellValue = cell.getStringCellValue();
            Toast.makeText(this, "Original Value: " + cellValue, Toast.LENGTH_SHORT).show();

            // Modify the cell value
            cell.setCellValue("Updated Value");

            // Save the changes to the file
            FileOutputStream fos = new FileOutputStream(new File(filePath));
            workbook.write(fos);
            fos.close();
            Toast.makeText(this, "Excel file updated!", Toast.LENGTH_SHORT).show();

        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Error reading Excel file", Toast.LENGTH_SHORT).show();
        }
    }

    private void updateGoogleSheet() {
        if (sheetsService != null) {
            String range = "Sheet1!A1";  // Update the range as needed
            List<List<Object>> newValues = new ArrayList<>();
            newValues.add(Collections.singletonList("Updated Value"));

            ValueRange body = new ValueRange().setValues(newValues);
            try {
                sheetsService.spreadsheets().values()
                        .update(SPREADSHEET_ID, range, body)
                        .setValueInputOption("RAW")
                        .execute();
                Toast.makeText(OpenSheetActivity.this, "Google Sheet updated!", Toast.LENGTH_SHORT).show();
            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(OpenSheetActivity.this, "Error updating Google Sheet", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(OpenSheetActivity.this, "Please sign in first!", Toast.LENGTH_SHORT).show();
        }
    }
}