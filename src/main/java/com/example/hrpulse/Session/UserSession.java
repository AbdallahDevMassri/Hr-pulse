package com.example.hrpulse.Session;

import com.example.hrpulse.Services.Objects.Employee;

public class UserSession {
    private static UserSession instance;
    private Employee currentUser;


    private UserSession() {
        // Private constructor to prevent instantiation
    }

    public static UserSession getInstance() {
        if (instance == null) {
            instance = new UserSession();
        }
        return instance;
    }

    public void setCurrentUser(Employee user) {
        currentUser = user;
    }

    public Employee getCurrentUser() {
        return currentUser;
    }



}

