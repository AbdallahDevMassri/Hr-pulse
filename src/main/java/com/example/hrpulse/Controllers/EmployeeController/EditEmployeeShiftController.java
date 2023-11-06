package com.example.hrpulse.Controllers.EmployeeController;

import com.example.hrpulse.Services.CSV.CsvRow;
import com.example.hrpulse.Services.CSV.UploadCsvFile;
import com.example.hrpulse.Services.Interfaces.EmployeeNavigators;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;


import java.io.IOException;

public class EditEmployeeShiftController implements EmployeeNavigators {

    @FXML
    private ChoiceBox<?> cb_monthChoice;

    @FXML
    private ChoiceBox<?> cd_employeeChoice;

    @FXML
    private TableColumn<CsvRow, String> tc_totalWorkHrs;

    @FXML
    private TableColumn<CsvRow, String> tc_breakTime;

    @FXML
    private TableColumn<CsvRow, String> tc_exitHour;

    @FXML
    private TableColumn<CsvRow, String> tc_enterHour;

    @FXML
    private TableColumn<CsvRow, String> tc_dateTable;

    @FXML
    private TableColumn<CsvRow, String> tc_employeeId;

    @FXML
    private Label tf_countDays;

    @FXML
    private Label tf_countHours;

    @FXML
    private TableView<CsvRow> tableViewCSVData;

    private UploadCsvFile uploadCsvFile;

    public EditEmployeeShiftController() {
        uploadCsvFile = new UploadCsvFile(tableViewCSVData, "employeeshiftdata");
    }

    @FXML
    void uploadCsvFile(ActionEvent event) {
        uploadCsvFile.upload();
    }

    @FXML
    public void initialize() {
        // Define TableView columns
        tc_totalWorkHrs.setCellValueFactory(cellData -> cellData.getValue().totalWorkHoursProperty());
        tc_breakTime.setCellValueFactory(cellData -> cellData.getValue().breakTimeProperty());
        tc_exitHour.setCellValueFactory(cellData -> cellData.getValue().exitHourProperty());
        tc_enterHour.setCellValueFactory(cellData -> cellData.getValue().enterHourProperty());
        tc_dateTable.setCellValueFactory(cellData -> cellData.getValue().dateTableProperty());
        tc_employeeId.setCellValueFactory(cellData -> cellData.getValue().employeeIdProperty());
        
    }

    @FXML
    void saveButtonClicked(ActionEvent event) {
        uploadCsvFile.saveToCsv();
        uploadCsvFile.saveToDatabase();
    }

    @FXML
    void mainPageButtonClicked(ActionEvent event) throws IOException {
   navigateToManageEmployeePage(event);
    }


}
