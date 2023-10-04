package com.example.hrpulse.Controllers.EmployeeController;

import com.example.hrpulse.Services.Interfaces.EmployeeNavigators;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class EditEmployeeShiftController implements EmployeeNavigators {

    @FXML
    private Button ButtonBack;

    @FXML
    void mainPageButtonClicked(ActionEvent event) throws IOException {
   navigateToManageEmployeePage(event);
    }

}
