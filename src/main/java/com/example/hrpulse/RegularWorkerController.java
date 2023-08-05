package com.example.hrpulse;

import com.example.hrpulse.Service.Navigators;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class RegularWorkerController implements Navigators {

    @FXML
    void Navigate2LoginPageClicked(ActionEvent event) throws IOException {
navigateToLoginPage(event);
    }

}
