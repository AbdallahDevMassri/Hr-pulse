package com.example.hrpulse.Services.Objects;
<<<<<<< HEAD
import javax.persistence.*;
import java.util.List;
@Entity
@Table(name = "departments") // Specify the actual database table name
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="department_id")
    private int departmentId;
    @Column(name = "department_name")
    private String departmentName;
    @Column(name = "description" )
    private String description;
    @OneToMany(mappedBy = "department", fetch = FetchType.EAGER)
    private List<Employee> employees;

    public Department() {
    }

=======
import java.util.List;
import javax.persistence.*;


@Entity
@Table(name = "Department")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "departmentId")
    private int departmentId;

<<<<<<<< HEAD:src/main/java/com/example/hrpulse/Service/Objects/Department.java
    @Column(name = "departmentName")
    private String departmentName;

    @OneToMany(mappedBy = "department") // Use mappedBy to define the reverse relationship in Employee
    private List<Employee> employees;
    public Department(int departmentId, String departmentName) {
        // Initialize department attributes here
        // Initialize the 'employees' list as an empty ArrayList
========
>>>>>>> 8452ff04666fa1a352d6dd4e284d1b1d09392159
    public Department(int departmentId, String departmentName, List<Employee> employees) {
        this.departmentId = departmentId;
        this.departmentName = departmentName;
        this.employees = employees;
<<<<<<< HEAD
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

=======
>>>>>>>> 8452ff04666fa1a352d6dd4e284d1b1d09392159:src/main/java/com/example/hrpulse/Services/Objects/Department.java
    }

    public void addEmployee(Employee employee) {
        // Add an employee to the department's 'employees' list
        // ...
    }

    public void removeEmployee(Employee employee) {
        // Remove an employee from the department's 'employees' list
        // ...
    }
>>>>>>> 8452ff04666fa1a352d6dd4e284d1b1d09392159

    public List<Employee> getEmployees() {
        // Return the list of employees in this department
        return employees;
    }

<<<<<<< HEAD
    @Override
    public String toString() {
        return "Department{" +
                "departmentId=" + departmentId +
                ", departmentName='" + departmentName + '\'' +
                ", description='" + description + '\'' +
                ", employees=" + employees +
                '}';
    }
}
=======
    public double calculateTotalDepartmentBudget() {
        // Calculate and return the total department budget (sum of employee salaries)
        // ...
        return 0;
    }
}
>>>>>>> 8452ff04666fa1a352d6dd4e284d1b1d09392159
