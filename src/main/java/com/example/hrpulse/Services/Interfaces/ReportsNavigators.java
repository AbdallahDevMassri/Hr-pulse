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

    // ------------------------------ProductionOfReports navigator ------------------------
    default void navigateToProductionOfReportsPage(ActionEvent event) throws IOException {
        Parent ReportPageViewParent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/hrpulse/productionOfReports_view/productionOfReports/productionOfReports_view.fxml")));
        Scene ReportPageViewScene = new Scene(ReportPageViewParent);
        Stage mainStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        mainStage.setScene(ReportPageViewScene);
        mainStage.setTitle("הפקת דוחות");
        mainStage.centerOnScreen();
        mainStage.show();
    }

    // ------------------------------MonthlyShift navigator ------------------------
    default void navigateToMonthlyShiftEmployee(ActionEvent event) throws IOException {
        Parent MonthlyShiftEmployeeViewParent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/hrpulse/productionOfReports_view/productionOfReports/MonthlyShiftEmployee_report.fxml")));
        Scene MonthlyShiftEmployeeViewScene = new Scene(MonthlyShiftEmployeeViewParent);
        Stage mainStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        mainStage.setScene(MonthlyShiftEmployeeViewScene);
        mainStage.setTitle("הפקת דוחות");
        mainStage.centerOnScreen();
        mainStage.show();
    }
    default void navigateToReportByYear(ActionEvent event) throws IOException {
        Parent navigateToReportByYearViewParent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/hrpulse/productionOfReports_view/productionOfReports/shiftReportByYear.fxml")));
        Scene navigateToReportByYearViewScene = new Scene(navigateToReportByYearViewParent);
        Stage mainStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        mainStage.setScene(navigateToReportByYearViewScene);
        mainStage.setTitle("שעות שנתיות");
        mainStage.centerOnScreen();
        mainStage.show();
    }
}
