package com.example.hrpulse.Service.Objects;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;


@Entity
@Table(name = "Department")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "departmentId")
    private int departmentId;

    @Column(name = "departmentName")
    private String departmentName;

    @OneToMany(mappedBy = "department") // Use mappedBy to define the reverse relationship in Employee
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
