package com.example.hrpulse.Controllers.ReportsControllers;

import com.example.hrpulse.Services.Interfaces.ReportsNavigators;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;

import java.io.IOException;

public class MonthlyShiftEmployeeController implements ReportsNavigators {
    @FXML
    private ChoiceBox<?> cb_retriveEmployee;

    @FXML
    void back_btn(ActionEvent event) throws IOException {
        navigateToProductionOfReportsPage(event);
    }

    @FXML
    void showListClicked(ActionEvent event) {

    }
}
