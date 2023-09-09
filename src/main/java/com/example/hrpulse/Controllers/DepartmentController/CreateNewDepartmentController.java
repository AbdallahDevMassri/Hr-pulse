package com.example.hrpulse.Controllers.DepartmentController;

import com.example.hrpulse.Service.Intefaces.Navigators;
import com.example.hrpulse.Service.Objects.Department;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.io.IOException;

public class CreateNewDepartmentController implements Navigators {

    @FXML
    private TextField departmentNameLabel;

    @FXML
    private TextField descriptionOfDepartmentLabel;

    @FXML
    private ChoiceBox<String> statusDepartmentDropDown;

    @FXML
    void backToManageDepartmentClick(ActionEvent event) throws IOException {
        navigateToManageDepartment(event);
    }

    @FXML
    void saveDepartmentClicked(ActionEvent event) {

    }

    @FXML
    private void initialize() {

        statusDepartmentDropDown.setItems(FXCollections.observableArrayList("פעיל", "לא פעיל").sorted());
    }
}
