package com.example.hrpulse.Controllers.EmployeeController;

import com.example.hrpulse.Services.CSV.CsvRow;
import com.example.hrpulse.Services.CSV.UploadCsvFile;
import com.example.hrpulse.Services.Interfaces.EmployeeNavigators;
import com.example.hrpulse.Services.Objects.Employee;
import com.example.hrpulse.Session.UserSession;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import java.util.*;

import static com.example.hrpulse.HR_Pulse.sessionFactory;

/**
 *
 * Controller class for Employee Shift Data.
 *
 */

public class EditEmployeeShiftController implements EmployeeNavigators {

    @FXML
    private TableColumn<CsvRow, String> tc_totalWorkHrs;

    @FXML
    private TableColumn<CsvRow, String> tc_breakTime;

    @FXML
    private TableColumn<CsvRow, String> tc_exitHour;

    @FXML
    private TableColumn<CsvRow, String> tc_enterHour;

    @FXML
    private TableColumn<CsvRow, String> tc_date;

    @FXML
    private TableColumn<CsvRow, String> tc_employeeId;

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

    private String csvFilePath;


    // Keep track of edited rows and their IDs
    private Map<String, CsvRow> editedRowsMap = new HashMap<>();

    UserSession userSession = UserSession.getInstance();


    /**
     * Initializes the EditEmployeeShiftController.
     */
    public EditEmployeeShiftController() {}

    /**
     * Handles the upload CSV file action.
     *
     * @param event The ActionEvent triggered by the upload button.
     */
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

        uploadCsvFile.upload();

        // Set the CSV file path in the controller
        setCsvFilePath(uploadCsvFile.getFilePath());
    }


    /**
     * Initializes the controller and sets up event handlers.
     */
    @FXML
    public void initialize() {

        // Set action for the last saved button
        loadLastSavedDataButton.setOnAction(this::loadLastSavedDataIntoTableview);

        // Initialize UploadCsvFile with TableView and data type
        uploadCsvFile = new UploadCsvFile(tableViewCSVData, "employeeshiftdata");

        // Setup column cell value factories and edit commit handlers

        tc_totalWorkHrs.setCellValueFactory(new PropertyValueFactory<>("totalWorkHours"));
        tc_totalWorkHrs.setCellFactory(TextFieldTableCell.forTableColumn());
        tc_totalWorkHrs.setOnEditCommit(event -> {
            CsvRow editedRow = event.getRowValue();
            editedRow.setTotalWorkHours(event.getNewValue());
            event.getRowValue().setTotalWorkHours(event.getNewValue());
            try {
                uploadCsvFile.updateRowInCsv(event.getRowValue());

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
            event.getRowValue().setBreakTime(event.getNewValue());
            try {
                uploadCsvFile.updateRowInCsv(event.getRowValue());

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
            event.getRowValue().setExitHour(event.getNewValue());
            try {
                uploadCsvFile.updateRowInCsv(event.getRowValue());

            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        });

        tc_enterHour.setCellValueFactory(new PropertyValueFactory<>("startHour"));
        tc_enterHour.setCellFactory(TextFieldTableCell.forTableColumn());
        tc_enterHour.setOnEditCommit(event -> {
            CsvRow editedRow = event.getRowValue();
            editedRow.setStartHour(event.getNewValue());
            event.getRowValue().setStartHour(event.getNewValue());
            try {
                uploadCsvFile.updateRowInCsv(event.getRowValue());

            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        });

        tc_date.setCellValueFactory(new PropertyValueFactory<>("date"));
        tc_date.setCellFactory(TextFieldTableCell.forTableColumn());
        tc_date.setOnEditCommit(event -> {
            CsvRow editedRow = event.getRowValue();
            editedRow.setDate(event.getNewValue());
            try {
                uploadCsvFile.updateRowInCsv(event.getRowValue());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        });

        tc_employeeId.setCellValueFactory(new PropertyValueFactory<>("employeeId"));
        tc_employeeId.setCellFactory(TextFieldTableCell.forTableColumn());
        tc_employeeId.setOnEditCommit(event -> {
            CsvRow editedRow = event.getRowValue();
            editedRow.setEmployeeId(event.getNewValue());
            event.getRowValue().setEmployeeId(event.getNewValue());
            try {
                uploadCsvFile.updateRowInCsv(event.getRowValue());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        });

        // Setup delete column with a delete button
        deleteColumn.setCellFactory(param -> new TableCell<>() {
            private final Button deleteButton = new Button("מחיקה");

            {
                // Set action for the delete button
                deleteButton.setOnAction(event -> {
                    CsvRow selectedRow = getTableView().getItems().get(getIndex());
                    handleDeleteButton(selectedRow);
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                // Display the delete button if the row is not empty
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(deleteButton);
                }
            }
        });

        // Add the delete column to the TableView
        tableViewCSVData.getColumns().add(deleteColumn);

        // Set edit commit handlers for each column
        setEditCommitHandler(tc_breakTime);
        setEditCommitHandler(tc_exitHour);
        setEditCommitHandler(tc_enterHour);
        setEditCommitHandler(tc_date);
        setEditCommitHandler(tc_employeeId);

        // Enable TableView editing
        tableViewCSVData.setEditable(true);
    }

    /**
     * Sets up the edit commit handler for a TableColumn.
     *
     * @param column The TableColumn for which the edit commit handler is set up.
     */
    private void setEditCommitHandler(TableColumn<CsvRow, String> column) {
        column.setCellFactory(TextFieldTableCell.forTableColumn());
        column.setOnEditCommit(event -> handleEditCommit(event, column));
        column.setEditable(true);
    }

    /**
     * Handles the delete button action for a selected row.
     *
     * @param selectedRow The CsvRow selected for deletion.
     */
    private void handleDeleteButton(CsvRow selectedRow) {
        // Display a confirmation dialog before deleting the row
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("מחק שורה");
        alert.setHeaderText(null);
        alert.setContentText("האם אתה בטוח שברצונך למחוק שורה זו?");

        // Process the user's choice from the confirmation dialog
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            try {
                // Remove the row from CSV and database, then update TableView
                uploadCsvFile.removeRowFromCsvAndDatabase(selectedRow);
                tableViewCSVData.getItems().remove(selectedRow);
                tableViewCSVData.getSelectionModel().clearSelection();
                showAlert("השורה ננמחקה בהצלחה.");

            } catch (Exception e) {
                e.printStackTrace();
                showAlert("שגיאה בניסיון למחיקת שורה");
            }
        }
    }

    /**
     * Handles the action when the add row button is clicked.
     *
     * @param event The ActionEvent triggered by the add row button.
     */
    @FXML
    private void addRowButtonClicked(ActionEvent event) {
        // Create a new CsvRow with default values
        CsvRow newRow = new CsvRow("", "", "", "", "", "");

        // Check for duplicate in the TableView
        if (isDuplicateRowInTableView(newRow)) {
            showAlert("התאריך הזה כבר קיים, אנא שנה תאריך או מחק ישן והכנס שורה חדשה");
            return;
        }

        // Mark the new row as externally added
        uploadCsvFile.markExternallyAddedRow(newRow);

        // Add the new row to the TableView
        tableViewCSVData.getItems().add(newRow);

        // Scroll to and select the new row
        tableViewCSVData.scrollTo(newRow);
        tableViewCSVData.getSelectionModel().select(newRow);
        tableViewCSVData.edit(tableViewCSVData.getItems().indexOf(newRow), tableViewCSVData.getColumns().get(0));

        // Track the new row in the map
        editedRowsMap.put(newRow.getCompositeKey(), newRow);
    }

    /**
     * Converts ObservableList of CsvRow to a List of String arrays for CSV processing.
     *
     * @param csvRows The ObservableList of CsvRow to be converted.
     * @return List of String arrays representing the CSV data.
     */
    private List<String[]> convertToStringCsv(ObservableList<CsvRow> csvRows) {
        List<String[]> stringCsvData = new ArrayList<>();
        for (CsvRow row : csvRows) {
            stringCsvData.add(new String[]{
                    row.getTotalWorkHours(),
                    row.getBreakTime(),
                    row.getExitHour(),
                    row.getStartHour(),
                    row.getDate(),
                    row.getEmployeeId(),
            });
        }
        return stringCsvData;
    }

    /**
     * Sets the CSV file path in the controller.
     *
     * @param csvFilePath The path to the CSV file.
     */
    public void setCsvFilePath(String csvFilePath) {
        this.csvFilePath = csvFilePath;
    }

    /**
     * Loads the last saved data from the database into the TableView.
     *
     * @param actionEvent The ActionEvent triggered by the load last saved data button.
     */
    @FXML
    private void loadLastSavedDataIntoTableview(ActionEvent actionEvent) {
        if (!uploadCsvFile.isTableViewLoaded()) {
            // Load last saved data from the database
            List<CsvRow> lastSavedData = DatabaseManager.loadLastSavedData("employeeshiftdata", CsvRow.class);
            if (lastSavedData.isEmpty()) {
                showAlert("לא נמצאו נתונים אחרונים להצגה מהמסד נתונים");
            } else {
                // Check if the database is empty and save all data to it
                if (DatabaseManager.isDatabaseEmpty("employeeshiftdata")) {
                    saveAllDataToDatabase();
                } else {
                    // Load the data into the TableView
                    ObservableList<CsvRow> dataModels = FXCollections.observableArrayList(lastSavedData);
                    tableViewCSVData.setItems(dataModels);
                }
            }
        } else {
            showAlert("לא ניתן להציג נתונים אחרונים כי הטבלה כבר בשימוש");
        }
    }

    /**
     * Displays an information alert with the given message.
     *
     * @param message The message to be displayed in the alert.
     */
    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }


    /**
     * Handles the save button click event.
     *
     * @param event The ActionEvent triggered by the save button.
     */
    @FXML
    private void saveButtonClicked(ActionEvent event) {
        try {
            boolean allExternallyAddedRowsValid = true;

            // Check if externally added rows exist
            if (DatabaseManager.isDatabaseEmpty("employeeshiftdata")) {
                saveAllDataToDatabase();
            } else if (!uploadCsvFile.isExternallyAddedRowsEmpty()) {
                for (CsvRow row : uploadCsvFile.getExternallyAddedRows()) {
                    // Check validity and duplicates for externally added rows
                    if (!isValidRow(row) || isDuplicateRow(row)) {
                        allExternallyAddedRowsValid = false;
                        showAlert("נתונים שגויים או שיש כפילות בנתונים, אנא בדוק ושנה בהתאם");
                        break;
                    }
                }

                if (allExternallyAddedRowsValid) {
                    // Clear externally added rows and edited rows map
                    uploadCsvFile.clearExternallyAddedRows();
                    editedRowsMap.clear();
                } else {
                    // If there are invalid rows, do not proceed with saving
                    return;
                }
            }

            // Save all data to CSV file
            uploadCsvFile.writeDataToCsv(tableViewCSVData.getItems());

            // Save all data to Database
            DatabaseManager.saveCSVDataToDatabase("employeeshiftdata", convertToStringCsv(tableViewCSVData.getItems()));

            showAlert("הנתונים נשמרו במסד הנתונים ובקובץ החיצוני בהצלחה.");

        } catch (Exception e) {
            e.printStackTrace();
            showAlert("נוצרה שגיאה במהלך שמירת הנתונים, נסה שנית");
        }
    }

    /**
     * Checks if a given CsvRow is a duplicate in the database.
     *
     * @param newRow The CsvRow to check for duplication.
     * @return True if the row is a duplicate, false otherwise.
     */
    private boolean isDuplicateRow(CsvRow newRow) {
        // Check if the combination of employee ID and date already exists in the database
        return DatabaseManager.isDataExists(sessionFactory, "employeeshiftdata", newRow.getEmployeeId(), newRow.getDate());
    }

    /**
     * Retrieves a formatted message indicating the invalid fields in a CsvRow.
     *
     * @param row The CsvRow to check for invalid fields.
     * @return A formatted message indicating the invalid fields.
     */
    private String getInvalidMessage(CsvRow row) {
        // Check if all the required fields are not empty
        StringBuilder invalidMessage = new StringBuilder("נא למלא את העמודות הבאות:\n");

        if (row.getTotalWorkHours().isEmpty()) {
            invalidMessage.append("- סהכ שעות\n");
        }
        if (row.getExitHour().isEmpty()) {
            invalidMessage.append("- שעת יציאה\n");
        }
        if (row.getStartHour().isEmpty()) {
            invalidMessage.append("- שעת כניסה\n");
        }
        if (row.getDate().isEmpty()) {
            invalidMessage.append("- תאריך\n");
        }
        if (row.getEmployeeId().isEmpty()) {
            invalidMessage.append("- ת.ז\n");
        }

        return invalidMessage.toString();
    }

    /**
     * Checks if a CsvRow is valid by ensuring all required fields are not empty and the employee ID exists.
     *
     * @param row The CsvRow to check for validity.
     * @return True if the row is valid, false otherwise.
     */
    private boolean isValidRow(CsvRow row) {
        // Check if all the required fields are not empty
        boolean isValid = !row.getTotalWorkHours().isEmpty() &&
                !row.getExitHour().isEmpty() &&
                !row.getStartHour().isEmpty() &&
                !row.getEmployeeId().isEmpty();

        if (!isValid) {
            showAlert(getInvalidMessage(row));
            return false;
        }

        // Check if the employee ID exists in the employee table
        if (!employeeExists(row.getEmployeeId())) {
            showAlert( "עובד עם ת.ז הנ'ל: " +  row.getEmployeeId() + " לא נמצא במערכת, אנא מחק שורה ונסה שנית ");
            return false;
        }

        return true;
    }

    /**
     * Checks if an employee with the given ID exists in the employee table.
     *
     * @param employeeId The ID of the employee to check for existence.
     * @return True if the employee exists, false otherwise.
     */
    private boolean employeeExists(String employeeId) {
        // Check if the employee ID exists in the employee table
        try (Session session = sessionFactory.openSession()) {
            Query<Employee> query = session.createQuery("FROM Employee WHERE employee_id = :employeeId", Employee.class);
            query.setParameter("employeeId", employeeId);
            return query.uniqueResult() != null;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    /**
     * Updates the specified CsvRow in the database with the edited values.
     *
     * @param editedRow The CsvRow with the edited values.
     */
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
                        "employee_id = :employeeId " +
                        "WHERE composite_key = :compositeKey";

                Query<?> query = session.createNativeQuery(updateQuery);
                setQueryParameters(query, editedRow);

                // Commit the transaction
                transaction.commit();
            } catch (Exception e) {
                handleTransactionException(transaction, e);
            }
        }
    }

    /**
     * Handles the case where the current user is null during an update operation.
     * Prints an error message to the console.
     */
    private void handleCurrentUserNull() {
        // Handle the case where currentUser is null (perhaps log an error or throw an exception)
        System.err.println("Error: currentUser is null in updateRowInDatabase");
    }

    /**
     * Handles exceptions that occur during a database transaction, including rolling back the transaction if needed.
     *
     * @param transaction The transaction being executed.
     * @param e           The exception that occurred.
     */
    private void handleTransactionException(Transaction transaction, Exception e) {
        if (transaction != null && transaction.isActive()) {
            transaction.rollback();
        }
        e.printStackTrace();
    }

    /**
     * Sets the parameters of the given database query using the values from the CsvRow.
     *
     * @param query The database query.
     * @param row   The CsvRow containing the values.
     */
    private void setQueryParameters(Query<?> query, CsvRow row) {
        query.setParameter("totalWorkHours", row.getTotalWorkHours());
        query.setParameter("breakTime", row.getBreakTime());
        query.setParameter("exitHour", row.getExitHour());
        query.setParameter("startHour", row.getStartHour());
        query.setParameter("dateTable", row.getDate());
        query.setParameter("employeeId", row.getEmployeeId());
    }

    /**
     * Handles the commit of an edit event on a TableView cell.
     * Validates the new value, rolls back the edit if needed, and updates the CSV file and database.
     *
     * @param event  The CellEditEvent triggered by the edit.
     * @param column The TableColumn being edited.
     */
    private void handleEditCommit(TableColumn.CellEditEvent<CsvRow, String> event, TableColumn<CsvRow, String> column) {
        CsvRow editedRow = event.getRowValue();
        String newValue = event.getNewValue();

        // Store the old value before it's changed
        String oldValue = event.getOldValue();

        // Validate date and hours formats only for specific columns
        if (column.equals(tc_date) && !isValidDateFormat(newValue)) {
            // Rollback the edit if validation fails for date
            tableViewCSVData.getItems().set(event.getTablePosition().getRow(), editedRow);
            return;
        }

        if ((column.equals(tc_enterHour) || column.equals(tc_exitHour)) && !isValidHoursFormat(newValue)) {
            // Rollback the edit if validation fails for start/end hours
            tableViewCSVData.getItems().set(event.getTablePosition().getRow(), editedRow);
            return;
        }

        // Update the specific property based on the edited column
        updatePropertyBasedOnColumn(editedRow, column, newValue);

        // Check for duplicate in the TableView
        if (isDuplicateRowInTableView(editedRow)) {
            showAlert("העובד הזה כבר רשום במערכת בתאריך הזה, אנא ערוך לתאריך אחר או מחק את התאריך הקודם כדי לעדכן תאריך/מידע זהה והמשך בפעולות.");

            // Rollback the edit to prevent adding a duplicate row
            tableViewCSVData.getItems().set(event.getTablePosition().getRow(), editedRow);
            return;
        }

        // Save changes to the CSV file and the database
        try {
            uploadCsvFile.updateRowInCsv(editedRow);
            updateRowInDatabase(editedRow);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Checks if a CsvRow is a duplicate in the TableView.
     *
     * @param editedRow The CsvRow being checked for duplication.
     * @return True if the row is a duplicate, false otherwise.
     */
    private boolean isDuplicateRowInTableView(CsvRow editedRow) {
        // Check if the combination of date and employee ID already exists in the TableView
        return tableViewCSVData.getItems().stream()
                .anyMatch(row -> row != editedRow && row.getDate().equals(editedRow.getDate())
                        && row.getEmployeeId().equals(editedRow.getEmployeeId()));
    }

    /**
     * Checks if the provided date string is in a valid date format (dd/mm/yyyy).
     *
     * @param date The date string to validate.
     * @return True if the date is in a valid format, false otherwise.
     */
    private boolean isValidDateFormat(String date) {
        // Define the expected date format
        String dateFormatPattern = "\\d{2}/\\d{2}/\\d{4}";

        // Check if the date matches the expected format
        if (!date.matches(dateFormatPattern)) {
            showAlert("dd/mm/yyyy :פורמט תאריך שגוי, אנא הכנס בפורמט הבא");
            return false;
        }

        return true;
    }

    /**
     * Checks if the provided hours string is in a valid hours format (h:mm).
     *
     * @param hours The hours string to validate.
     * @return True if the hours are in a valid format, false otherwise.
     */
    private boolean isValidHoursFormat(String hours) {
        // Define the expected hours format
        String hoursFormatPattern = "\\d{1,2}:\\d{2}";

        // Check if the hours match the expected format
        if (!hours.matches(hoursFormatPattern)) {
            showAlert(" פורמט שעות שגוי, אנא הכנס בפורמט הבא: h:mm");
            return false;
        }

        return true;
    }

    /**
     * Updates the specific property of a CsvRow based on the edited TableColumn and new value.
     *
     * @param editedRow    The CsvRow being updated.
     * @param editedColumn The TableColumn being edited.
     * @param newValue     The new value for the property.
     */
    private void updatePropertyBasedOnColumn(CsvRow editedRow, TableColumn<CsvRow, String> editedColumn, String newValue) {
        // Update the specific property based on the edited column
        if (editedColumn.equals(tc_employeeId)) {
            editedRow.setEmployeeId(newValue);
        } else if (editedColumn.equals(tc_date)) {
            editedRow.setDate(newValue);
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

    /**
     * Saves all data from the TableView to the database.
     */
    private void saveAllDataToDatabase() {
        ObservableList<CsvRow> csvRows = tableViewCSVData.getItems();
        DatabaseManager.saveCSVDataToDatabase("employeeshiftdata", convertToStringCsv(csvRows));
    }

    /**
     * Handles the button click event to navigate to the main employee management page.
     *
     * @param event The ActionEvent triggered by the button click.
     * @throws IOException If an I/O exception occurs during navigation.
     */
    @FXML
    void mainPageButtonClicked(ActionEvent event) throws IOException {
        navigateToManageEmployeePage(event);
    }

    /**
     * Handles the button click event to perform a search based on the entered employee ID.
     *
     * @param event The ActionEvent triggered by the button click.
     */
    @FXML
    private void searchButtonClicked(ActionEvent event) {
        String employeeId = tf_employeeID.getText().trim();

        if (!employeeId.isEmpty() && employeeId.matches("\\d+")) {
            loadLastSavedDataByEmployeeIdIntoTableview(employeeId);
        } else {
            showAlert("ת.ז עובד שגוי - אנא הכנס מספר בעל 9 ספרות");
        }
    }

    /**
     * Loads the last saved data from the database based on the provided employee ID into the TableView.
     *
     * @param employeeId The employee ID for which to load the data.
     */
    private void loadLastSavedDataByEmployeeIdIntoTableview(String employeeId) {
        List<CsvRow> data = DatabaseManager.loadLastSavedDataByEmployeeId("employeeshiftdata", CsvRow.class, employeeId);

        if (data.isEmpty()) {
            showAlert("לא נמצאו נתונים לעובד הנ'ל.");
        } else {
            ObservableList<CsvRow> dataModels = FXCollections.observableArrayList(data);
            tableViewCSVData.setItems(dataModels);
        }
    }
}