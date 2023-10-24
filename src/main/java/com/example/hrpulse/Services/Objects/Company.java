package com.example.hrpulse.Services.Objects;
import java.util.ArrayList;
import java.util.List;

public class Company {
    private static  Company myCompany;
    private List<Department> departments= new ArrayList<>();
    private List<Employee>employees=new ArrayList<>();
    private Company() {
        // Any initialization code can go here
    }
public static Company getMyCompany(){
    if (myCompany == null) {
        // Initialize myCompany if it's null
        myCompany = new Company();
    }
    return myCompany;
}

    public void addDepartment(Department department) {
        departments.add(department);
    }
    public void addEmployee(Employee employee){
    employees.add(employee);
    }
    public Department getDepartmentByName(String departmentName) {
        for (Department department : departments) {
            if (department.getDepartmentName().equalsIgnoreCase(departmentName)) {
                return department;
            }
        }
        return null; // Department not found
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
