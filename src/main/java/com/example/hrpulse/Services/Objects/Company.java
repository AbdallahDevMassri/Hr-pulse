<<<<<<< HEAD
package com.example.hrpulse.Services.Objects;
import java.util.ArrayList;
import java.util.List;

public class Company {
    private static  Company myCompany;
    private List<Department> departments= new ArrayList<>();
    private List<Employee>employees=new ArrayList<>();
    private Company() {
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
=======
<<<<<<<< HEAD:src/main/java/com/example/hrpulse/Service/Objects/Company.java
package com.example.hrpulse.Service.Objects;
========
package com.example.hrpulse.Services.Objects;
>>>>>>>> 8452ff04666fa1a352d6dd4e284d1b1d09392159:src/main/java/com/example/hrpulse/Services/Objects/Company.java
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
>>>>>>> 8452ff04666fa1a352d6dd4e284d1b1d09392159
    }
}
