package com.example.hrpulse.Controllers.EmployeeController;

import com.example.hrpulse.Service.Intefaces.Navigators;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class ManageEmployeeController implements Navigators {

    @FXML
    void addEmployeeClicked(ActionEvent event) {

    }

    @FXML
    void backToManagerPage(ActionEvent event) throws IOException {
        navigateToManagerPage(event);

    }

    @FXML
    void editRoleClicked(ActionEvent event) {

    }

    @FXML
    void employeeReportClicked(ActionEvent event) {

    }

    @FXML
    void feedbackClicked(ActionEvent event) {

    }

    @FXML
    void removeEmployeeClicked(ActionEvent event) {

    }

    @FXML
    void updateRoleClicked(ActionEvent event) {

    }

}
