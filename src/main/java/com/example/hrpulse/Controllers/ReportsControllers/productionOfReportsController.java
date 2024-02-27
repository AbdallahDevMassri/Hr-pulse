package com.example.hrpulse.Controllers.ReportsControllers;

import com.example.hrpulse.Services.Interfaces.Navigators;
import com.example.hrpulse.Services.Interfaces.ReportsNavigators;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.io.IOException;

/**
 * The `productionOfReportsController` class manages the user interface for generating reports.
 */
public class productionOfReportsController implements Navigators, ReportsNavigators {

    @FXML
    private Button back_btn;

    @FXML
    private Button employee_reports_btn;

    @FXML
    private Button shiftEmployee_reports_btn;


    @FXML
    void backToMainClicked(ActionEvent event) throws IOException {
        // Method to handle the "Back to Main" button click
        navigateToMainPage(event);
    }

    @FXML
    void shiftEmployeeReportsClicked(ActionEvent event) throws IOException {
        navigateToMonthlyShiftEmployee(event);
    }
    @FXML
    void shiftEmployeeReportByYear(ActionEvent event) throws IOException{
        navigateToReportByYear(event);
    }


    @FXML
    void employeeRepotsClicked(ActionEvent event) {
        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            // Establish a connection to the MySQL database
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/pulsedb", "root", "hrpulse123");

            // Path to the JR XML file (JasperReports XML template)
            String reportPath = "EmployeeReport.jrxml";

            // Compile the JRXML file into a JasperReport object
            JasperReport jr = JasperCompileManager.compileReport(reportPath);

            // Fill the report with data and create a JasperPrint object
            JasperPrint jp = JasperFillManager.fillReport(jr, null, connection);

            JasperViewer viewer = new JasperViewer(jp, false);
            viewer.setZoomRatio(0.5f); // Set zoom level to 50%
            viewer.setVisible(true);

            // Close the database connection
            connection.close();

        } catch (ClassNotFoundException | SQLException | JRException e) {
            throw new RuntimeException(e);
        }
    }
}
