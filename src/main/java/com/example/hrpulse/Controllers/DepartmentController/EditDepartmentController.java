package com.example.hrpulse.Controllers.DepartmentController;

import com.example.hrpulse.HR_Pulse;
import com.example.hrpulse.Services.Interfaces.Navigators;
import com.example.hrpulse.Services.Objects.Department;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.hibernate.SessionFactory;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.example.hrpulse.HR_Pulse.retrieveDepartments;

public class EditDepartmentController implements Navigators {
    private HR_Pulse hrPulse;
    private SessionFactory sessionFactory;
    public EditDepartmentController(){}
    public EditDepartmentController(HR_Pulse hrPulse){
        this.hrPulse = hrPulse;
        this.sessionFactory =null;
    }
    public EditDepartmentController(HR_Pulse hrPulse, SessionFactory sessionFactory){
        this.hrPulse=hrPulse;
        this.sessionFactory=sessionFactory;
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

    private List<Department> departments; // Your list of departments

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

    @FXML
    void backButtonClicked(ActionEvent event) throws IOException {
        navigateToManageDepartment(event);
    }

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
                HR_Pulse.performDatabaseOperations(selectedDepartment, true);

                // Refresh the choice box
                departments = retrieveDepartments();
                List<String> departmentNames = retrieveDepartmentNames(departments);
                cb_departmentShow.getItems().clear();
                cb_departmentShow.getItems().addAll(departmentNames);

                // Display confirmation
                System.out.println("Department updated successfully.");
            } else {
                // Display error
                System.out.println("Error updating department.");
            }
        }
    }


    @FXML
    void removeButtonClicked(ActionEvent event) {
        String selectedDepartmentName = cb_departmentShow.getValue();

        if (selectedDepartmentName != null) {
            Department selectedDepartment = retrieveDepartmentByName(selectedDepartmentName);

            if (selectedDepartment != null) {
                // Call the method in HR_Pulse to remove the department
                HR_Pulse.performDatabaseOperations(selectedDepartment, false);

                // Refresh the choice box
                departments = retrieveDepartments();
                List<String> departmentNames = retrieveDepartmentNames(departments);
                cb_departmentShow.getItems().clear();
                cb_departmentShow.getItems().addAll(departmentNames);

                // Display confirmation
                System.out.println("Department removed successfully.");
            } else {
                // Display error
                System.out.println("Error removing department.");
            }
        }
    }
    // Define the retrieveDepartmentNames method
    public static List<String> retrieveDepartmentNames(List<Department> departments) {
        List<String> departmentNames = new ArrayList<>();

        for (Department department : departments) {
            departmentNames.add(department.getDepartmentName());
        }

        return departmentNames;
    }

}
