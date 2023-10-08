package com.example.hrpulse.Services.Objects;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;
@Entity
@Table(name = "departments") // Specify the actual database table name
public class Department {
    private int departmentId;
    private String departmentName;
    private List<Employee> employees;

    public Department(int departmentId, String departmentName, List<Employee> employees) {
        this.departmentId = departmentId;
        this.departmentName = departmentName;
        this.employees = employees;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
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
