package com.example.hrpulse.Services.Objects;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "employee_id")
    private int employeeId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "password")
    private String password;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column(name = "email")
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

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
    private double hourlyRate;

    @Column(name = "employment_start_date")
    private Date employmentStartDate;

    @Column(name = "hours_worked")
    private int hoursWorked;

    @Column(name = "isHourly")
    private boolean isHourly;

    @Column(name = "salaryToTravel")
    private double salaryToTravel;

    @Column(name = "salaryPerMonth")
    private double salaryPerMonth;

    @Column(name = "timestamp")
    private LocalDateTime timestamp;


    public Employee() {
        this.bankInfo = new BankInfo();
    }
    public Employee(String firstName,String email,String phoneNumber,String password){
        this.firstName=firstName;
        this.email=email;
        this.phoneNumber=phoneNumber;
        this.password=password;
    }

    public boolean isHourly() {
        return isHourly;
    }

    public void setHourly(boolean hourly) {
        isHourly = hourly;
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

    public double getSalaryPerMonth() {
        return salaryPerMonth;
    }

    public void setSalaryPerMonth(double salaryPerMonth) {
        this.salaryPerMonth = salaryPerMonth;
    }

    public double computeSalary() {
        // Calculate and return the employee's salary based on hourly rate and hours worked
        // ...
        return 0;
    }

    public void clockIn() {
        // Update the timestamp field with the current date and time
        this.timestamp = LocalDateTime.now();
    }

    public void clockOut() {
        // Update the timestamp field with the current date and time
        this.timestamp = LocalDateTime.now();
    }



    public void updateEmployeeDetails() {
        // Update employee details here
        // ...
    }
}