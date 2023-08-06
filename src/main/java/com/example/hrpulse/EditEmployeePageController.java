package com.example.hrpulse;

import com.example.hrpulse.Service.Intefaces.Navigators;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class EditEmployeePageController implements Navigators {

    @FXML
    private Button backButton;

    @FXML
    void goToMain(ActionEvent event) throws IOException {
    navigateToManagerPage(event);
    }

}
