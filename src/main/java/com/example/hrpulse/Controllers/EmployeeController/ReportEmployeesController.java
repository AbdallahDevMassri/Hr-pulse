package com.example.hrpulse.Controllers.EmployeeController;

import com.example.hrpulse.Services.Interfaces.Navigators;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class ReportEmployeesController implements Navigators {

    @FXML
    private Button backButton;

    @FXML
    void GoToHomeButtonClicked(ActionEvent event) throws IOException {
    navigateToManagerPage(event);
    }

}
