package com.example.hrpulse;

import com.example.hrpulse.Services.Database.DatabaseManager;
import com.example.hrpulse.Services.Database.DatabaseSessionManager;
import com.example.hrpulse.Services.Hibernate.HibernateUtil;
import com.example.hrpulse.Services.Objects.Department;
import com.example.hrpulse.Services.Objects.Employee;
import com.example.hrpulse.Controllers.EmployeeController.*;
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

public class HR_Pulse extends Application {
    private static SessionFactory sessionFactory;
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        // Initialize the DatabaseManager when the application starts
        sessionFactory = DatabaseManager.getSessionFactory();

        // Load the login view
        FXMLLoader fxmlLoader = new FXMLLoader(HR_Pulse.class.getResource("login-view.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, 600, 500);
        stage.setResizable(false);
        stage.setTitle("Hr-Pulse");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.show();

        List<Department> departments = retrieveDepartments();
        for (Department department : departments) {
            System.out.println("Department Name: " + department.getDepartmentName());
            System.out.println("Description: " + department.getDescription());
            // Add more attributes if needed
        }


        // Close the SessionFactory when the application exits
        stage.setOnCloseRequest(event -> {
            DatabaseManager.closeSessionFactory();
        });
    }

    // Method to perform database operations after form submission
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
    public static void performDatabaseOperations(Department department) {
        DatabaseSessionManager sessionManager = new DatabaseSessionManager(DatabaseManager.getSessionFactory());

        // Save the employee to the database
        boolean saved = sessionManager.saveDepartment(department);

        if (saved) {
            // Display confirmation
            System.out.println("Departments saved successfully.");
        } else {
            // Display error
            System.out.println("Error saving Department.");
        }

    }
    public static void performDatabaseOperations(Department department, boolean update) {
        DatabaseSessionManager sessionManager = new DatabaseSessionManager(DatabaseManager.getSessionFactory());

        if (update) {
            boolean updated = sessionManager.updateDepartment(department);

            if (updated) {
                // Display confirmation
                System.out.println("Department updated successfully.");
            } else {
                // Display error
                System.out.println("Error updating Department.");
            }
        } else {
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
    public static List<Employee> retrieveEmployees() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            Query<Employee> query = session.createQuery("from Employee", Employee.class);
            List<Employee> employees = query.list();

            session.getTransaction().commit();

            return employees;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


}
