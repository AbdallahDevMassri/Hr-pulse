package com.example.hrpulse.Controllers.EmployeeController;

import com.example.hrpulse.Services.Interfaces.EmployeeNavigators;
import com.example.hrpulse.Services.Interfaces.Navigators;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import java.io.IOException;

/**
 * The `ManageEmployeeController` class manages the user interface for managing employees.
 */
public class ManageEmployeeController implements Navigators, EmployeeNavigators {

    /**
     * Handles the action when the "Add Employee" button is clicked.
     *
     * @param event The ActionEvent triggered by the user.
     * @throws IOException If an I/O error occurs.
     */
    @FXML
    void addEmployeeClicked(ActionEvent event) throws IOException {
        navigateToCreateEmployeePage(event);
    }

    /**
     * Handles the action when the "Back to Manager Page" button is clicked.
     *
     * @param event The ActionEvent triggered by the user.
     * @throws IOException If an I/O error occurs.
     */
    @FXML
    void backToManagerPage(ActionEvent event) throws IOException {
        navigateToMainPage(event);
    }

    /**
     * Handles the action when the "Edit Shift" button is clicked.
     *
     * @param event The ActionEvent triggered by the user.
     * @throws IOException If an I/O error occurs.
     */
    @FXML
    void editShiftClicked(ActionEvent event) throws IOException {
        navigateToEditEmployeeShiftPage(event);
    }

    /**
     * Handles the action when the "Feedback" button is clicked.
     *
     * @param event The ActionEvent triggered by the user.
     * @throws IOException If an I/O error occurs.
     */
    @FXML
    void feedbackClicked(ActionEvent event) throws IOException {
        navigateToFeedbackEmployeePage(event);
    }

    /**
     * Handles the action when the "Edit Employee" button is clicked.
     *
     * @param event The ActionEvent triggered by the user.
     * @throws IOException If an I/O error occurs.
     */
    @FXML
    void editEmployeeClicked(ActionEvent event) throws IOException {
        navigateToEditEmployeePage(event);
    }
}
