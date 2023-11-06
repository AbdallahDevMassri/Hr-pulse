package com.example.hrpulse.Services.CSV;

import com.example.hrpulse.Services.Database.DatabaseManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UploadCsvFile {
    private TableView<CsvRow> tableView;
    private String filePath;
    private String dbTableName;

    public UploadCsvFile(TableView<CsvRow> tableView, String dbTableName) {
        this.tableView = tableView;
        this.dbTableName = dbTableName;
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
                tableView.setItems(csvRows);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private ObservableList<CsvRow> convertToCsvRows(List<String[]> csvData) {
        ObservableList<CsvRow> csvRows = FXCollections.observableArrayList();
        for (String[] row : csvData) {
            CsvRow csvRow = new CsvRow(row[0], row[1], row[2], row[3], row[4], row[5]);
            csvRows.add(csvRow);
        }
        return csvRows;
    }


    public void saveToCsv() {
        if (filePath != null) {
            ObservableList<CsvRow> csvData = tableView.getItems();
            List<String[]> stringCsvData = convertToStringCsv(csvData);
            CsvService.writeCsv(filePath, stringCsvData);
        }
    }

    private List<String[]> convertToStringCsv(ObservableList<CsvRow> csvRows) {
        List<String[]> stringCsvData = new ArrayList<>();
        for (CsvRow row : csvRows) {
            stringCsvData.add(new String[]{
                    row.getTotalWorkHours(),
                    row.getBreakTime(),
                    row.getExitHour(),
                    row.getEnterHour(),
                    row.getDateTable(),
                    row.getEmployeeId()
            });
        }
        return stringCsvData;
    }

    public void saveToDatabase() {
        ObservableList<CsvRow> csvRows = tableView.getItems();
        List<String[]> stringCsvData = convertToStringCsv(csvRows);
        DatabaseManager.saveToDatabase(dbTableName, stringCsvData);
    }
}
