package com.example.hrpulse;

import com.example.hrpulse.Service.Navigators;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class ReportsController implements Navigators {

    @FXML
    private Button BackButton;

    @FXML
    void goToMain(ActionEvent event) throws IOException {
        navigateToManagerPage(event);
    }

}
