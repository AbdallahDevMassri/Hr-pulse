package com.example.hrpulse.Controllers.DepartmentController;

import com.example.hrpulse.Service.Intefaces.Navigators;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

import java.io.IOException;

public class ReportDepartmentController implements Navigators {

    @FXML
    private ListView<?> listOfDepartments;

    @FXML
    void backToManageDepartment(ActionEvent event) throws IOException {
        navigateToManageDepartment(event);

    }

    @FXML
    void showNotActiveDepartment(ActionEvent event) {

    }

}
