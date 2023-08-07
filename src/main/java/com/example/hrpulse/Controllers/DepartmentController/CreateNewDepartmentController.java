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
        String name = departmentNameLabel.getText();
        String description = descriptionOfDepartmentLabel.getText();
        String status = statusDepartmentDropDown.getValue();

        // Convert status to a Boolean value
        boolean isActive = status != null && status.equals("Active");

        // Create the Department object
        Department department = new Department(name, isActive);

        // Do something with the department object (e.g., print its details)
        System.out.println("Department Name: " + department.getName());
        System.out.println("Description: " + department.getDescription());
        System.out.println("Status: " + department.getStatus());

    }
    // Initialize method is called automatically when the view is loaded
    @FXML
    private void initialize() {
        // Populate the choice box with the status options
        statusDepartmentDropDown.setItems(FXCollections.observableArrayList("פעיל", "לא פעיל").sorted());
    }
}
