package com.example.hrpulse.Controllers.DepartmentController;

import com.example.hrpulse.Services.Database.DatabaseManager;
import com.example.hrpulse.Services.Interfaces.Navigators;
import com.example.hrpulse.Services.Objects.Department;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.hibernate.SessionFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.example.hrpulse.Services.Database.DatabaseManager.retrieveDepartments;

/**
 * The `EditDepartmentController` class handles the logic for editing and removing departments.
 */
public class EditDepartmentController implements Navigators {
    private DatabaseManager databaseManager;
    private SessionFactory sessionFactory;

    public EditDepartmentController() {
    }

    public EditDepartmentController(DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
        this.sessionFactory = null;
    }

    public EditDepartmentController(DatabaseManager databaseManager, SessionFactory sessionFactory) {
        this.databaseManager = databaseManager;
        this.sessionFactory = sessionFactory;
    }

    @FXML
    private Button btn_editButton;

    @FXML
    private Button btn_removeButton;

    @FXML
    private ChoiceBox<String> cb_departmentShow;

    @FXML
    private Label label_departmentDescription;

    @FXML
    private Label label_departmentName;

    @FXML
    private TextField tf_department;

    @FXML
    private TextField tf_departmentDescription;

    private List<Department> departments; // the list of departments

    /**
     * Initializes the controller.
     */
    @FXML
    public void initialize() {
        departments = retrieveDepartments();

        List<String> departmentNames = retrieveDepartmentNames(departments);
        cb_departmentShow.getItems().addAll(departmentNames);
        cb_departmentShow.setDisable(false);

        // Initialize the choice box selection listener
        cb_departmentShow.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                showFields();

                // Fetch the selected department by name
                Department selectedDepartment = retrieveDepartmentByName(newValue);

                if (selectedDepartment != null) {
                    tf_department.setText(selectedDepartment.getDepartmentName());
                    tf_departmentDescription.setText(selectedDepartment.getDescription());
                }
            } else {
                hideFields();
            }
        });

        hideFields();
    }

    // Define the retrieveDepartmentByName method
    private Department retrieveDepartmentByName(String departmentName) {
        for (Department department : departments) {
            if (department.getDepartmentName().equals(departmentName)) {
                return department;
            }
        }
        return null;
    }

    // Other methods for edit and remove actions can be implemented here

    private void showFields() {
        tf_department.setVisible(true);
        label_departmentName.setVisible(true);
        tf_departmentDescription.setVisible(true);
        label_departmentDescription.setVisible(true);
        btn_editButton.setVisible(true);
        btn_removeButton.setVisible(true);
    }

    private void hideFields() {
        tf_department.setVisible(false);
        label_departmentName.setVisible(false);
        tf_departmentDescription.setVisible(false);
        label_departmentDescription.setVisible(false);
        btn_editButton.setVisible(false);
        btn_removeButton.setVisible(false);
    }

    /**
     * Handles the back button click event, navigating back to the manage department page.
     */
    @FXML
    void backButtonClicked(ActionEvent event) throws IOException {
        navigateToManageDepartment(event);
    }

    /**
     * Handles the edit button click event, updating the selected department.
     */
    @FXML
    void editButtonClicked(ActionEvent event) {
        String selectedDepartmentName = cb_departmentShow.getValue();

        if (selectedDepartmentName != null) {
            Department selectedDepartment = retrieveDepartmentByName(selectedDepartmentName);

            if (selectedDepartment != null) {
                // Update the department entity with new data
                selectedDepartment.setDepartmentName(tf_department.getText());
                selectedDepartment.setDescription(tf_departmentDescription.getText());

                // Call the method in HR_Pulse to update the department
                com.example.hrpulse.Services.Database.DatabaseManager.performDatabaseOperations(selectedDepartment, true);

                // Display confirmation
                showConfirmationDialog("Updated successfully!", "המחלקה עודכנה בהצלחה !");
            } else {
                // Display error
                showErrorDialog("Error", "שגיאה בעידכון המחלקה !");
            }
        }
//        ReportDepartmentController.refreshDepartmentList();
    }

    /**
     * Handles the remove button click event, removing the selected department.
     */
    @FXML
    void removeButtonClicked(ActionEvent event) {
        String selectedDepartmentName = cb_departmentShow.getValue();

        if (selectedDepartmentName != null) {
            Department selectedDepartment = retrieveDepartmentByName(selectedDepartmentName);

            if (selectedDepartment != null) {
                // Call the method in HR_Pulse to remove the department
                com.example.hrpulse.Services.Database.DatabaseManager.performDatabaseOperations(selectedDepartment, false);

                // Display confirmation
                showConfirmationDialog("Removed successfully!", "המחלקה הוסרה בהצלחה !");
            } else {
                // Display error
                showErrorDialog("Error", "שגיאה במחיקת המחלקה !");
            }
        }
//        ReportDepartmentController.refreshDepartmentList();
    }

    // Define the retrieveDepartmentNames method
    public static List<String> retrieveDepartmentNames(List<Department> departments) {
        List<String> departmentNames = new ArrayList<>();

        for (Department department : departments) {
            departmentNames.add(department.getDepartmentName());
        }

        return departmentNames;
    }

    // Helper method to show a confirmation dialog
    private void showConfirmationDialog(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    // Helper method to show an error dialog
    private void showErrorDialog(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
