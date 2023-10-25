package com.example.hrpulse.Controllers.EmployeeController;

import com.example.hrpulse.Services.CSV.CSVData;
import com.example.hrpulse.Services.CSV.CsvService;
import com.example.hrpulse.Services.Database.ShiftDataService;
import com.example.hrpulse.Services.Interfaces.EmployeeNavigators;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EditEmployeeShiftController implements EmployeeNavigators {

    @FXML
    private ChoiceBox<?> cb_monthChoice;

    @FXML
    private ChoiceBox<?> cd_employeeChoice;

    @FXML
    private TableColumn<?, ?> tc_breakTime;

    @FXML
    private TableColumn<?, ?> tc_commit;

    @FXML
    private TableColumn<?, ?> tc_dayTable;

    @FXML
    private TableColumn<?, ?> tc_enterHour;

    @FXML
    private TableColumn<?, ?> tc_exitHour;

    @FXML
    private Label tf_countDays;

    @FXML
    private Label tf_countHours;

    @FXML
    private TableView<CSVData> tableView; // Added TableView with appropriate generic type

    @FXML
    private TextField tf_enterHour; // Added TextField for "Enter Hour"

    @FXML
    private TextField tf_exitHour; // Added TextField for "Exit Hour"

    @FXML
    private TextField tf_breakTime; // Added TextField for "Break Time"

    private List<String[]> csvData; // Define csvData as an instance variable

    private File lastUploadedCsvFile;

    private List<String> uploadedCSVFiles = new ArrayList<>();

    private ObservableList<CSVData> csvDataList = FXCollections.observableArrayList();


    private ShiftDataService shiftDataService; // Initialize this with your Hibernate ShiftDataService

    @FXML
    void saveButtonClicked(ActionEvent event) {
        if (lastUploadedCsvFile != null) {
            List<String[]> newData = convertDataToCsvFormat(csvDataList);
            CsvService.writeCsv(lastUploadedCsvFile.getAbsolutePath(), newData);
            showInformationDialog("CSV Saved", "CSV data saved successfully.");
        } else {
            showErrorDialog("No File Selected", "Please upload a CSV file first.");
        }
    }


    private List<String[]> convertDataToCsvFormat(ObservableList<CSVData> data) {
        List<String[]> csvData = new ArrayList<>();
        for (CSVData csvDataItem : data) {
            csvData.add(new String[] {
                    csvDataItem.getComments(),
                    csvDataItem.getBreakTime(),
                    csvDataItem.getExitHour(),
                    csvDataItem.getEnterHour(),
                    csvDataItem.getDay()
            });
        }
        return csvData;
    }



    private void showInformationDialog(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private void showErrorDialog(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    @FXML
    void mainPageButtonClicked(ActionEvent event) throws IOException {
        navigateToManageEmployeePage(event);
    }

    @FXML
    void uploadCsvFile(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV Files", "*.csv"));
        Stage stage = (Stage) tableView.getScene().getWindow();

        File selectedFile = fileChooser.showOpenDialog(stage);

        if (selectedFile != null) {
            try {
                lastUploadedCsvFile = selectedFile;
                csvDataList.clear(); // Clear existing data
                List<String[]> rawData = CsvService.readCsv(selectedFile.getAbsolutePath());
                for (String[] row : rawData) {
                    CSVData csvData = new CSVData();
                    csvData.setComments(row[0]);
                    csvData.setBreakTime(row[1]);
                    csvData.setExitHour(row[2]);
                    csvData.setEnterHour(row[3]);
                    csvData.setDay(row[4]);
                    csvDataList.add(csvData);
                }
                tableView.setItems(csvDataList);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }



    @FXML
    void displayCsvData() {
        if (csvData != null) {
            if (lastUploadedCsvFile != null) {
                // Create a new window or dialog to display the CSV data
                Stage csvDataStage = new Stage();
                csvDataStage.setTitle("CSV Data");

                TableView<String[]> tableView = new TableView<>();

                for (int i = 0; i < csvData.get(0).length; i++) {
                    final int columnIndex = i;
                    TableColumn<String[], String> column = new TableColumn<>("Column " + i);
                    column.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue()[columnIndex]));
                    tableView.getColumns().add(column);
                }

                tableView.getItems().addAll(csvData);

                Scene scene = new Scene(tableView);
                csvDataStage.setScene(scene);
                csvDataStage.show();
            } else {
                // Show an error message when no history to show
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Sorry, no history to show.");
                alert.showAndWait();
            }
        }
    }

    void displayLastUploadedCsvData() {
        if (!uploadedCSVFiles.isEmpty()) {
            String lastUploadedFile = uploadedCSVFiles.get(uploadedCSVFiles.size() - 1);
            try {
                csvData = CsvService.readCsv(lastUploadedFile);
                displayCsvData(); // Display the CSV data in a new window
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            // Handle no history error
            showErrorDialog("No History", "No CSV file history available.");
        }
    }

    @FXML
    void initialize() {
        // Initialize and configure the 'הערות' (tc_commit) column
        initColumn((TableColumn<CSVData, String>) tc_commit, "comments");

        // Initialize and configure the 'זמן הפסקה' (tc_breakTime) column
        initColumn((TableColumn<CSVData, String>) tc_breakTime, "breakTime");

        // Initialize and configure the 'שעת יציאה' (tc_exitHour) column
        initColumn((TableColumn<CSVData, String>) tc_exitHour, "exitHour");

        // Initialize and configure the 'שעת כניסה' (tc_enterHour) column
        initColumn((TableColumn<CSVData, String>) tc_enterHour, "enterHour");

        // Initialize and configure the 'יום' (tc_dayTable) column
        initColumn((TableColumn<CSVData, String>) tc_dayTable, "day");

        tc_commit.setEditable(true);
// Add similar lines for other columns (breakTime, exitHour, enterHour, day)
        tc_breakTime.setEditable(true);
        tc_exitHour.setEditable(true);
        tc_enterHour.setEditable(true);



    }

    private void initColumn(TableColumn<CSVData, String> column, String propertyName) {
        column.setCellValueFactory(new PropertyValueFactory<>(propertyName));
        column.setCellFactory(TextFieldTableCell.forTableColumn());
        column.setOnEditCommit(event -> {
            CSVData item = event.getRowValue();
            item.setComments(event.getNewValue());
        });
    }





}




