package com.example.hrpulse.Services.Objects;

<<<<<<< HEAD
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import javax.persistence.*;

@Entity
@Table(name = "employees")
=======
import java.util.Date;
import javax.persistence.*;
import java.util.Date;
import javax.persistence.*;
import java.util.Date;

@Entity
<<<<<<<< HEAD:src/main/java/com/example/hrpulse/Service/Objects/Employee.java
@Table(name = "Employee")
========
@Table(name = "employees")
>>>>>>>> 8452ff04666fa1a352d6dd4e284d1b1d09392159:src/main/java/com/example/hrpulse/Services/Objects/Employee.java
>>>>>>> 8452ff04666fa1a352d6dd4e284d1b1d09392159
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
<<<<<<< HEAD
    private Integer id;

    @Column(name = "employee_id")
    private Integer employeeId;
=======
    private int id;
<<<<<<<< HEAD:src/main/java/com/example/hrpulse/Service/Objects/Employee.java
========

    @Column(name = "employee_id")
    private int employeeId;
>>>>>>> 8452ff04666fa1a352d6dd4e284d1b1d09392159

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "password")
    private String password;

    @Column(name = "date_of_birth")
<<<<<<< HEAD
    private LocalDate dateOfBirth;
=======
    private Date dateOfBirth;
>>>>>>> 8452ff04666fa1a352d6dd4e284d1b1d09392159

    @Column(name = "email")
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;
<<<<<<< HEAD

=======
>>>>>>> 8452ff04666fa1a352d6dd4e284d1b1d09392159
    @Column(name = "gender")
    private String gender;

    @Column(name = "employee_role")
    private String employeeRole;

    @Column(name = "department")
    private String department;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "bank_info_id")
    private BankInfo bankInfo;

    @Column(name = "hourly_rate")
<<<<<<< HEAD
    private Double hourlyRate;
=======
    private double hourlyRate;
    @Column(name = "permonth")
    private boolean perMonth;
>>>>>>> 8452ff04666fa1a352d6dd4e284d1b1d09392159

    @Column(name = "employment_start_date")
    private Date employmentStartDate;

    @Column(name = "hours_worked")
<<<<<<< HEAD
    private Integer hoursWorked;

    @Column(name = "isHourly")
    private Boolean isHourly;

    @Column(name = "salaryToTravel")
    private Double salaryToTravel;

    @Column(name = "salaryPerMonth")
    private Double salaryPerMonth;


    @Transient
    private String username;


    public Employee() {
        this.bankInfo = new BankInfo();
    }
    public Employee(String firstName,String lastName, String email,String phoneNumber,String password,String username){
        this.firstName=firstName;
        this.lastName=lastName;
        this.email=email;
        this.phoneNumber=phoneNumber;
        this.password=password;
        this.username = username;
    }

    public Employee(String firstName, String email, String phoneNumber, String password) {

=======
    private int hoursWorked;
    @Column(name = "status")
    private boolean status;
    @Column(name = "salaryToTravel")
    private double salaryToTravel;

    public double getSalaryToTravel() {
        return salaryToTravel;
    }

    public void setSalaryToTravel(double salaryToTravel) {
        this.salaryToTravel = salaryToTravel;
    }
>>>>>>>> 8452ff04666fa1a352d6dd4e284d1b1d09392159:src/main/java/com/example/hrpulse/Services/Objects/Employee.java

    @Column(name = "employeeId")
    private int employeeId;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "password")
    private String password;

    @Column(name = "dateOfBirth")
    private Date dateOfBirth;

    @Column(name = "email")
    private String email;

    @Column(name = "phoneNumber")
    private String phoneNumber;

    @Column(name = "employeeRole")
    private String employeeRole;

    @Column(name = "department")
    private String department;

    @OneToOne(cascade = CascadeType.ALL) // Use CascadeType.ALL if you want to save BankInfo when saving Employee
    @JoinColumn(name = "bankInfoId") // Add a column for the BankInfo reference
    private BankInfo bankInfo;

    @Column(name = "hourlyRate")
    private double hourlyRate;

    @Column(name = "employmentStartDate")
    private Date employmentStartDate;

    @Column(name = "hoursWorked")
    private int hoursWorked;
    public Employee() {
    }
    public Employee(String firstName,String email,String phoneNumber,String password){
>>>>>>> 8452ff04666fa1a352d6dd4e284d1b1d09392159
        this.firstName=firstName;
        this.email=email;
        this.phoneNumber=phoneNumber;
        this.password=password;
<<<<<<< HEAD
        this.employeeRole = employeeRole;
    }

    public Employee(String firstName,String email,String phoneNumber,String password,String employeeRole){
        this.firstName=firstName;
        this.email=email;
        this.phoneNumber=phoneNumber;
        this.password=password;
        this.employeeRole =employeeRole;
    }


    public boolean isHourly() {
        return isHourly;
    }

    public void setHourly(boolean hourly) {
        isHourly = hourly;
=======
    }

    public boolean isPerMonth() {
        return perMonth;
    }

    public void setPerMonth(boolean perMonth) {
        this.perMonth = perMonth;
    }



    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
>>>>>>> 8452ff04666fa1a352d6dd4e284d1b1d09392159
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

<<<<<<< HEAD
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }
    public double getSalaryToTravel() {
        return salaryToTravel;
    }

    public void setSalaryToTravel(double salaryToTravel) {
        this.salaryToTravel = salaryToTravel;
    }
    public void setDateOfBirth(LocalDate dateOfBirth) {
=======
    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
>>>>>>> 8452ff04666fa1a352d6dd4e284d1b1d09392159
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmployeeRole() {
        return employeeRole;
    }

    public void setEmployeeRole(String employeeRole) {
        this.employeeRole = employeeRole;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public BankInfo getBankInfo() {
        return bankInfo;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setBankInfo(BankInfo bankInfo) {
        this.bankInfo = bankInfo;
    }

    public double getHourlyRate() {
        return hourlyRate;
    }

    public void setHourlyRate(double hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    public Date getEmploymentStartDate() {
        return employmentStartDate;
    }

    public void setEmploymentStartDate(Date employmentStartDate) {
        this.employmentStartDate = employmentStartDate;
    }

    public int getHoursWorked() {
        return hoursWorked;
    }

    public void setHoursWorked(int hoursWorked) {
        this.hoursWorked = hoursWorked;
    }

<<<<<<< HEAD
    public double getSalaryPerMonth() {
        return salaryPerMonth;
    }

    public void setSalaryPerMonth(double salaryPerMonth) {
        this.salaryPerMonth = salaryPerMonth;
=======
    public double computeSalary() {
        // Calculate and return the employee's salary based on hourly rate and hours worked
        // ...
        return 0;
>>>>>>> 8452ff04666fa1a352d6dd4e284d1b1d09392159
    }



<<<<<<< HEAD
=======
    public void updateEmployeeDetails() {
        // Update employee details here
        // ...
    }
>>>>>>> 8452ff04666fa1a352d6dd4e284d1b1d09392159
}