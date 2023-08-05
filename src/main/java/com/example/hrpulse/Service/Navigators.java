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

public interface Navigators {
    // navigate to login page
    default void navigateToLoginPage(ActionEvent event) throws IOException {
        Parent loginPageViewParent = FXMLLoader.load(getClass().getResource("login-view.fxml"));
        Scene loginPageViewScene = new Scene(loginPageViewParent);
        Stage mainStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        mainStage.setScene(loginPageViewScene);
        mainStage.show();
    }
    default void navigateToManagerPage(ActionEvent event) throws IOException {
        Parent managerPageViewParent = FXMLLoader.load(getClass().getResource("manager_view.fxml"));
        Scene managerPageViewScene = new Scene(managerPageViewParent);
        Stage mainStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        mainStage.setScene(managerPageViewScene);
        mainStage.show();
    }
    default void navigateToHeadPage(ActionEvent event) throws IOException {
        Parent headPageViewParent = FXMLLoader.load(getClass().getResource("head_view.fxml"));
        Scene headPageViewScene = new Scene(headPageViewParent);
        Stage mainStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        mainStage.setScene(headPageViewScene);
        mainStage.show();
    }
    default void navigateToSecerterPage(ActionEvent event) throws IOException {
        Parent secerterPageViewParent = FXMLLoader.load(getClass().getResource("secerteria_view.fxml"));
        Scene secerterPageViewScene = new Scene(secerterPageViewParent);
        Stage mainStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        mainStage.setScene(secerterPageViewScene);
        mainStage.show();
    }
    default void navigateToDisplayEmployeesPage(ActionEvent event) throws IOException {
        Parent homePageViewParent = FXMLLoader.load(getClass().getResource("employees_view.fxml"));
        Scene homePageViewScene = new Scene(homePageViewParent);
        Stage mainStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        mainStage.setScene(homePageViewScene);
        mainStage.show();
    }
    default void navigateToRegularEmployeesPage(ActionEvent event) throws IOException {
        Parent employeePageViewParent = FXMLLoader.load(getClass().getResource("regularWorker_view.fxml"));
        Scene employeePageViewScene = new Scene(employeePageViewParent);
        Stage mainStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        mainStage.setScene(employeePageViewScene);
        mainStage.show();
    }


    default void goToEditEmployeePage(ActionEvent event) throws IOException {
        Parent editEmployeeViewParent = FXMLLoader.load(getClass().getResource("editEmployee_view.fxml"));
        Scene editEmployeeViewScene = new Scene(editEmployeeViewParent);
        Stage mainStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        mainStage.setScene(editEmployeeViewScene);
        mainStage.show();
    }

    default void navigateToTimeEditPage(ActionEvent event) throws IOException {
        Parent timeEditViewParent = FXMLLoader.load(getClass().getResource("timeEditor_view.fxml"));
        Scene timeEditViewScene = new Scene(timeEditViewParent);
        Stage mainStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        mainStage.setScene(timeEditViewScene);
        mainStage.show();
    }

    default void goToReportPage(ActionEvent event) throws IOException {
        Parent reportViewParent = FXMLLoader.load(getClass().getResource("reportsPage_view.fxml"));
        Scene reportEmployeeViewScene = new Scene(reportViewParent);
        Stage mainStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        mainStage.setScene(reportEmployeeViewScene);
        mainStage.show();
    }
}