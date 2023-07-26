package com.example.hrpulse;

import com.example.hrpulse.Service.Navigators;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class DisplayEmployeesController implements Navigators {

    @FXML
    private Button backButton;

    @FXML
    void GoToHomeButtonClicked(ActionEvent event) throws IOException {
    navigateToManagerPage(event);
    }

}
