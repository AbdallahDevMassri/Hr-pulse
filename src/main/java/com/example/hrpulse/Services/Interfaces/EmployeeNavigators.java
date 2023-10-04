package com.example.hrpulse.Services.Interfaces;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public interface EmployeeNavigators extends Navigators{

    default void navigateToManageEmployeePage(ActionEvent event) throws IOException {
        Parent ManageEmployeePageViewParent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/hrpulse/EmployeeView/manageEmployee_view.fxml")));
        Scene ManageEmployeePageViewScene = new Scene(ManageEmployeePageViewParent);
        Stage mainStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        openInFullScreen(mainStage,ManageEmployeePageViewScene,"ניהול עובד");
    }
    default void navigateToCreateEmployeePage(ActionEvent event) throws IOException {
        Parent createEmployeeViewParent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/hrpulse/EmployeeView/createNewEmployee_view.fxml")));
        Scene createEmployeeViewScene = new Scene(createEmployeeViewParent);
        Stage mainStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        openInFullScreen(mainStage,createEmployeeViewScene,"יצירת עובד חדש");

    }
    default void navigateToRemoveEmployeePage(ActionEvent event) throws IOException {
        Parent removeEmployeeViewParent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/hrpulse/EmployeeView/removeEmployee_view.fxml")));
        Scene removeEmployeeViewScene = new Scene(removeEmployeeViewParent);
        Stage mainStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        openInFullScreen(mainStage,removeEmployeeViewScene,"מחיקת עובד");
    }
    default void navigateToEditEmployeeRolePage(ActionEvent event) throws IOException {
        Parent editEmployeeRoleViewParent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/hrpulse/EmployeeView/editEmployeeRole_view.fxml")));
        Scene editEmployeeRoleViewScene = new Scene(editEmployeeRoleViewParent);
        Stage mainStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        openInFullScreen(mainStage,editEmployeeRoleViewScene,"עדכון תפקיד לעובד");
    }
    default void navigateToEditEmployeeShiftPage(ActionEvent event) throws IOException {
        Parent editEmployeeShiftViewParent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/hrpulse/EmployeeView/editEmployeeShift_view.fxml")));
        Scene editEmployeeShiftViewScene = new Scene(editEmployeeShiftViewParent);
        Stage mainStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        openInFullScreen(mainStage,editEmployeeShiftViewScene,"עדכון שעות עובד");
    }
    default void navigateToFeedbackEmployeePage(ActionEvent event) throws IOException {
        Parent FeedbackEmployeeViewParent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/hrpulse/EmployeeView/feedBackEmployee_view.fxml")));
        Scene FeedbackEmployeeViewScene = new Scene(FeedbackEmployeeViewParent);
        Stage mainStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        openInFullScreen(mainStage,FeedbackEmployeeViewScene,"דף משוב לעובד");
    }
    default void navigateToReportPage(ActionEvent event) throws IOException {
        Parent reportViewParent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/hrpulse/EmployeeView/reportEmployees_view.fxml")));
        Scene reportEmployeeViewScene = new Scene(reportViewParent);
        Stage mainStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        openInFullScreen(mainStage,reportEmployeeViewScene,"דוח עובדים");

    }
}
