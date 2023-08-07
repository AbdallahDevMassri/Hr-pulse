package com.example.hrpulse.Controllers.DepartmentController;

import com.example.hrpulse.Service.Intefaces.Navigators;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class ManageDepartmentController implements Navigators {

    @FXML
    void navigateToEditDepartment(ActionEvent event) throws IOException {
    navigateEditDepartment(event);
    }

    @FXML
    void createDepartmentClicked(ActionEvent event) throws IOException {
        navigateToCreateDepartment(event);
    }

    @FXML
    void navigateToDepartmentDetails(ActionEvent event) throws IOException {
    navigateToReportOfDepartment(event);
    }

    @FXML
    void BackToMainClicked(ActionEvent event) throws IOException {
        navigateToManagerPage(event);
    }

}
