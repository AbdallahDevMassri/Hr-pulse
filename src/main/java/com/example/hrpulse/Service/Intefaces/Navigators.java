package com.example.hrpulse.Service.Intefaces;
/**
 * this class  is used to handle all the navigators between the pages
 */

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public interface Navigators {
    // navigate to login page
    default void navigateToLoginPage(ActionEvent event) throws IOException {
        Parent loginPageViewParent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/hrpulse/login-view.fxml")));
        Scene loginPageViewScene = new Scene(loginPageViewParent);
        Stage mainStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        mainStage.setScene(loginPageViewScene);
        mainStage.show();
    }

    default void navigateToManagerPage(ActionEvent event) throws IOException {
        Parent managerPageViewParent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/hrpulse/manager_view.fxml")));
        Scene managerPageViewScene = new Scene(managerPageViewParent);
        Stage mainStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        mainStage.setScene(managerPageViewScene);
        mainStage.show();
    }

    default void navigateToHeadPage(ActionEvent event) throws IOException {
        Parent headPageViewParent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/hrpulse/head_view.fxml")));
        Scene headPageViewScene = new Scene(headPageViewParent);
        Stage mainStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        mainStage.setScene(headPageViewScene);
        mainStage.show();
    }

    default void navigateToSecretaryPage(ActionEvent event) throws IOException {
        Parent secretaryPageViewParent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/hrpulse/secretariat_view.fxml")));
        Scene secretaryPageViewScene = new Scene(secretaryPageViewParent);
        Stage mainStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        mainStage.setScene(secretaryPageViewScene);
        mainStage.show();
    }

    default void navigateToDisplayEmployeesPage(ActionEvent event) throws IOException {
        Parent homePageViewParent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/hrpulse/employees_view.fxml")));
        Scene homePageViewScene = new Scene(homePageViewParent);
        Stage mainStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        mainStage.setScene(homePageViewScene);
        mainStage.show();
    }

    default void navigateToRegularEmployeesPage(ActionEvent event) throws IOException {
        Parent employeePageViewParent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/hrpulse/regularWorker_view.fxml")));
        Scene employeePageViewScene = new Scene(employeePageViewParent);
        Stage mainStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        mainStage.setScene(employeePageViewScene);
        mainStage.show();
    }


    default void goToEditEmployeePage(ActionEvent event) throws IOException {
        Parent editEmployeeViewParent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/hrpulse/editEmployee_view.fxml")));
        Scene editEmployeeViewScene = new Scene(editEmployeeViewParent);
        Stage mainStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        mainStage.setScene(editEmployeeViewScene);
        mainStage.show();
    }

    default void navigateToTimeEditPage(ActionEvent event) throws IOException {
        Parent timeEditViewParent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/hrpulse/timeEditor_view.fxml")));
        Scene timeEditViewScene = new Scene(timeEditViewParent);
        Stage mainStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        mainStage.setScene(timeEditViewScene);
        mainStage.show();
    }

    default void goToReportPage(ActionEvent event) throws IOException {
        Parent reportViewParent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/hrpulse/reportsPage_view.fxml")));
        Scene reportEmployeeViewScene = new Scene(reportViewParent);
        Stage mainStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        mainStage.setScene(reportEmployeeViewScene);
        mainStage.show();
    }

    default void navigateToManageDepartment(ActionEvent event) throws IOException {
        // Update the relative path to the FXML file within the resources folder
        Parent manageDepartmentPageViewParent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/hrpulse/DepartmentView/manageDepartment_view.fxml")));
        Scene manageDepartmentPageViewScene = new Scene(manageDepartmentPageViewParent);
        Stage mainStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        mainStage.setScene(manageDepartmentPageViewScene);
        mainStage.setTitle("ניהול מחלקות");
        mainStage.show();
    }

    default void navigateToCreateDepartment(ActionEvent event) throws IOException {
        // Load the FXML file and create the Scene as before
        Parent createDepartmentPageViewParent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/hrpulse/DepartmentView/createNewDepratment_view.fxml")));
        Scene createDepartmentPageViewScene = new Scene(createDepartmentPageViewParent);
        // Get the main Stage and set its properties
        Stage mainStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        mainStage.setScene(createDepartmentPageViewScene);
        // Set the Stage properties for resizable and on Center of the screen
        mainStage.setResizable(true);
        mainStage.centerOnScreen();
        mainStage.setTitle("יצירת מחלקה חדשה ");
        mainStage.show();

    }
    default void navigateEditDepartment(ActionEvent event) throws IOException {
        // Load the FXML file and create the Scene as before
        Parent editDepartmentPageViewParent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/hrpulse/DepartmentView/editDepartment_view.fxml")));
        Scene editDepartmentPageViewScene = new Scene(editDepartmentPageViewParent);
        // Get the main Stage and set its properties
        Stage mainStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        mainStage.setScene(editDepartmentPageViewScene);
        // Set the Stage properties for resizable and on Center of the screen
        mainStage.setResizable(true);
        mainStage.centerOnScreen();
        mainStage.setTitle("עדכון נתוני מחלקה");
        mainStage.show();

    }
    default void navigateToReportOfDepartment(ActionEvent event) throws IOException {
        // Load the FXML file and create the Scene as before
        Parent ReportOfDepartmentPageViewParent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/hrpulse/DepartmentView/reportDepartment_view.fxml")));
        Scene ReportOfDepartmentPageViewScene = new Scene(ReportOfDepartmentPageViewParent);
        // Get the main Stage and set its properties
        Stage mainStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        mainStage.setScene(ReportOfDepartmentPageViewScene);
        // Set the Stage properties for resizable and on Center of the screen
        mainStage.setResizable(true);
        mainStage.centerOnScreen();
        mainStage.setTitle("דוח מחלקות");
        mainStage.show();

    }
}