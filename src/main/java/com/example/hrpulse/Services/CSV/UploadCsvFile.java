package com.example.hrpulse.Services.CSV;


import com.example.hrpulse.Services.Database.DatabaseManager;
import com.opencsv.CSVWriter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.scene.control.TableView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.example.hrpulse.HR_Pulse.sessionFactory;
import static com.example.hrpulse.Services.Database.DatabaseManager.isDataExists;

public class UploadCsvFile {
    private TableView<CsvRow> tableView;
    private String filePath;
    private String pulseDB;
    private boolean isTableViewLoaded = false;

    private String csvFilePath = "C:\\Users\\User31.8.23\\Desktop\\CSV file for project HR-Pulse\\employees - in-out.csv";


    private ObservableList<CsvRow> additionalRows = FXCollections.observableArrayList();


    private List<CsvRow> externallyAddedRows = new ArrayList<>();

    public String getFilePath() {
        return filePath;
    }

    public UploadCsvFile(TableView<CsvRow> tableView, String pulseDB) {
        this.tableView = tableView; // Initialize the variable through the constructor
        this.pulseDB = pulseDB;

    }


    public void upload(String lastEditorUsername) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV Files", "*.csv"));
        File selectedFile = fileChooser.showOpenDialog(new Stage());

        if (selectedFile != null) {
            // Store the CSV file path
            filePath = selectedFile.getAbsolutePath();

            try {
                List<String[]> csvData = CsvService.readCsv(filePath);
                ObservableList<CsvRow> csvRows = convertToCsvRows(csvData);
                csvRows.forEach(row -> {
                    row.setLastEditor(lastEditorUsername);
                    row.setComments("");
                });

                // Track externally added rows
                for (String[] rowData : csvData) {
                    String employeeId = rowData[5];
                    String date = rowData[4];

                    // Check if the data already exists in the database
                    if (!DatabaseManager.isDataExists(sessionFactory, pulseDB, employeeId, date)) {
                        // Data doesn't exist, track it as externally added
                        CsvRow csvRow = new CsvRow(rowData);
                        csvRow.setLastEditor(lastEditorUsername);
                        externallyAddedRows.add(csvRow);
                    }
                }

                tableView.setItems(csvRows);
                isTableViewLoaded = true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }



    // Update a row in the CSV file
    public void updateRowInCsv(CsvRow updatedRow) throws IOException {
        ObservableList<CsvRow> data = tableView.getItems();
        int index = data.indexOf(updatedRow);

        if (index != -1) {
            data.set(index, updatedRow);
            writeDataToCsv(data);
        } else {
            System.out.println("Row not found in the TableView. Cannot update CSV.");
        }
    }

    private void writeDataToCsv(ObservableList<CsvRow> data) throws IOException {
        try (CSVWriter writer = new CSVWriter(new FileWriter(csvFilePath))) {
            for (CsvRow row : data) {
                writer.writeNext(new String[]{
                        row.getTotalWorkHours(),
                        row.getBreakTime(),
                        row.getExitHour(),
                        row.getStartHour(),
                        row.getDateTable(),
                        row.getEmployeeId(),
                        row.getComments(),
                        row.getLastEditor()
                });
            }
        }
    }

    public void deleteRowFromCsv(CsvRow rowToDelete) throws IOException {
        // Load the existing CSV data
        List<String[]> csvData = CsvService.readCsv(csvFilePath);

        // Create a new list to store the updated CSV data
        List<String[]> updatedCsvData = new ArrayList<>();

        // Iterate through the existing CSV data and exclude the row to delete
        for (String[] row : csvData) {
            CsvRow csvRow = new CsvRow(row[0], row[1], row[2], row[3], row[4], row[5], row[6], row[7]);

            // Check if the current row matches the row to delete
            if (!csvRow.equals(rowToDelete)) {
                updatedCsvData.add(row);
            }
        }

        // Save the updated CSV data
        CsvService.writeCsv(csvFilePath, updatedCsvData);
    }


    // Clear externally added rows after processing
    public void clearExternallyAddedRows() {
        externallyAddedRows.clear();
    }


    public boolean isTableViewLoaded() {
        return isTableViewLoaded;
    }


    public boolean hasExternallyAddedRows() {
        return !externallyAddedRows.isEmpty();
    }

    // Method to get externally added rows
    public List<CsvRow> getExternallyAddedRows() {
        return new ArrayList<>(externallyAddedRows);
    }

    public ObservableList<CsvRow> convertToCsvRows(List<String[]> csvData) {
        ObservableList<CsvRow> csvRows = FXCollections.observableArrayList();
        for (String[] row : csvData) {
            if (row.length >= 6) { // Ensure the array has at least 6 elements
                CsvRow csvRow = new CsvRow(
                        row[0], // total work hours
                        row[1], // break time
                        row[2], // exit hour
                        row[3], // enter hour
                        row[4], // date
                        row[5], // employee ID
                        "",     // initialize comments here
                        ""      // initialize lastEditor here
                );
                csvRows.add(csvRow);
            } else {
                // Handle the case where the array doesn't have enough elements
                System.err.println("Invalid CSV row: " + Arrays.toString(row));
            }
        }
        return csvRows;
    }


    private List<String[]> convertToStringCsv(ObservableList<CsvRow> csvRows) {
        List<String[]> stringCsvData = new ArrayList<>();
        for (CsvRow row : csvRows) {
            stringCsvData.add(new String[]{
                    row.getTotalWorkHours(),
                    row.getBreakTime(),
                    row.getExitHour(),
                    row.getStartHour(),
                    row.getDateTable(),
                    row.getEmployeeId()
            });
        }
        return stringCsvData;
    }

    public boolean isExternallyAddedRow(CsvRow row) {
        return externallyAddedRows.contains(row);
    }


}