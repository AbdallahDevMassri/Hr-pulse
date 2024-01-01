package com.example.hrpulse.Controllers.RoleControllers.HeadOfDepartment;

import com.example.hrpulse.Services.Interfaces.Navigators;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class HeadOfDepartmentController implements Navigators {

    @FXML
    void AddNewUserClicked(ActionEvent event) {

    }

    @FXML
    void ChangeUserClicked(ActionEvent event) throws IOException {
        navigateToLoginPage(event);
    }

    @FXML
    void ReportToManagerClicked(ActionEvent event) {

    }

    @FXML
    void ShowEmployeeDetailsClicked(ActionEvent event) {

    }

}
