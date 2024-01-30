package com.example.hrpulse;

import com.example.hrpulse.Services.Database.DatabaseManager;
import com.example.hrpulse.Services.Database.DatabaseSessionManager;
import com.example.hrpulse.Services.Objects.Department;
import com.example.hrpulse.Services.Objects.Employee;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.io.IOException;
import java.util.List;

/**
 * The `HR_Pulse` class is the main entry point for the HR Pulse application.
 */
public class HR_Pulse extends Application {

    // Hibernate SessionFactory for database operations
    public static SessionFactory sessionFactory;

    // Main method to launch the JavaFX application
    public static void main(String[] args) {
        launch(args);
    }

    // Method called on application start
    @Override
    public void start(Stage stage) throws IOException {
        // Initialize the DatabaseManager when the application starts
        sessionFactory = DatabaseManager.getSessionFactory();

        // Load the login view
        FXMLLoader fxmlLoader = new FXMLLoader(HR_Pulse.class.getResource("login-view.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);

        // Set up the stage properties
        stage.setResizable(false);
        stage.setTitle("Hr-Pulse");
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();

        // Close the SessionFactory when the application exits
        stage.setOnCloseRequest(event -> {
            DatabaseManager.closeSessionFactory();
        });
    }

    // Method to perform database operations after form submission for Employee
    public static void performDatabaseOperations(Employee employee) {
        DatabaseSessionManager sessionManager = new DatabaseSessionManager(DatabaseManager.getSessionFactory());

        // Save the employee to the database
        boolean saved = sessionManager.saveEmployee(employee);

        if (saved) {
            // Display confirmation
            System.out.println("Employee saved successfully.");
        } else {
            // Display error
            System.out.println("Error saving employee.");
        }
    }

    // Method to retrieve a list of employees from the database
    public static List<Employee> retrieveEmployees() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            // Create an HQL query to select all employees
            Query<Employee> query = session.createQuery("from Employee", Employee.class);
            List<Employee> employees = query.list();

            // Commit the transaction
            session.getTransaction().commit();
            System.out.println("Retrieved " + employees.size() + " employees.");

            return employees;
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error retrieving employees: " + e.getMessage());
            return null;
        }
    }

    // Method to perform database operations after form submission for Department
    public static void performDatabaseOperations(Department department) {
        DatabaseSessionManager sessionManager = new DatabaseSessionManager(DatabaseManager.getSessionFactory());

        // Save the department to the database
        boolean saved = sessionManager.saveDepartment(department);

        if (saved) {
            // Display confirmation
            System.out.println("Departments saved successfully.");
        } else {
            // Display error
            System.out.println("Error saving Department.");
        }
    }

    // Method to perform database operations after form submission for Department (update or remove)
    public static void performDatabaseOperations(Department department, boolean update) {
        DatabaseSessionManager sessionManager = new DatabaseSessionManager(DatabaseManager.getSessionFactory());

        if (update) {
            // Update the department in the database
            boolean updated = sessionManager.updateDepartment(department);

            if (updated) {
                // Display confirmation
                System.out.println("Department updated successfully.");
            } else {
                // Display error
                System.out.println("Error updating Department.");
            }
        } else {
            // Remove the department from the database
            boolean removed = sessionManager.removeDepartment(department);

            if (removed) {
                // Display confirmation
                System.out.println("Department removed successfully.");
            } else {
                // Display error
                System.out.println("Error removing Department.");
            }
        }
    }

    // Method to retrieve a list of departments from the database
    public static List<Department> retrieveDepartments() {
        try (Session session = sessionFactory.openSession()) {
            // Begin a transaction
            session.beginTransaction();

            // Create an HQL query to select all departments
            Query<Department> query = session.createQuery("from Department", Department.class);

            // Execute the query and get the list of departments
            List<Department> departments = query.list();

            // Commit the transaction
            session.getTransaction().commit();

            return departments;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Method to perform database operations after form submission for Employee (update or remove)
    public static void employeePDO(Employee selectedEmployee, boolean update) {
        DatabaseSessionManager sessionManager = new DatabaseSessionManager(DatabaseManager.getSessionFactory());

        if (update) {
            // Update the employee in the database
            boolean updated = sessionManager.updateEmployee(selectedEmployee);

            if (updated) {
                // Display confirmation
                System.out.println("Employee updated successfully.");
            } else {
                // Display error
                System.out.println("Error updating employee.");
            }
        } else {
            // Remove the employee from the database
            boolean removed = sessionManager.removeEmployee(selectedEmployee);

            if (removed) {
                // Display confirmation
                System.out.println("Employee removed successfully.");
            } else {
                // Display error
                System.out.println("Error removing Employee.");
            }
        }
    }
}
