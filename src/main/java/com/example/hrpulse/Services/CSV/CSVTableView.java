package com.example.hrpulse.Services.CSV;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
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
import java.util.List;

public class CSVTableView extends Application {
    public static void main(String[] args) {
        launch(args);
    }

     ObservableList<CsvRow> data = FXCollections.observableArrayList();

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("TableView");

        TableView<CsvRow> tableView = new TableView<>();
        tableView.setEditable(true); // Enable editing

        TableColumn<CsvRow, String> totalWorkHoursColumn = new TableColumn<>("Total Work Hours");
        totalWorkHoursColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTotalWorkHours()));
        totalWorkHoursColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        totalWorkHoursColumn.setOnEditCommit(event -> {
            event.getRowValue().setTotalWorkHours(event.getNewValue());
        });

        TableColumn<CsvRow, String> breakTimeColumn = new TableColumn<>("Break time");
        breakTimeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getBreakTime()));
        breakTimeColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        breakTimeColumn.setOnEditCommit(event -> {
            event.getRowValue().setBreakTime(event.getNewValue());
        });

        TableColumn<CsvRow, String> endTimeColumn = new TableColumn<>("End time");
        endTimeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getExitHour()));
        endTimeColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        endTimeColumn.setOnEditCommit(event -> {
            event.getRowValue().setExitHour(event.getNewValue());
        });

        TableColumn<CsvRow, String> startTimeColumn = new TableColumn<>("Start time");
        startTimeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStartHour()));
        startTimeColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        startTimeColumn.setOnEditCommit(event -> {
            event.getRowValue().setStartHour(event.getNewValue());
        });

        TableColumn<CsvRow, String> dateColumn = new TableColumn<>("Date");
        dateColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDateTable()));
        dateColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        dateColumn.setOnEditCommit(event -> {
            event.getRowValue().setDateTable(event.getNewValue());
        });

        TableColumn<CsvRow, String> empIdColumn = new TableColumn<>("EmployeeID");
        empIdColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEmployeeId()));
        empIdColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        empIdColumn.setOnEditCommit(event -> {
            event.getRowValue().setEmployeeId(event.getNewValue());
        });

        TableColumn<CsvRow, String> lastEditorColumn = new TableColumn<>("Last Editor");
        lastEditorColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getLastEditor()));
        lastEditorColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        lastEditorColumn.setOnEditCommit(event -> {
            event.getRowValue().setLastEditor(event.getNewValue());
        });

        tableView.getColumns().addAll(totalWorkHoursColumn, breakTimeColumn, endTimeColumn, startTimeColumn, dateColumn, empIdColumn, lastEditorColumn);
        tableView.setItems(data);

        Button loadButton = new Button("Load CSV");

        Button addButton = new Button("Add Row");
        addButton.setOnAction(e -> addNewRow(tableView));

        loadButton.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV Files", "*.csv"));
            File selectedFile = fileChooser.showOpenDialog(primaryStage);

            if (selectedFile != null) {
                try {
                    List<String[]> csvData = CsvService.readCsv(selectedFile.getAbsolutePath());
                    data.clear(); // Clear existing data
                    for (String[] row : csvData) {
                        CsvRow newRow = new CsvRow(row[0], row[1], row[2], row[3], row[4], row[5], row[6], row[7]);
                        data.add(newRow);
                    }
                } catch (IOException ex) {
                    showErrorDialog("Error loading CSV", "An error occurred while loading the CSV file.");
                }
            }
        });

        VBox vbox = new VBox(tableView, loadButton);
        Scene scene = new Scene(vbox, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void showErrorDialog(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private void addNewRow(TableView<CsvRow> tableView) {
        // Create a new row with default values or empty values
        CsvRow newRow = new CsvRow("", "", "", "", "", "", "", "");
        data.add(newRow);
        tableView.scrollTo(newRow); // Scroll to the new row
        tableView.getSelectionModel().select(newRow); // Select the new row for editing
        tableView.edit(tableView.getItems().indexOf(newRow), tableView.getColumns().get(0)); // Start editing the first cell
    }


}
