package com.example.hrpulse.Controllers.EmployeeController;

import com.example.hrpulse.Service.Intefaces.EmployeeNavigators;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class RemoveEmployeeController implements EmployeeNavigators {

    @FXML
    void BackToManageEmployee(ActionEvent event) throws IOException {
    navigateToManageEmployeePage(event);
    }

}
