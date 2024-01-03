package com.example.hrpulse.Controllers.UsersControllers;

import com.example.hrpulse.Services.Interfaces.EmployeeNavigators;
import com.example.hrpulse.Services.Interfaces.Navigators;
import com.example.hrpulse.Services.Interfaces.ReportsNavigators;
import com.example.hrpulse.Services.Objects.Employee;
import com.example.hrpulse.Session.UserSession;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class UserController implements Navigators , EmployeeNavigators, ReportsNavigators {

    @FXML
    private Label label_userName;
    @FXML
    private BorderPane borderPane;
    public void initialize() {
        // Get the current user from the UserSession
        UserSession userSession = UserSession.getInstance();
        Employee currentUser = userSession.getCurrentUser();

        System.out.println(currentUser.getEmployeeRole());
        System.out.println(currentUser.getFirstName());
        if (currentUser != null) {
            // Set the text of the label_userName to the user's name
            label_userName.setText("User: " + currentUser.getFirstName());
            // Apply different styles based on the user's role
            if ("manager".equals(currentUser.getEmployeeRole())) {
                // Manager role
                applyManagerStyles();
            } else if ("secretary".equals(currentUser.getEmployeeRole())) {
                // Secretary role
                applySecretaryStyles();
            }
        }
    }

    private void applySecretaryStyles() {
        // Remove existing style classes to prevent conflicts
        borderPane.getStyleClass().removeAll("manager");

        // Add the secretary style class
        borderPane.getStyleClass().add("secretary");
    }

    private void applyManagerStyles() {
        // Remove existing style classes to prevent conflicts
        borderPane.getStyleClass().removeAll("secretary");

        // Add the manager style class
        borderPane.getStyleClass().add("manager");
    }


    @FXML
    void reportsClicked(ActionEvent event) throws IOException {
        navigateToProductionOfReportsPage(event);
    }

    @FXML
    void manageEmployeeClicked(ActionEvent event) throws IOException {
        navigateToManageEmployeePage(event);
    }

    @FXML
    void NavigateToManageDepartment(ActionEvent event) throws IOException {
        navigateToManageDepartment(event);
    }

    @FXML
    void NavigateToReportsPage(ActionEvent event) throws IOException {

    }

    @FXML
    void ExitButtonClicked(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    void HomePageClicked(ActionEvent event) throws IOException {
        navigateToLoginPage(event);
    }

}