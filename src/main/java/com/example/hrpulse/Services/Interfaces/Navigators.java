package com.example.hrpulse.Services.Interfaces;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Objects;

public interface Navigators {



    default void navigateToLoginPage(ActionEvent event) throws IOException {

        Parent loginPageViewParent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/hrpulse/login-view.fxml")));
        Scene loginPageViewScene = new Scene(loginPageViewParent);
        Stage mainStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        mainStage.setScene(loginPageViewScene);
        mainStage.setTitle("Hr-Pulse");
        mainStage.setResizable(false);
        mainStage.centerOnScreen();
        mainStage.show();
    }

    default void navigateToMainPage(ActionEvent event) throws IOException {
        Parent managerPageViewParent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/hrpulse/UsersView/UsersView/Users_view.fxml")));
        Scene managerPageViewScene = new Scene(managerPageViewParent);
        Stage mainStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        mainStage.setScene(managerPageViewScene);
        mainStage.setTitle(" כניסה ראשית ");
        mainStage.centerOnScreen();
        mainStage.show();
    }

    // ------------------------------Department navigators ------------------------
    default void navigateToManageDepartment(ActionEvent event) throws IOException {
        Parent manageDepartmentPageViewParent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/hrpulse/DepartmentView/manageDepartment_view.fxml")));
        Scene manageDepartmentPageViewScene = new Scene(manageDepartmentPageViewParent);
        Stage mainStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        mainStage.setScene(manageDepartmentPageViewScene);
        mainStage.setTitle("ניהול מחלקות");
        mainStage.centerOnScreen();
        mainStage.show();
    }

    default void navigateToCreateDepartment(ActionEvent event) throws IOException {
        // Load the FXML file and create the Scene as before
        Parent createDepartmentPageViewParent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/hrpulse/DepartmentView/createNewDepartment_view.fxml")));
        Scene createDepartmentPageViewScene = new Scene(createDepartmentPageViewParent);
        // Get the main Stage and set its properties
        Stage mainStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        mainStage.setScene(createDepartmentPageViewScene);
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
        mainStage.centerOnScreen();
        mainStage.setTitle("דוח מחלקות");
        mainStage.show();
    }
}