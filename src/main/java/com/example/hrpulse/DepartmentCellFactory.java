package com.example.hrpulse;

import com.example.hrpulse.Services.Objects.Department;
import com.example.hrpulse.Services.Objects.Employee;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.util.Callback;

/**
 * The `DepartmentCellFactory` class is a custom cell factory for rendering Department objects in a ListView.
 */
public class DepartmentCellFactory implements Callback<ListView<Department>, ListCell<Department>> {

    @Override
    public ListCell<Department> call(ListView<Department> param) {
        return new ListCell<Department>() {

            @Override
            protected void updateItem(Department department, boolean empty) {
                super.updateItem(department, empty);

                if (department == null || empty) {
                    setText(null);
                } else {
                    // Customize the cell rendering

                    // Label for department name
                    Label departmentLabel = new Label("  שם מחלקה " + department.getDepartmentName());
                    departmentLabel.setAlignment(Pos.CENTER_RIGHT);

                    // Label for department description
                    Label descriptionLabel = new Label(" תיאור מחלקה " + department.getDescription());
                    descriptionLabel.setAlignment(Pos.CENTER_RIGHT);

                    // Label for employee count
                    int employeeCount = department.getEmployees().size();
                    Label countLabel = new Label(" כמות העובדים " + employeeCount);
                    countLabel.setAlignment(Pos.CENTER_RIGHT);

                    // Button to show employees
                    Button showEmployeesButton = new Button(" הצגת העובדים ");
                    showEmployeesButton.setOnAction(event -> showEmployeesDialog(department));

                    // Create a horizontal layout for labels and the button
                    HBox hbox = new HBox(10);
                    hbox.getChildren().addAll(showEmployeesButton, countLabel, descriptionLabel, departmentLabel);
                    hbox.setAlignment(Pos.CENTER);

                    setGraphic(hbox);
                    setStyle("-fx-border-color: gray; -fx-border-width: 0 0 1 0;"); // Add a bottom borderline
                }
            }
        };
    }

    /**
     * Displays an information dialog with the list of employees in the given department.
     *
     * @param department The department for which to show the employees.
     */
    private void showEmployeesDialog(Department department) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("עובדים במחלקת: " + department.getDepartmentName());
        alert.setHeaderText("רשימת עובדים במחלקת: " + department.getDepartmentName());

        StringBuilder employeesInfo = new StringBuilder();
        for (Employee employee : department.getEmployees()) {
            employeesInfo.append(employee.getFirstName()).append(" ").append(employee.getLastName()).append("\n");
        }

        alert.setContentText(employeesInfo.toString());
        alert.showAndWait();
    }
}
