package com.example.hrpulse.ManageDepratment;

import com.example.hrpulse.Service.Navigators;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class ManagerDepratmentController implements Navigators {

    @FXML
    void BackToMainClicked(ActionEvent event) throws IOException {
    navigateToManagerPage(event);
    }

}
