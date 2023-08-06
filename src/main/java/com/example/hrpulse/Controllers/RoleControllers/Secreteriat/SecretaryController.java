package com.example.hrpulse.Controllers.RoleControllers.Secreteriat;

import com.example.hrpulse.Service.Intefaces.Navigators;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class SecretaryController implements Navigators {

    @FXML
    private Button homePageButton;

    @FXML
    void homePageClicked(ActionEvent event) throws IOException {
    navigateToManagerPage(event);
    }

}
