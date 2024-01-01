package com.example.hrpulse.Services.CSV;

import javafx.application.Application;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

import java.io.File;

public class  CSVUploadApp extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("CSV Upload Example");

        FileChooser fileChooser = new FileChooser();
        Button uploadButton = new Button("Upload CSV File");

        uploadButton.setOnAction(e -> {
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV Files", "*.csv"));
            File selectedFile = fileChooser.showOpenDialog(primaryStage);

            if (selectedFile != null) {
                String fileName = selectedFile.getName();
                System.out.println("Selected File: " + fileName);
                // Here, you would proceed to read and display the CSV content.
            }
        });

        VBox vbox = new VBox(uploadButton);
        Scene scene = new Scene(vbox, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
