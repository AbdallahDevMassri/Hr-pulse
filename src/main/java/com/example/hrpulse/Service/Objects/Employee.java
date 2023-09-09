package com.example.hrpulse.Service.Objects;
import java.util.Date;

public class Employee {
    private int id;
    private int employeeId;
    private String firstName;
    private String lastName;
    private String password;
    private Date dateOfBirth;
    private String email;
    private String phoneNumber;
    private String employeeRole;
    private String department;
    private BankInfo bankInfo; // Add a BankInfo object to store bank details
    private double hourlyRate; // Hourly rate for salary calculation
    private Date employmentStartDate;
    private int hoursWorked; // Hours worked by the employee

    public Employee() {
    }
    public Employee(String firstName,String email,String phoneNumber,String password){
        this.firstName=firstName;
        this.email=email;
        this.phoneNumber=phoneNumber;
        this.password=password;
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

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
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

    public double computeSalary() {
        // Calculate and return the employee's salary based on hourly rate and hours worked
        // ...
        return 0;
    }

    public boolean isActiveEmployee() {
        // Check if the employee is considered active based on specific criteria
        // For example, you can check if they have worked in the last month, etc.
        // ...
        return false;
    }

    public void updateEmployeeDetails() {
        // Update employee details here
        // ...
    }
}