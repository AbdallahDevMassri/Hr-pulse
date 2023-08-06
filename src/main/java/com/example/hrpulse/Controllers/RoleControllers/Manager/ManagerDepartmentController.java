package com.example.hrpulse.Controllers.RoleControllers.Manager;

import com.example.hrpulse.Service.Intefaces.Navigators;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class ManagerDepartmentController implements Navigators {

    @FXML
    void BackToMainClicked(ActionEvent event) throws IOException {
    navigateToManagerPage(event);
    }

}
