package com.example.hrpulse.Service;
/**
 * this class  is used to handle all the navigators between the pages
 */

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public interface Navigators {
    // navigate to login page
    default void navigateToLoginPage(ActionEvent event) throws IOException {
        Parent loginPageViewParent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("login-view.fxml")));
        Scene loginPageViewScene = new Scene(loginPageViewParent);
        Stage mainStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        mainStage.setScene(loginPageViewScene);
        mainStage.show();
    }
    default void navigateToManagerPage(ActionEvent event) throws IOException {
        Parent managerPageViewParent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("manager_view.fxml")));
        Scene managerPageViewScene = new Scene(managerPageViewParent);
        Stage mainStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        mainStage.setScene(managerPageViewScene);
        mainStage.show();
    }
    default void navigateToHeadPage(ActionEvent event) throws IOException {
        Parent headPageViewParent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("head_view.fxml")));
        Scene headPageViewScene = new Scene(headPageViewParent);
        Stage mainStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        mainStage.setScene(headPageViewScene);
        mainStage.show();
    }
    default void navigateToSecretaryPage(ActionEvent event) throws IOException {
        Parent secretaryPageViewParent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("secretariat_view.fxml")));
        Scene secretaryPageViewScene = new Scene(secretaryPageViewParent);
        Stage mainStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        mainStage.setScene(secretaryPageViewScene);
        mainStage.show();
    }
    default void navigateToDisplayEmployeesPage(ActionEvent event) throws IOException {
        Parent homePageViewParent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("employees_view.fxml")));
        Scene homePageViewScene = new Scene(homePageViewParent);
        Stage mainStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        mainStage.setScene(homePageViewScene);
        mainStage.show();
    }
    default void navigateToRegularEmployeesPage(ActionEvent event) throws IOException {
        Parent employeePageViewParent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("regularWorker_view.fxml")));
        Scene employeePageViewScene = new Scene(employeePageViewParent);
        Stage mainStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        mainStage.setScene(employeePageViewScene);
        mainStage.show();
    }


    default void goToEditEmployeePage(ActionEvent event) throws IOException {
        Parent editEmployeeViewParent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("editEmployee_view.fxml")));
        Scene editEmployeeViewScene = new Scene(editEmployeeViewParent);
        Stage mainStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        mainStage.setScene(editEmployeeViewScene);
        mainStage.show();
    }

    default void navigateToTimeEditPage(ActionEvent event) throws IOException {
        Parent timeEditViewParent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("timeEditor_view.fxml")));
        Scene timeEditViewScene = new Scene(timeEditViewParent);
        Stage mainStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        mainStage.setScene(timeEditViewScene);
        mainStage.show();
    }

    default void goToReportPage(ActionEvent event) throws IOException {
        Parent reportViewParent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("reportsPage_view.fxml")));
        Scene reportEmployeeViewScene = new Scene(reportViewParent);
        Stage mainStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        mainStage.setScene(reportEmployeeViewScene);
        mainStage.show();
    }
    default void navigateToManageDepartment(ActionEvent event) throws IOException {
        // Update the relative path to the FXML file within the resources folder
        Parent manageDepartmentPageViewParent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("department_view.fxml")));
        Scene manageDepartmentPageViewScene = new Scene(manageDepartmentPageViewParent);
        Stage mainStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        mainStage.setScene(manageDepartmentPageViewScene);
        mainStage.show();
    }
}