package com.example.hrpulse;

import com.example.hrpulse.Services.Database.DatabaseManager;
import com.example.hrpulse.Services.Database.DatabaseSessionManager;
import com.example.hrpulse.Services.Hibernate.HibernateUtil;
import com.example.hrpulse.Services.Objects.Department;
import com.example.hrpulse.Services.Objects.Employee;
import com.example.hrpulse.Controllers.EmployeeController.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.io.IOException;
import java.util.List;

public class HR_Pulse extends Application {
    private static SessionFactory sessionFactory;
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        // Initialize the DatabaseManager when the application starts
        sessionFactory = DatabaseManager.getSessionFactory();

        // Load the login view
        FXMLLoader fxmlLoader = new FXMLLoader(HR_Pulse.class.getResource("login-view.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, 600, 500);
        stage.setResizable(false);
        stage.setTitle("Hr-Pulse");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.show();

        // Close the SessionFactory when the application exits
        stage.setOnCloseRequest(event -> {
            DatabaseManager.closeSessionFactory();
        });
    }

    // Method to perform database operations after form submission
    public static void performDatabaseOperations(Employee employee) {
        DatabaseSessionManager sessionManager = new DatabaseSessionManager(DatabaseManager.getSessionFactory());

        // Save the employee to the database
        boolean saved = sessionManager.saveEmployee(employee);

        if (saved) {
            // Display confirmation
            System.out.println("Employee saved successfully.");
        } else {
            // Display error
            System.out.println("Error saving employee.");
        }

    }



}
