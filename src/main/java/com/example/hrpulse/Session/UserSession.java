package com.example.hrpulse.Session;

import com.example.hrpulse.Services.Objects.Employee;

/**
 * The `UserSession` class manages the user session and stores information about the current user.
 */
public class UserSession {

    // Singleton instance
    private static UserSession instance;

    // Current user information
    private Employee currentUser;

    // Private constructor to prevent instantiation
    private UserSession() {
        // Private constructor to prevent instantiation
    }

    /**
     * Gets the singleton instance of UserSession.
     *
     * @return The singleton instance of UserSession.
     */
    public static UserSession getInstance() {
        if (instance == null) {
            instance = new UserSession();
        }
        return instance;
    }

    /**
     * Sets the current user for the session.
     *
     * @param user The Employee representing the current user.
     */
    public void setCurrentUser(Employee user) {
        currentUser = user;
    }

    /**
     * Gets the current user for the session.
     *
     * @return The Employee representing the current user.
     */
    public Employee getCurrentUser() {
        return currentUser;
    }
}
