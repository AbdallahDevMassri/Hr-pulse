package com.example.hrpulse.Controllers.UsersControllers;

import com.example.hrpulse.Services.Interfaces.EmployeeNavigators;
import com.example.hrpulse.Services.Interfaces.Navigators;
import com.example.hrpulse.Services.Interfaces.ReportsNavigators;
import com.example.hrpulse.Services.Objects.Employee;
import com.example.hrpulse.Session.UserSession;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

/**
 * The `UserController` class manages the user interface for the logged-in user.
 */
public class UserController implements Navigators, EmployeeNavigators, ReportsNavigators {

    @FXML
    private Label label_userName;

    @FXML
    private Button reportsButton;

    @FXML
    private BorderPane borderPane;

    /**
     * Initializes the controller. Sets up the UI based on the logged-in user's role.
     */
    public void initialize() {
        // Get the current user from the UserSession
        UserSession userSession = UserSession.getInstance();
        Employee currentUser = userSession.getCurrentUser();

        if (currentUser != null) {
            // Set the text of the label_userName to the user's name
            label_userName.setText("User: " + currentUser.getFirstName());

            // Apply different styles based on the user's role
            if ("headOfDepartment".equals(currentUser.getEmployeeRole())) {
                // Head of Department role
                applyHeadOfDepartmentStyles();
            } else if ("secretary".equals(currentUser.getEmployeeRole())) {
                // Secretary role
                applySecretaryStyles();
            } else {
                // Manager role
                applyManagerStyles();
            }
        }
    }

    private void applyHeadOfDepartmentStyles() {
        // Remove existing style classes to prevent conflicts
        borderPane.getStyleClass().removeAll("secretary");
        // Add the headOfDepartment style class & Hide fields
        borderPane.getStyleClass().add("headOfDepartment");
        hideHeadOfDepartmentButtons();
    }

    private void hideHeadOfDepartmentButtons() {
        reportsButton.setVisible(false);
        // TODO: Add logic to hide other buttons specific to Head of Department
    }

    private void applySecretaryStyles() {
        // Remove existing style classes to prevent conflicts
        borderPane.getStyleClass().removeAll("headOfDepartment");
        // Add the secretary style class & Hide fields
        borderPane.getStyleClass().add("secretary");
        hideSecretaryButtons();
    }

    private void hideSecretaryButtons() {
        // TODO: Add logic to hide buttons specific to Secretary
    }

    private void applyManagerStyles() {
        // Remove existing style classes to prevent conflicts
        borderPane.getStyleClass().removeAll("headOfDepartment");
        // Add the manager style class
        borderPane.getStyleClass().add("manager");
        // TODO: Add logic for Manager-specific styles
    }

    @FXML
    void reportsClicked(ActionEvent event) throws IOException {
        // Method to handle the "Reports" button click
        navigateToProductionOfReportsPage(event);
    }

    @FXML
    void manageEmployeeClicked(ActionEvent event) throws IOException {
        // Method to handle the "Manage Employee" button click
        navigateToManageEmployeePage(event);
    }

    @FXML
    void NavigateToManageDepartment(ActionEvent event) throws IOException {
        // Method to handle the "Manage Department" button click
        navigateToManageDepartment(event);
    }

    @FXML
    void NavigateToReportsPage(ActionEvent event) throws IOException {
        // TODO: Method to handle the "Reports Page" button click

        // Show error or display a message for the user; this button will be used in the next version
    }

    @FXML
    void ExitButtonClicked(ActionEvent event) {
        // Method to handle the "Exit" button click
        Platform.exit();
    }

    @FXML
    void HomePageClicked(ActionEvent event) throws IOException {
        // Method to handle the "Home Page" button click
        navigateToLoginPage(event);
    }
}
