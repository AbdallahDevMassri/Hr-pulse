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
import static com.example.hrpulse.Services.CSV.CsvService.readCsv;
import static com.example.hrpulse.Services.CSV.CsvService.writeCsv;

/**
 * Utility class for uploading CSV files and managing data in a TableView.
 */
public class UploadCsvFile {

    // Fields for managing TableView, file paths, and additional rows
    private TableView<CsvRow> tableView;
    private String filePath;
    private String pulseDB;
    private boolean isTableViewLoaded = false;

    // Default CSV file path
    private String csvFilePath = "C:\\CSV file for project HR-Pulse\\employees - in-out.csv";

    private ObservableList<CsvRow> additionalRows = FXCollections.observableArrayList();
    private List<CsvRow> externallyAddedRows = new ArrayList<>();

    // Constructor
    public UploadCsvFile(TableView<CsvRow> tableView, String pulseDB) {
        this.tableView = tableView;
        this.pulseDB = pulseDB;
    }

    // Getter for file path
    public String getFilePath() {
        return filePath;
    }

    /**
     * Uploads data from a CSV file to the TableView.
     */
    public void upload() {
        // FileChooser for selecting CSV file
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV Files", "*.csv"));
        File selectedFile = fileChooser.showOpenDialog(new Stage());

        // If a file is selected
        if (selectedFile != null) {
            filePath = selectedFile.getAbsolutePath();
            csvFilePath = filePath; // Set csvFilePath to the selected file path

            try {
                // Read CSV data and convert to CsvRows
                List<String[]> csvData = readCsv(filePath);
                ObservableList<CsvRow> csvRows = convertToCsvRows(csvData);

                // Identify externally added rows
                for (String[] rowData : csvData) {
                    String employeeId = rowData[5];
                    String date = rowData[4];

                    if (!DatabaseManager.isDataExists(sessionFactory, pulseDB, employeeId, date)) {
                        CsvRow csvRow = new CsvRow(rowData);
                        externallyAddedRows.add(csvRow);
                    }
                }

                // Set TableView items and mark as loaded
                tableView.setItems(csvRows);
                isTableViewLoaded = true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // Update a row in the CSV file and the TableView
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

    // Write data to the CSV file
    public void writeDataToCsv(ObservableList<CsvRow> data) throws IOException {
        try (CSVWriter writer = new CSVWriter(new FileWriter(csvFilePath))) {
            for (CsvRow row : data) {
                writer.writeNext(new String[]{
                        row.getTotalWorkHours(),
                        row.getBreakTime(),
                        row.getExitHour(),
                        row.getStartHour(),
                        row.getDate(),
                        row.getEmployeeId(),
                });
            }
        }
    }

    // Check if TableView is loaded
    public boolean isTableViewLoaded() {
        return isTableViewLoaded;
    }

    /**
     * Removes a row from the CSV file and the database.
     */
    public void removeRowFromCsvAndDatabase(CsvRow row) throws IOException {
        List<String[]> csvData = readCsv(csvFilePath);
        csvData.removeIf(csvRow -> Arrays.equals(csvRow, row.toArray()));
        writeCsv(csvFilePath, csvData);
        DatabaseManager.deleteRowFromDatabase("employeeshiftdata", row.getEmployeeId(), row.getDate());
    }

    // Convert a list of String arrays to CsvRows
    public ObservableList<CsvRow> convertToCsvRows(List<String[]> csvData) {
        ObservableList<CsvRow> csvRows = FXCollections.observableArrayList();
        for (String[] row : csvData) {
            if (row.length >= 5) {
                CsvRow csvRow = new CsvRow(
                        row[0], row[1], row[2], row[3], row[4], row[5]);
                csvRows.add(csvRow);
            } else {
                System.err.println("Invalid CSV row: " + Arrays.toString(row));
            }
        }
        return csvRows;
    }

    // Mark a row as externally added
    public void markExternallyAddedRow(CsvRow row) {
        externallyAddedRows.add(row);
    }

    // Retrieve externally added rows
    public List<CsvRow> getExternallyAddedRows() {
        return new ArrayList<>(externallyAddedRows);
    }

    // Check if externally added rows are empty
    public boolean isExternallyAddedRowsEmpty() {
        return externallyAddedRows.isEmpty();
    }

    // Clear externally added rows
    public void clearExternallyAddedRows() {
        externallyAddedRows.clear();
    }
}
