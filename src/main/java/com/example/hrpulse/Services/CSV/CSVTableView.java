package com.example.hrpulse.Services.CSV;

import com.example.hrpulse.Services.CSV.CsvService;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVTableView extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    private ObservableList<Person> data = FXCollections.observableArrayList();

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("TableView Example");

        TableView<Person> tableView = new TableView<>();

        // Create columns
        TableColumn<Person, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        nameColumn.setCellFactory(TextFieldTableCell.forTableColumn()); // Allow editing
        nameColumn.setOnEditCommit(event -> {
            Person person = event.getTableView().getItems().get(event.getTablePosition().getRow());
            person.setName(event.getNewValue());
        });

        tableView.getColumns().add(nameColumn);
        tableView.setItems(data);

        Button loadButton = new Button("Load CSV");
        Button saveButton = new Button("Save CSV");

        // Load CSV data into the TableView
        loadButton.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV Files", "*.csv"));
            File selectedFile = fileChooser.showOpenDialog(primaryStage);

            if (selectedFile != null) {
                try {
                    List<String[]> csvData = CsvService.readCsv(selectedFile.getAbsolutePath());
                    data.clear(); // Clear existing data
                    for (String[] row : csvData) {
                        data.add(new Person(row[0]));
                    }
                } catch (IOException ex) {
                    showErrorDialog("Error loading CSV", "An error occurred while loading the CSV file.");
                }
            }
        });

        // Save TableView data to a CSV file
        saveButton.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV Files", "*.csv"));
            File selectedFile = fileChooser.showSaveDialog(primaryStage);

            if (selectedFile != null) {
                List<String[]> csvData = convertDataToCsvFormat(data);
                CsvService.writeCsv(selectedFile.getAbsolutePath(), csvData);
                showInformationDialog("CSV Saved", "CSV data saved successfully.");
            }
        });

        VBox vbox = new VBox(tableView, loadButton, saveButton);
        Scene scene = new Scene(vbox, 300, 250);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private List<String[]> convertDataToCsvFormat(ObservableList<Person> data) {
        List<String[]> csvData = new ArrayList<>();
        for (Person person : data) {
            csvData.add(new String[]{person.getName()});
        }
        return csvData;
    }

    private void showErrorDialog(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private void showInformationDialog(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public static class Person {
        private final StringProperty name;

        public Person(String name) {
            this.name = new SimpleStringProperty(name);
        }

        public String getName() {
            return name.get();
        }

        public StringProperty nameProperty() {
            return name;
        }

        public void setName(String name) {
            this.name.set(name);
        }
    }
}
