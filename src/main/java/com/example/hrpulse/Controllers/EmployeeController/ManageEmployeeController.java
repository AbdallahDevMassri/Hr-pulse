package com.example.hrpulse.Controllers.EmployeeController;

import com.example.hrpulse.Service.Intefaces.EmployeeNavigators;
import com.example.hrpulse.Service.Intefaces.Navigators;
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
        navigateToManagerPage(event);

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
    void removeEmployeeClicked(ActionEvent event) throws IOException {
        navigateToRemoveEmployeePage(event);
    }

    @FXML
    void updateRoleClicked(ActionEvent event) throws IOException {
        navigateToEditEmployeeRolePage(event);
    }

}
