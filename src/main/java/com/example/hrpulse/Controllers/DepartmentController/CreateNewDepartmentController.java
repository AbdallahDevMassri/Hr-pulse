package com.example.hrpulse.Controllers.DepartmentController;

import com.example.hrpulse.Services.Database.DatabaseManager;
import com.example.hrpulse.Services.Interfaces.Navigators;
import com.example.hrpulse.Services.Objects.Department;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import org.hibernate.SessionFactory;
import java.io.IOException;

/**
 * The `CreateNewDepartmentController` class handles the logic for creating a new department.
 */
public class CreateNewDepartmentController implements Navigators {

    // Constructors to allow for different ways of initializing the controller
    private DatabaseManager databaseManager;
    private SessionFactory sessionFactory;

    public CreateNewDepartmentController() {

    }

    public CreateNewDepartmentController(DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
        this.sessionFactory = null;
    }

    public CreateNewDepartmentController(DatabaseManager databaseManager, SessionFactory sessionFactory) {
        this.databaseManager = databaseManager;
        this.sessionFactory = sessionFactory;
    }

    @FXML
    private TextField tf_departmentName;

    @FXML
    private TextField tf_description;

    /**
     * Handles the back button click event, navigating back to the manage department page.
     */
    @FXML
    void backToManageDepartmentClick(ActionEvent event) throws IOException {
        navigateToManageDepartment(event);
    }

    /**
     * Handles the save button click event, saving the new department to the database.
     */
    @FXML
    void saveDepartmentClicked(ActionEvent event) {
        String departmentName = tf_departmentName.getText();
        String description = tf_description.getText();

        // Create a new Department instance with the entered details
        Department department = new Department();
        department.setDepartmentName(departmentName);
        department.setDescription(description);
         // Perform database operations to save the department
        databaseManager.performDatabaseOperations(department);
        // Show a confirmation dialog
        showConfirmationDialog("נתוני המחלקה התווספו בהצלחה .");
    }

    /**
     * Shows an information dialog with the given message.
     *
     * @param message The message to be displayed in the dialog.
     */
    private void showConfirmationDialog(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Department Saved");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
