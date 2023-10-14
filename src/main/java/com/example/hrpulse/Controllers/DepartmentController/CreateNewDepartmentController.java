package com.example.hrpulse.Controllers.DepartmentController;

import com.example.hrpulse.HR_Pulse;
import com.example.hrpulse.Services.Interfaces.Navigators;
import com.example.hrpulse.Services.Objects.Department;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import org.hibernate.SessionFactory;

import java.io.IOException;
import java.util.List;

public class CreateNewDepartmentController implements Navigators {

    private HR_Pulse hrPulse;
    private SessionFactory sessionFactory;

    public CreateNewDepartmentController() {

    }

    public CreateNewDepartmentController(HR_Pulse hrPulse) {
        this.hrPulse = hrPulse;
        this.sessionFactory = null;
    }

    public CreateNewDepartmentController(HR_Pulse hrPulse, SessionFactory sessionFactory) {
        this.hrPulse = hrPulse;
        this.sessionFactory = sessionFactory;
    }

    @FXML
    private TextField tf_departmentName;

    @FXML
    private TextField tf_description;

    @FXML
    void backToManageDepartmentClick(ActionEvent event) throws IOException {
        navigateToManageDepartment(event);
    }

    @FXML
    void saveDepartmentClicked(ActionEvent event) {
        String departmentName = tf_departmentName.getText();
        String description = tf_description.getText();
        Department department = new Department();
        department.setDepartmentName(departmentName);
        department.setDescription(description);
        hrPulse.performDatabaseOperations(department);


        showConfirmationDialog("נתוני המחלקה התווספו בהצלחה .");

    }

    private void showConfirmationDialog(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Department Saved");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
