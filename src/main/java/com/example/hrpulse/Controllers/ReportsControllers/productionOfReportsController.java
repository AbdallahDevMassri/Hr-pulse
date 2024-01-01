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
        try {
            Class.forName("com.mysql.jdbc.Driver");

            // Establish a connection to the MySQL database
            Connection connection =DriverManager.getConnection("jdbc:mysql://localhost:3306/hrpulsedb","root","0523239955");
            String reportPath = "EmployeeReport.jrxml";

            JasperReport jr =JasperCompileManager.compileReport(reportPath);
            JasperPrint jp =JasperFillManager.fillReport(jr,null,connection);
            JasperViewer.viewReport(jp,false);
            connection.close();

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        } catch (JRException e) {
            throw new RuntimeException(e);
        }
    }

}





