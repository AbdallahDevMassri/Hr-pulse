package com.example.hrpulse.Controllers.DepartmentController;

import com.example.hrpulse.Service.Intefaces.Navigators;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class EditDepartmentController implements Navigators {

    @FXML
    void backToManageDepartment(ActionEvent event) throws IOException {
        navigateToManageDepartment(event);
    }

    @FXML
    void saveDetailsClicked(ActionEvent event) {

    }

}
