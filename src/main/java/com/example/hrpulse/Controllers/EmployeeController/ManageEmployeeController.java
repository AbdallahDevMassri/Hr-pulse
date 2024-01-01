package com.example.hrpulse.Controllers.EmployeeController;

import com.example.hrpulse.Services.Interfaces.EmployeeNavigators;
import com.example.hrpulse.Services.Interfaces.Navigators;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class ManageEmployeeController implements Navigators, EmployeeNavigators {

    @FXML
    void addEmployeeClicked(ActionEvent event) throws IOException {
        navigateToCreateEmployeePage(event);
    }

    @FXML
    void backToManagerPage(ActionEvent event) throws IOException {
        navigateToMainPage(event);

    }

    @FXML
    void editShiftClicked(ActionEvent event) throws IOException {
        navigateToEditEmployeeShiftPage(event);
    }

    @FXML
    void employeeReportClicked(ActionEvent event) throws IOException {
    navigateToReportPage(event);
    }

    @FXML
    void feedbackClicked(ActionEvent event) throws IOException {
    navigateToFeedbackEmployeePage(event);
    }

    @FXML
    void editEmployeeClicked(ActionEvent event) throws IOException {
        navigateToEditEmployeePage(event);
    }



}
