package com.example.hrpulse;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("login-view.fxml"));


        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, 600, 500);
        stage.setTitle("Hr-Pulse");

        stage.setScene(scene);

// Prevent the stage from being resizable
        stage.setResizable(false);

// Center the title in the stage
        stage.centerOnScreen();

        stage.show();

    }

    public static void main(String[] args) {
        


        launch();
    }
}