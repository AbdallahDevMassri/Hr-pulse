package com.example.hrpulse.Controllers.ReportsControllers;

import com.example.hrpulse.Services.Interfaces.Navigators;
import com.example.hrpulse.Services.Interfaces.ReportsNavigators;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import net.sf.jasperreports.engine.JRException;
//import net.sf.jasperreports.engine.JasperCompileManager;
//import net.sf.jasperreports.engine.JasperFillManager;
//import net.sf.jasperreports.engine.JasperPrint;
//import net.sf.jasperreports.view.JasperViewer;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//import java.util.HashMap;
//import java.util.Map;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;

import java.io.IOException;

public class productionOfReportsController implements Navigators, ReportsNavigators {

    @FXML
    private Button back_btn;

    @FXML
    private Button department_reports_btn;

    @FXML
    private Button employee_repots_btn;

    @FXML
    void backToMainClicked(ActionEvent event) throws IOException {
        navigateToManagerPage(event);
    }



    @FXML
    void departmentReportsClicked(ActionEvent event) {

    }

    @FXML
    void employeeRepotsClicked(ActionEvent event) {
//        try {
//            // Load and compile the JasperReport
//            String reportPath = "EmployeeReport.jrxml";
//            JasperCompileManager.compileReportToFile(reportPath);
//
//            // Establish a connection to the MySQL database
//            Connection connection = getConnection();
//
//            // Fill the JasperReport with data from the database
//            JasperPrint jasperPrint = JasperFillManager.fillReport(reportPath, null, connection);
//
//            // Display the JasperReport in a viewer
//            JasperViewer.viewReport(jasperPrint, false);
//
//            // Close the database connection
//            connection.close();
//        } catch (JRException | SQLException e) {
//            e.printStackTrace();
//            // Handle the exception (show an alert, log the error, etc.)
//        }

    }
}
    // Establish a connection to the MySQL database
//    private Connection getConnection() throws SQLException {
//        String jdbcUrl = "jdbc:mysql://localhost:3306/hrpulsedb";
//        String username = "root";
//        String password = "0523239955";
//        return DriverManager.getConnection(jdbcUrl, username, password);
//}




