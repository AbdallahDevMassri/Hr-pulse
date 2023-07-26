package com.example.hrpulse.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Employee {
    public static int employeeKey ;
    private String firstName;
    private String lastName;
    // have 4 level for every employee  the employee with level 1 is the boss  and the last level is the employees
    // if you have level more up you can manage the worker that down to you

    private int level;
    private String id;
    private String email;
    private String password;
    private String phoneNumber;

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
       if(firstName=="Abdallah"){
           this.level=4;
       }else{
           this.level=1;
       }
        this.password=password;
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

    public int getLevel() {
        // Check if the new level is less than or equal to the employee's current level
        if (level <= this.level) {
            return this.level;
        } else {
            throw new IllegalArgumentException("You don't have the authority to set a level higher than your current level.");
        }
    }

    public void setLevel(int level) {
        // Check if the new level is less than or equal to the employee's current level
        if (level <= this.level) {
            this.level = level;
        } else {
            throw new IllegalArgumentException("You don't have the authority to set a level higher than your current level.");
        }
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
}
