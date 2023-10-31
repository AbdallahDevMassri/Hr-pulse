package com.example.hrpulse.Controllers.RoleControllers.Manager;

import com.example.hrpulse.Services.Interfaces.EmployeeNavigators;
import com.example.hrpulse.Services.Interfaces.Navigators;
import com.example.hrpulse.Services.Objects.Employee;
import com.example.hrpulse.Session.UserSession;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import java.io.IOException;

public class ManagerController implements Navigators , EmployeeNavigators {

    @FXML
    private Label label_userName;
    public void initialize() {
        // Get the current user from the UserSession
        UserSession userSession = UserSession.getInstance();
        Employee currentUser = userSession.getCurrentUser();

        if (currentUser != null) {
            // Set the text of the label_userName to the user's name
            label_userName.setText("User: " + currentUser.getFirstName());
        }
    }


    @FXML
    void DisplayEmployees(ActionEvent event) throws IOException {
        navigateToDisplayEmployeesPage(event);
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
