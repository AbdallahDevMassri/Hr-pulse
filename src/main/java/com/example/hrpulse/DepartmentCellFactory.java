package com.example.hrpulse;
import com.example.hrpulse.Services.Objects.Department;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

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
                    // Customize the cell rendering here
                    String departmentDetails = "Department Name: " + department.getDepartmentName()
                            + "\nDescription: " + department.getDescription();
                    int employeeCount = department.getEmployees().size();
                    String cellText = departmentDetails + "\nEmployee Count: " + employeeCount;

                    setText(cellText);
                    setStyle("-fx-border-color: gray; -fx-border-width: 0 0 1 0;"); // Add a bottom border line
                }
            }
        };
    }
}
