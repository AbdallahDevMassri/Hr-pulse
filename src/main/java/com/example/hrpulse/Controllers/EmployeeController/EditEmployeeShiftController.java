package com.example.hrpulse.Controllers.EmployeeController;

import com.example.hrpulse.Services.CSV.CsvRow;
import com.example.hrpulse.Services.CSV.UploadCsvFile;
import com.example.hrpulse.Services.Interfaces.EmployeeNavigators;
import com.example.hrpulse.Services.Objects.Employee;
import com.example.hrpulse.Session.UserSession;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import com.example.hrpulse.Services.Database.DatabaseManager;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.io.IOException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.example.hrpulse.HR_Pulse.sessionFactory;



public class EditEmployeeShiftController implements EmployeeNavigators {

    @FXML
    private ChoiceBox<?> cb_monthChoice;

    @FXML
    private ChoiceBox<?> cd_employeeChoice;

    @FXML
    private TableColumn<CsvRow, String> tc_totalWorkHrs;

    @FXML
    private TableColumn<CsvRow, String> tc_breakTime;

    @FXML
    private TableColumn<CsvRow, String> tc_exitHour;

    @FXML
    private TableColumn<CsvRow, String> tc_enterHour;

    @FXML
    private TableColumn<CsvRow, String> tc_dateTable;

    @FXML
    private TableColumn<CsvRow, String> tc_employeeId;

    @FXML
    private TableColumn<CsvRow, String> tc_comments;

    @FXML
    private TableColumn<CsvRow, String> tc_lastEditor;


    @FXML
    TableColumn<CsvRow, Void> deleteColumn = new TableColumn<>("Delete");



    @FXML
    private TableView<CsvRow> tableViewCSVData;

    private UploadCsvFile uploadCsvFile;

    @FXML
    private Button loadLastSavedDataButton;

    @FXML
    private TextField tf_employeeID;

    @FXML
    private Button saveButton;

    private Set<CsvRow> editedRows = new HashSet<>();

    private String csvFilePath;

    private boolean externallyAddedRowsExist;

    // Keep track of edited rows and their IDs
    private Map<String, CsvRow> editedRowsMap = new HashMap<>();

    UserSession userSession = UserSession.getInstance();
    Employee currentUser = userSession.getCurrentUser();





    public EditEmployeeShiftController() {

    }




    @FXML
    void uploadCsvFile(ActionEvent event) {
        // Get the current user from UserSession
        Employee currentUser = userSession.getCurrentUser();

        // Check if the current user is null (handle it appropriately)
        if (currentUser == null) {
            // Handle the case where currentUser is null
            System.err.println("Error: currentUser is null in uploadCsvFile");
            return;
        }

        String lastEditorUsername = currentUser.getUsername();
        System.out.println("uploadCsvFile - lastEditorUsername: " + lastEditorUsername);

        uploadCsvFile.upload(lastEditorUsername);

        // Set the CSV file path in the controller
        setCsvFilePath(uploadCsvFile.getFilePath());
    }



    @FXML
    public void initialize() {

        loadLastSavedDataButton.setOnAction(this::loadLastSavedDataIntoTableview); // Set action for the button

        uploadCsvFile = new UploadCsvFile(tableViewCSVData, "employeeshiftdata");

        tc_totalWorkHrs.setCellValueFactory(new PropertyValueFactory<>("totalWorkHours"));
        tc_totalWorkHrs.setCellFactory(TextFieldTableCell.forTableColumn());
        tc_totalWorkHrs.setOnEditCommit(event -> {
            CsvRow editedRow = event.getRowValue();
            editedRow.setTotalWorkHours(event.getNewValue());
            event.getRowValue().setTotalWorkHours(event.getNewValue());
            try {
                uploadCsvFile.updateRowInCsv(event.getRowValue());
                saveChanges(editedRow);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        tc_totalWorkHrs.setEditable(true);


        tc_breakTime.setCellValueFactory(new PropertyValueFactory<>("breakTime"));
        tc_breakTime.setCellFactory(TextFieldTableCell.forTableColumn());
        tc_breakTime.setOnEditCommit(event -> {
            CsvRow editedRow = event.getRowValue();
            editedRow.setBreakTime(event.getNewValue());
           // event.getRowValue().setBreakTime(event.getNewValue());
            try {
                uploadCsvFile.updateRowInCsv(event.getRowValue());
                saveChanges(editedRow);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        });
        tc_breakTime.setEditable(true);


        tc_exitHour.setCellValueFactory(new PropertyValueFactory<>("exitHour"));
        tc_exitHour.setCellFactory(TextFieldTableCell.forTableColumn());
        tc_exitHour.setOnEditCommit(event -> {
            CsvRow editedRow = event.getRowValue();
            editedRow.setExitHour(event.getNewValue());
           // event.getRowValue().setExitHour(event.getNewValue());
            try {
                uploadCsvFile.updateRowInCsv(event.getRowValue());
                saveChanges(editedRow);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        });

        tc_enterHour.setCellValueFactory(new PropertyValueFactory<>("startHour"));
        tc_enterHour.setCellFactory(TextFieldTableCell.forTableColumn());
        tc_enterHour.setOnEditCommit(event -> {
            CsvRow editedRow = event.getRowValue();
            editedRow.setStartHour(event.getNewValue());
           // event.getRowValue().setStartHour(event.getNewValue());
            try {
                uploadCsvFile.updateRowInCsv(event.getRowValue());
                saveChanges(editedRow);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        });

        tc_dateTable.setCellValueFactory(new PropertyValueFactory<>("dateTable"));
        tc_dateTable.setCellFactory(TextFieldTableCell.forTableColumn());
        tc_dateTable.setOnEditCommit(event -> {
            CsvRow editedRow = event.getRowValue();
            editedRow.setDateTable(event.getNewValue());
            // event.getRowValue().setDateTable(event.getNewValue());
            try {
                uploadCsvFile.updateRowInCsv(event.getRowValue());
                saveChanges(editedRow);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        });

        tc_employeeId.setCellValueFactory(new PropertyValueFactory<>("employeeId"));
        tc_employeeId.setCellFactory(TextFieldTableCell.forTableColumn());
        tc_employeeId.setOnEditCommit(event -> {
            CsvRow editedRow = event.getRowValue();
            editedRow.setEmployeeId(event.getNewValue());
            // event.getRowValue().setEmployeeId(event.getNewValue());
            try {
                uploadCsvFile.updateRowInCsv(event.getRowValue());
                saveChanges(editedRow);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        });

        tc_comments.setCellValueFactory(new PropertyValueFactory<>("comments"));
        tc_comments.setCellFactory(TextFieldTableCell.forTableColumn());
        tc_comments.setOnEditCommit(event -> {
            String newComment = event.getNewValue();
            CsvRow editedRow = event.getRowValue();

            if (newComment != null && newComment.length() >= 4) {
                // Update the comment in the row
                editedRow.setComments(newComment);
                System.out.println("Comments set: " + newComment);

                // Save the comment to the database
                saveCommentToDatabase(editedRow);
            }
        });

        deleteColumn.setCellFactory(param -> new TableCell<>() {
            private final Button deleteButton = new Button("Delete");

            {
                deleteButton.setOnAction(event -> {
                    CsvRow selectedRow = getTableView().getItems().get(getIndex());
                    handleDeleteButton(selectedRow);
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(deleteButton);
                }
            }
        });

        tableViewCSVData.getColumns().add(deleteColumn);

        // Set edit commit handlers for each column
        setEditCommitHandler(tc_totalWorkHrs);
        setEditCommitHandler(tc_breakTime);
        setEditCommitHandler(tc_exitHour);
        setEditCommitHandler(tc_enterHour);
        setEditCommitHandler(tc_dateTable);
        setEditCommitHandler(tc_employeeId);
        setEditCommitHandler(tc_comments);

        tableViewCSVData.setEditable(true);
    }


    private void setEditCommitHandler(TableColumn<CsvRow, String> column) {
        column.setCellFactory(TextFieldTableCell.forTableColumn());
        column.setOnEditCommit(event -> handleEditCommit(event, column));
        column.setEditable(true);
    }


    private void handleDeleteButton(CsvRow selectedRow) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete Row");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to delete this row?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            try {
                // Delete from the database
                DatabaseManager.deleteRowFromDatabase(selectedRow);

                // Delete from the CSV file
                uploadCsvFile.deleteRowFromCsv(selectedRow);

                // Remove the row from the TableView
                tableViewCSVData.getItems().remove(selectedRow);


            } catch (Exception e) {
                // Log the exception
                e.printStackTrace();
                // Handle the error appropriately (show an error message, etc.)
            }
        }
    }

    @FXML
    private void addRowButtonClicked(ActionEvent event) {
        // Get the current user from the class-level field
        Employee currentUser = userSession.getCurrentUser();

        // Check if the current user is null (handle it appropriately)
        if (currentUser == null) {
            // Handle the case where currentUser is null
            System.err.println("Error: currentUser is null in addRowButtonClicked");
            return;
        }

        // Create a new CsvRow with default values
        CsvRow newRow = new CsvRow("", "", "", "", "", "", "", "");

        // Set the last editor for the new row
        newRow.setLastEditor(currentUser.getUsername());
        System.out.println("addRowButtonClicked - lastEditor: " + currentUser.getUsername());

        // Add the new row to the TableView
        tableViewCSVData.getItems().add(newRow);

        // Scroll to and select the new row
        tableViewCSVData.scrollTo(newRow);
        tableViewCSVData.getSelectionModel().select(newRow);
        tableViewCSVData.edit(tableViewCSVData.getItems().indexOf(newRow), tableViewCSVData.getColumns().get(0));

        // Track the new row in the map
        editedRowsMap.put(newRow.getCompositeKey(), newRow);
    }




    private List<String[]> convertToStringCsv(ObservableList<CsvRow> csvRows) {
        List<String[]> stringCsvData = new ArrayList<>();
        for (CsvRow row : csvRows) {
            stringCsvData.add(new String[]{
                    row.getTotalWorkHours(),
                    row.getBreakTime(),
                    row.getExitHour(),
                    row.getStartHour(),
                    row.getDateTable(),
                    row.getEmployeeId(),
            });
        }
        return stringCsvData;
    }



    public void setCsvFilePath(String csvFilePath) {
        this.csvFilePath = csvFilePath;
    }



    // Load last saved data from the database into TableView
    @FXML
    private void loadLastSavedDataIntoTableview(ActionEvent actionEvent) {
        if (!uploadCsvFile.isTableViewLoaded()) {
            List<CsvRow> lastSavedData = DatabaseManager.loadLastSavedData("employeeshiftdata", CsvRow.class);
            if (lastSavedData.isEmpty()) {
                showAlert("No last saved data found in the database.");
            } else {
                String lastEditorUsername = String.valueOf(UserSession.getInstance().getCurrentUser());
                for (CsvRow row : lastSavedData) {
                    row.setLastEditor(lastEditorUsername);
                }

                // Check if the database is empty
                if (DatabaseManager.isDatabaseEmpty("employeeshiftdata")) {
                    saveAllDataToDatabase();
                } else {
                    // Load the data into the TableView
                    ObservableList<CsvRow> dataModels = FXCollections.observableArrayList(lastSavedData);
                    tableViewCSVData.setItems(dataModels);
                }
            }
        } else {
            showAlert("TableView is already loaded. Cannot load last saved data.");
        }
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }


    // Save button click event

    @FXML
    private void saveButtonClicked(ActionEvent event) {
        boolean editedRowsExist = !editedRowsMap.isEmpty();
        boolean externallyAddedRowsExist = uploadCsvFile.hasExternallyAddedRows();

        for (CsvRow row : tableViewCSVData.getItems()) {
            try {
                // Update the CSV file with the new data for existing rows
                if (!uploadCsvFile.isExternallyAddedRow(row)) {
                    uploadCsvFile.updateRowInCsv(row);
                }

                // Save or update the row in the database
                if (DatabaseManager.isDataExists(sessionFactory, "employeeshiftdata", row.getEmployeeId(), row.getDateTable())) {
                    // Existing row, update the database
                    updateRowInDatabase(row);
                } else {
                    // New row, insert into the database
                    // Set last editor and comments in the database only, not in the CSV file
                    row.setLastEditor(null);
                    row.setComments(null);

                    DatabaseManager.insertRowIntoDatabase(row);
                }
            } catch (IOException e) {
                e.printStackTrace();
                // Handle the exception as needed
            }
        }

        // Optionally, you may want to clear the map and handle other cleanup tasks
        editedRowsMap.clear();
        uploadCsvFile.clearExternallyAddedRows();

        showAlert("Changes saved to both CSV file and database.");
    }



    private void saveCommentToDatabase(CsvRow row) {
        // Ensure that you are working within a transactional context
        try (Session session = sessionFactory.openSession()) {
            // Begin a transaction
            Transaction transaction = null;

            try {
                transaction = session.beginTransaction();

                // Use the original ID when updating the comment
                String updateQuery = "UPDATE employeeshiftdata SET comments = :comments, last_editor = :lastEditor " +
                        "WHERE employee_id = :employeeId AND date = :dateTable";

                // Creating a query object
                Query<?> query = session.createNativeQuery(updateQuery);

                // Setting parameters for the query
                query.setParameter("comments", row.getComments());
                query.setParameter("lastEditor", row.getLastEditor());
                query.setParameter("employeeId", row.getEmployeeId());
                query.setParameter("dateTable", row.getDateTable());

                // Execute the update query
                query.executeUpdate();

                // Commit the transaction
                transaction.commit();

                // Update the CsvRow object with the saved values
                row.setLastEditor(query.getParameter("lastEditor").toString());
                row.setComments(query.getParameter("comments").toString());

            } catch (Exception e) {
                // If an exception occurs, rollback the transaction
                if (transaction != null && transaction.isActive()) {
                    transaction.rollback();
                }

                // Handle exceptions, print or log the error
                e.printStackTrace();
                // Handle the exception as needed
            }
        }
    }




    private void processEditedRowsAndExternallyAddedRows(boolean editedRowsExist, boolean externallyAddedRowsExist) {
        if (editedRowsExist || externallyAddedRowsExist) {
            Task<Void> databaseUpdateTask = createDatabaseUpdateTask();
            setDatabaseUpdateTaskCallbacks(databaseUpdateTask);
            new Thread(databaseUpdateTask).start();
        } else {
            showAlert("No changes made. Nothing to save.");
        }
    }

    private Task<Void> createDatabaseUpdateTask() {
        return new Task<>() {
            @Override
            protected Void call() throws Exception {
                try {
                    processEditedRows();
                    processExternallyAddedRows();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }
        };
    }


    private void processEditedRows() {
        for (CsvRow row : editedRowsMap.values()) {
            try {
                // Update the CSV file with the new data
                uploadCsvFile.updateRowInCsv(row);

                // If the row is not externally added, update it in the database
                if (!uploadCsvFile.isExternallyAddedRow(row)) {
                    updateRowInDatabase(row);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        editedRowsMap.clear(); // Clear the map after updating
    }



    private void updateRowInDatabase(CsvRow editedRow) {
        try (Session session = sessionFactory.openSession()) {
            Employee currentUser = UserSession.getInstance().getCurrentUser();

            if (currentUser == null) {
                handleCurrentUserNull();
                return;
            }

            Transaction transaction = null;

            try {
                transaction = session.beginTransaction();
                String updateQuery = "UPDATE employeeshiftdata SET " +
                        "total_work_hours = :totalWorkHours, " +
                        "break_time = :breakTime, " +
                        "end_of_shift = :exitHour, " +
                        "start_of_shift = :startHour, " +
                        "date = :dateTable, " +
                        "employee_id = :employeeId, " +
                        "comments = :comments, " +
                        "last_editor = :lastEditor " +
                        "WHERE employee_id = :employeeId AND date = :dateTable";

                Query<?> query = session.createNativeQuery(updateQuery);
                setQueryParameters(query, editedRow);

                // Set lastEditor parameter
                query.setParameter("lastEditor", currentUser.getUsername());

                // Execute the update query
                query.executeUpdate();

                // Commit the transaction
                transaction.commit();
            } catch (Exception e) {
                handleTransactionException(transaction, e);
            }
        }
    }




    private void handleCurrentUserNull() {
        // Handle the case where currentUser is null (perhaps log an error or throw an exception)
        System.err.println("Error: currentUser is null in updateRowInDatabase");
    }

    private void handleTransactionException(Transaction transaction, Exception e) {
        if (transaction != null && transaction.isActive()) {
            transaction.rollback();
        }
        e.printStackTrace();
    }

    private void setQueryParameters(Query<?> query, CsvRow row) {
        query.setParameter("totalWorkHours", row.getTotalWorkHours());
        query.setParameter("breakTime", row.getBreakTime());
        query.setParameter("exitHour", row.getExitHour());
        query.setParameter("startHour", row.getStartHour());
        query.setParameter("dateTable", row.getDateTable());
        query.setParameter("employeeId", row.getEmployeeId());
        query.setParameter("comments", row.getComments());
        query.setParameter("lastEditor", row.getLastEditor());
    }

    private void processExternallyAddedRows() {
        if (externallyAddedRowsExist) {
            List<CsvRow> externallyAddedRows = uploadCsvFile.getExternallyAddedRows();
            for (CsvRow addedRow : externallyAddedRows) {
                if (!DatabaseManager.isDataExists(sessionFactory, "employeeshiftdata", addedRow.getEmployeeId(), addedRow.getDateTable())) {
                    // New row, insert into the database
                    DatabaseManager.insertRowIntoDatabase(addedRow);
                    editedRows.add(addedRow);
                }
            }
        }
    }


    private boolean isValidEmployeeId(String employeeId) {
        return employeeId.matches("\\d{9}");
    }

    private boolean isValidDateFormat(String date) {
        try {
            // Use SimpleDateFormat to parse the date with the specified format
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            sdf.setLenient(false);
            sdf.parse(date);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    private boolean isValidComments(String comments) {
        return comments.length() >= 5;
    }

    private boolean isValidHourFormat(String hour) {
        try {
            // Use SimpleDateFormat to parse the time with the specified format
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
            sdf.setLenient(false);
            sdf.parse(hour);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    // Handle edit commit for each column
    @FXML
    private void handleEditCommit(TableColumn.CellEditEvent<CsvRow, String> event, TableColumn<CsvRow, String> column) {
        CsvRow editedRow = event.getRowValue();
        String newValue = event.getNewValue();

        // Validate employeeId and date format for new rows
        if (event.getSource().equals(tc_employeeId)) {
            if (!isValidEmployeeId(newValue)) {
                showAlert("ת.ז (Employee ID) must have 9 numbers.");
                return;
            }
        }
        if (event.getSource().equals(tc_dateTable)) {
            if (!isValidDateFormat(newValue)) {
                showAlert("Invalid date format. Please use dd/MM/yyyy.");
                return;
            }
        }
        if (event.getSource().equals(tc_comments)) {
            if (!isValidComments(newValue)) {
                showAlert("Comments must be at least 5 characters long.");
                return;
            }
        }
        if (event.getSource().equals(tc_enterHour) || event.getSource().equals(tc_exitHour)) {
            if (!isValidHourFormat(newValue)) {
                showAlert("Invalid hour format. Please use HH:mm.");
                return;
            }
        }

        // Update the specific property based on the edited column
        updatePropertyBasedOnColumn(editedRow, event.getTableColumn(), newValue);

        // Set last editor to the current user
        Employee currentUser = UserSession.getInstance().getCurrentUser();
        if (currentUser != null) {
            editedRow.setLastEditor(currentUser.getUsername());
        } else {
            // Handle the case where currentUser is null
            System.err.println("Error: currentUser is null in handleEditCommit");
        }

        // Save changes to the CSV file and the database
        try {
            uploadCsvFile.updateRowInCsv(editedRow);
            updateRowInDatabase(editedRow);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }




    private void updatePropertyBasedOnColumn(CsvRow editedRow, TableColumn<CsvRow, String> editedColumn, String newValue) {
        // Update the specific property based on the edited column
        if (editedColumn.equals(tc_comments)) {
            editedRow.setComments(newValue);
        } else if (editedColumn.equals(tc_employeeId)) {
            editedRow.setEmployeeId(newValue);
        } else if (editedColumn.equals(tc_dateTable)) {
            editedRow.setDateTable(newValue);
        } else if (editedColumn.equals(tc_exitHour)) {
            editedRow.setExitHour(newValue);
        } else if (editedColumn.equals(tc_enterHour)) {
            editedRow.setStartHour(newValue);
        } else if (editedColumn.equals(tc_breakTime)) {
            editedRow.setBreakTime(newValue);
        } else if (editedColumn.equals(tc_totalWorkHrs)) {
            editedRow.setTotalWorkHours(newValue);
        }
    }

    private void saveEmployeeIdToDatabase(CsvRow editedRow) {
        savePropertyToDatabase("employee_id", editedRow.getEmployeeId(), editedRow);
    }

    private void saveDateTableToDatabase(CsvRow editedRow) {
        savePropertyToDatabase("date", editedRow.getDateTable(), editedRow);
    }

    private void saveExitHourToDatabase(CsvRow editedRow) {
        savePropertyToDatabase("end_of_shift", editedRow.getExitHour(), editedRow);
    }

    private void saveEnterHourToDatabase(CsvRow editedRow) {
        savePropertyToDatabase("start_of_shift", editedRow.getStartHour(), editedRow);
    }

    private void saveBreakTimeToDatabase(CsvRow editedRow) {
        savePropertyToDatabase("break_time", editedRow.getBreakTime(), editedRow);
    }

    private void saveTotalWorkHoursToDatabase(CsvRow editedRow) {
        savePropertyToDatabase("total_work_hours", editedRow.getTotalWorkHours(), editedRow);
    }

    private void savePropertyToDatabase(String propertyName, String propertyValue, CsvRow editedRow) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = null;

            try {
                transaction = session.beginTransaction();

                // Construct the update query
                String updateQuery = String.format("UPDATE employeeshiftdata SET %s = :value, last_editor = :lastEditor WHERE employee_id = :employee_id", propertyName);

                Query<?> query = session.createNativeQuery(updateQuery);
                query.setParameter("value", propertyValue);
                query.setParameter("lastEditor", editedRow.getLastEditor());


                // Commit the transaction
                transaction.commit();
            } catch (Exception e) {
                if (transaction != null && transaction.isActive()) {
                    transaction.rollback();
                }
                e.printStackTrace();
            }
        }
    }

    private void saveChanges(CsvRow editedRow) {

        // Set last editor
        editedRow.setLastEditor(currentUser.getUsername());

        // Use the composite key for tracking
        editedRowsMap.put(editedRow.getCompositeKey(), editedRow);

        // Database operation
        DatabaseManager.insertRowIntoDatabase(editedRow);
        System.out.println("Changes saved for the row with composite key: " + editedRow.getCompositeKey());
    }


    private void setDatabaseUpdateTaskCallbacks(Task<Void> databaseUpdateTask) {
        databaseUpdateTask.setOnSucceeded(e -> {
            Platform.runLater(() -> {
                System.out.println("Database update task succeeded. Refreshing TableView.");
                tableViewCSVData.refresh();
                editedRows.clear();
                uploadCsvFile.clearExternallyAddedRows();
            });
        });

        databaseUpdateTask.setOnFailed(e -> {
            Throwable exception = databaseUpdateTask.getException();
            if (exception != null) {
                System.out.println("Database update task failed with exception: " + exception.getMessage());
                exception.printStackTrace();
            }
        });
    }

    // Save all data to the database
    private void saveAllDataToDatabase() {
        ObservableList<CsvRow> csvRows = tableViewCSVData.getItems();
        DatabaseManager.saveCSVDataToDatabase("employeeshiftdata", convertToStringCsv(csvRows));
    }


    @FXML
    void mainPageButtonClicked(ActionEvent event) throws IOException {
        navigateToManageEmployeePage(event);
    }

    @FXML
    private void searchButtonClicked(ActionEvent event) {
        String employeeId = tf_employeeID.getText().trim();

        if (!employeeId.isEmpty() && employeeId.matches("\\d+")) {
            loadLastSavedDataByEmployeeIdIntoTableview(employeeId);
        } else {
            showAlert("Invalid employee ID. Please enter a numeric value.");
        }
    }


    private void loadLastSavedDataByEmployeeIdIntoTableview(String employeeId) {
        List<CsvRow> data = DatabaseManager.loadLastSavedDataByEmployeeId("employeeshiftdata", CsvRow.class, employeeId);

        if (data.isEmpty()) {
            showAlert("No data found for the specified employee ID.");
        } else {
            ObservableList<CsvRow> dataModels = FXCollections.observableArrayList(data);
            tableViewCSVData.setItems(dataModels);
        }
    }


}