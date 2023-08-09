package com.example.hrpulse.Controllers.EmployeeController;

import com.example.hrpulse.Service.Intefaces.EmployeeNavigators;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class feedBackEmployeeController implements EmployeeNavigators {

    @FXML
    void BackToManageEmployeeClicked(ActionEvent event) throws IOException {
        navigateToManageEmployeePage(event);
    }

}
