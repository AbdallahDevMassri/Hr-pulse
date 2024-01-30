package com.example.hrpulse;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.hibernate.SessionFactory;

import java.io.IOException;

/**
 * The `HR_Pulse` class is the main entry point for the HR Pulse application.
 */
public class HR_Pulse extends Application {

    // Hibernate SessionFactory for database operations
    public static SessionFactory sessionFactory;

    // Main method to launch the JavaFX application
    public static void main(String[] args) {
        launch(args);
    }

    // Method called on application start
    @Override
    public void start(Stage stage) throws IOException {
        // Initialize the DatabaseManager when the application starts
        sessionFactory = com.example.hrpulse.Services.Database.DatabaseManager.getSessionFactory();

        // Load the login view
        FXMLLoader fxmlLoader = new FXMLLoader(HR_Pulse.class.getResource("login-view.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);

        // Set up the stage properties
        stage.setResizable(false);
        stage.setTitle("Hr-Pulse");
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();

        // Close the SessionFactory when the application exits
        stage.setOnCloseRequest(event -> {
            com.example.hrpulse.Services.Database.DatabaseManager.closeSessionFactory();
        });
    }



}
