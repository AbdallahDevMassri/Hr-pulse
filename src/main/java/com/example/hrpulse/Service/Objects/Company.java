package com.example.hrpulse.Service.Objects;
import java.util.List;
import javax.persistence.*;


@Entity
@Table(name = "Company")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "companyName")
    private String companyName;

    @OneToMany(mappedBy = "company") // Use mappedBy to define the reverse relationship in Department
    private List<Department> departments;

    public Company(String companyName) {
        // Initialize company attributes here
        // Initialize the 'departments' list as an empty ArrayList
    }

    public void addDepartment(Department department) {
        // Add a department to the company's 'departments' list
        // ...
    }

    public void removeDepartment(Department department) {
        // Remove a department from the company's 'departments' list
        // ...
    }

    public List<Employee> getAllEmployees() {
        // Return a list of all employees in the company (across all departments)
        // ...
        return null;
    }

    public double calculateTotalCompanyBudget() {
        // Calculate and return the total company budget (sum of all department budgets)
        // ...
        return 0;
    }

    public void generateReports() {
        // Generate and display reports (e.g., employee list, department-wise reports)
        // ...
    }
}
