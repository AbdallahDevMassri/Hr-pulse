package com.example.hrpulse.Services.Interfaces;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Objects;

public interface ReportsNavigators {

    default void navigateToProductionOfReportsPage(ActionEvent event) throws IOException {
        Parent ReportPageViewParent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/hrpulse/productionOfReports_view/productionOfReports/productionOfReports_view.fxml")));
        Scene ReportPageViewScene = new Scene(ReportPageViewParent);
        Stage mainStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        mainStage.setScene(ReportPageViewScene);
        mainStage.setTitle("הפקת דוחות");
        mainStage.centerOnScreen();
        mainStage.show();
    }
}
