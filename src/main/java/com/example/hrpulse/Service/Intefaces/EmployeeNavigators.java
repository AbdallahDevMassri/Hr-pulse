package com.example.hrpulse.Service.Intefaces;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public interface EmployeeNavigators {

    default void navigateToManageEmployeePage(ActionEvent event) throws IOException {
        Parent ManageEmployeePageViewParent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/hrpulse/EmployeeView/manageEmployee_view.fxml")));
        Scene ManageEmployeePageViewScene = new Scene(ManageEmployeePageViewParent);
        Stage mainStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        mainStage.setScene(ManageEmployeePageViewScene);
        mainStage.setTitle("ניהול עובד");
        mainStage.centerOnScreen();
        mainStage.show();
    }
}
