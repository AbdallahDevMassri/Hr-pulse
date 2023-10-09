package com.example.hrpulse.Controllers.EmployeeController;

import com.example.hrpulse.Services.Interfaces.EmployeeNavigators;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;


import java.io.IOException;

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
    void saveButtonClicked(ActionEvent event) {

    }
    @FXML
    void mainPageButtonClicked(ActionEvent event) throws IOException {
   navigateToManageEmployeePage(event);
    }

}
