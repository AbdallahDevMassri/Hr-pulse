package com.example.hrpulse.Controllers.DepartmentController;

import com.example.hrpulse.HR_Pulse;
import com.example.hrpulse.Services.Interfaces.Navigators;
import com.example.hrpulse.Services.Objects.Company;
import com.example.hrpulse.Services.Objects.Department;
import com.example.hrpulse.Services.Objects.Employee;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import org.hibernate.SessionFactory;

import java.io.IOException;
import java.util.List;

import static com.example.hrpulse.HR_Pulse.retrieveDepartments;
import static com.example.hrpulse.HR_Pulse.retrieveEmployees;

public class ReportDepartmentController implements Navigators {

    private HR_Pulse hrPulse;
    private SessionFactory sessionFactory;

    public ReportDepartmentController() {
    }

    public ReportDepartmentController(HR_Pulse hrPulse) {
        this.hrPulse = hrPulse;
        this.sessionFactory = null;
    }

    public ReportDepartmentController(HR_Pulse hrPulse, SessionFactory sessionFactory) {
        this.hrPulse = hrPulse;
        this.sessionFactory = sessionFactory;
    }

    @FXML
    private ListView<Department> lv_departments;
    @FXML
    private Button viewDetailsButton;

    @FXML
    private Button countEmployeesButton;
    private ObservableList<Department> departmentList = FXCollections.observableArrayList();
    public void initialize() {
        List<Employee> employees = retrieveEmployees();// import the static method from HR_PULSE
        List<Department> departments = retrieveDepartments(); // import the static method from HR_PULSE
        departmentList.addAll(departments);
        lv_departments.setItems(departmentList);

        Company myCompany = Company.getMyCompany();
        if (myCompany != null) {
            for (Department department : departments) {
                myCompany.addDepartment(department);
                System.out.println(department);
            }
            for (Employee employee: employees
                 ) {
                myCompany.addEmployee(employee);
                System.out.println(employee);
                String departmentName = employee.getDepartment();
                Department matchingDepartment = myCompany.getDepartmentByName(departmentName);
                if (matchingDepartment != null) {
                    // If a matching department is found, add the employee to it
                    matchingDepartment.addEmployee(employee);
                } else {
                    System.err.println("No matching department found for employee: " + employee.getFirstName());
                }
            }
        } else {
            System.err.println("Company is not properly initialized.");
        }
    }



    @FXML
    void backToManageDepartment(ActionEvent event) throws IOException {
        navigateToManageDepartment(event);

    }


}
