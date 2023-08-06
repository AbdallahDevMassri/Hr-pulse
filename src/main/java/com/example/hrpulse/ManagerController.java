package com.example.hrpulse;

import com.example.hrpulse.Service.Navigators;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.Button;


import java.io.IOException;

public class ManagerController implements Navigators {

    @FXML
    private Button EditEmployeeButton;

    @FXML
    private Button displayWorkerButton;

    @FXML
    private Button exitButton;

    @FXML
    private Button homePageButton;

    @FXML
    private Button reportsButton;

    @FXML
    private Button shiftHandlingbutton;

    @FXML
    void DisplayEmployees(ActionEvent event) throws IOException {
        navigateToDisplayEmployeesPage(event);
    }

    @FXML
    void NavigateToTimesPage(ActionEvent event) throws IOException {
        navigateToTimeEditPage(event);
    }

    @FXML
    void NavigateToManageDepartment(ActionEvent event) throws IOException {
    navigateToManageDepartment(event);
    }

    @FXML
    void NavigateToReportsPage(ActionEvent event) throws IOException {
        goToReportPage(event);
    }

    @FXML
    void ExitButtonClicked(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    void HomePageClicked(ActionEvent event) throws IOException {
        navigateToLoginPage(event);
    }

}
