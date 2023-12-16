package com.example.hrpulse.Controllers.ReportsControllers;

import com.example.hrpulse.Services.Interfaces.Navigators;
import com.example.hrpulse.Services.Interfaces.ReportsNavigators;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class productionOfReportsController implements Navigators, ReportsNavigators {

    @FXML
    private Button back_btn;

    @FXML
    private Button company_reports_btn;

    @FXML
    private Button department_reports_btn;

    @FXML
    private Button employee_repots_btn;

    @FXML
    void backToMainClicked(ActionEvent event) throws IOException {
        navigateToManagerPage(event);
    }

    @FXML
    void companyReportsClicked(ActionEvent event) {

    }

    @FXML
    void departmentReportsClicked(ActionEvent event) {

    }

    @FXML
    void employeeRepotsClicked(ActionEvent event) {

    }

}




