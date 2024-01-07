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

    public UploadCsvFile(TableView<CsvRow> tableView, String pulseDB) {
        this.tableView = tableView;
        this.pulseDB = pulseDB;
    }

    public String getFilePath() {
        return filePath;
    }

    public void upload() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV Files", "*.csv"));
        File selectedFile = fileChooser.showOpenDialog(new Stage());

        if (selectedFile != null) {
            filePath = selectedFile.getAbsolutePath();

            try {
                List<String[]> csvData = CsvService.readCsv(filePath);
                ObservableList<CsvRow> csvRows = convertToCsvRows(csvData);

                for (String[] rowData : csvData) {
                    String employeeId = rowData[5];
                    String date = rowData[4];

                    if (!DatabaseManager.isDataExists(sessionFactory, pulseDB, employeeId, date)) {
                        CsvRow csvRow = new CsvRow(rowData);
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
                        row.getComments()
                });
            }
        }
    }

    public void deleteRowFromCsv(CsvRow rowToDelete) throws IOException {
        List<String[]> csvData = CsvService.readCsv(csvFilePath);
        List<String[]> updatedCsvData = new ArrayList<>();

        for (String[] row : csvData) {
            CsvRow csvRow = new CsvRow(row);

            if (!csvRow.equals(rowToDelete)) {
                updatedCsvData.add(row);
            }
        }

        CsvService.writeCsv(csvFilePath, updatedCsvData);
    }


    public boolean isTableViewLoaded() {
        return isTableViewLoaded;
    }

    public boolean hasExternallyAddedRows() {
        return !externallyAddedRows.isEmpty();
    }

    public List<CsvRow> getExternallyAddedRows() {
        return new ArrayList<>(externallyAddedRows);
    }

    public ObservableList<CsvRow> convertToCsvRows(List<String[]> csvData) {
        ObservableList<CsvRow> csvRows = FXCollections.observableArrayList();
        for (String[] row : csvData) {
            if (row.length >= 6) {
                CsvRow csvRow = new CsvRow(
                        row[0], row[1], row[2], row[3], row[4], row[5], row[6]);
                csvRows.add(csvRow);
            } else {
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

    public void markExternallyAddedRow(CsvRow row) {
        externallyAddedRows.add(row);
    }

    public boolean isExternallyAddedRow(CsvRow row) {
        return externallyAddedRows.contains(row);
    }

    public void clearExternallyAddedRows() {
        externallyAddedRows.clear();
    }
}
