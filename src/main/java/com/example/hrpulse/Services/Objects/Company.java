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
}
