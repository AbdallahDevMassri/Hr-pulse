package com.example.hrpulse.Controllers.RoleControllers.HeadOfDepartment;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HeadOfDepartmentController {
    @FXML
    private Label welcomeLabel;

    public void initialize() {
        welcomeLabel.setText("Welcome, Head of Department!");
    }
}

