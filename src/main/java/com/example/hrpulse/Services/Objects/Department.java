package com.example.hrpulse.Services.Objects;
import java.util.List;

public class Department {
    private int departmentId;
    private String departmentName;
    private List<Employee> employees;

    public Department(int departmentId, String departmentName) {
        // Initialize department attributes here
        // Initialize the 'employees' list as an empty ArrayList
    }

    public void addEmployee(Employee employee) {
        // Add an employee to the department's 'employees' list
        // ...
    }

    public void removeEmployee(Employee employee) {
        // Remove an employee from the department's 'employees' list
        // ...
    }

    public List<Employee> getEmployees() {
        // Return the list of employees in this department
        return employees;
    }

    public double calculateTotalDepartmentBudget() {
        // Calculate and return the total department budget (sum of employee salaries)
        // ...
        return 0;
    }
}
