package com.example.hrpulse.Service.Objects;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Employee {
    public static int employeeKey ;
    private String firstName;
    private String lastName;
    private String id;
    private String email;
    private String password;
    private String phoneNumber;
    private String userName;
    private boolean status;
    private String role;
    private String department;
    private int bankCode;
    private int bankSneefCode;
    private int acountNumber;
    private String bankName;


    public Employee() {
        employeeKey++;
    }

    public Employee(String firstName, String lastName, String id, String email, String phoneNumber,String password) {
        // Validation for firstName and lastName
        if (firstName == null || firstName.trim().isEmpty()) {
            throw new IllegalArgumentException("First name cannot be empty.");
        }
        if (lastName == null || lastName.trim().isEmpty()) {
            throw new IllegalArgumentException("Last name cannot be empty.");
        }

        // Validation for id (only alphanumeric characters)
        if (!id.matches("^[a-zA-Z0-9]+$")) {
            throw new IllegalArgumentException("ID must contain only alphanumeric characters.");
        }

        // Validation for email
        if (!isValidEmail(email)) {
            throw new IllegalArgumentException("Invalid email address.");
        }

        // You can add your own validation logic for phoneNumber here

        // If all validations pass, assign the values to instance variables
        this.firstName = firstName;
        this.lastName = lastName;
        this.id = id;
        this.email = email;
        this.phoneNumber = phoneNumber;

        this.password=password;
    }

    public Employee(String firstName, String email, String phone, String password1) {
        // Validation for firstName
        if (firstName == null || firstName.trim().isEmpty()) {
            throw new IllegalArgumentException("First name cannot be empty.");
        }

        // Validation for email
        if (!isValidEmail(email)) {
            throw new IllegalArgumentException("Invalid email address.");
        }

        // You can add your own validation logic for phone number here

        // Validation for password
        if (password1 == null || password1.trim().isEmpty()) {
            throw new IllegalArgumentException("Password cannot be empty.");
        }

        // If all validations pass, assign the values to instance variables
        this.firstName = firstName;
        this.email = email;
        this.phoneNumber = phone;
        this.password = password1;
    }

    // Helper method to validate email using regex
    private boolean isValidEmail(String email) {
        // A simple email validation using regex
        String emailRegex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static int getEmployeeKey() {
        return employeeKey;
    }

    public static void setEmployeeKey(int employeeKey) {
        Employee.employeeKey = employeeKey;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
