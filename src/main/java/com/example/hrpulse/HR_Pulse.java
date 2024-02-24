package com.example.hrpulse;

import com.example.hrpulse.Services.Hibernate.HibernateUtil;
import com.example.hrpulse.Services.Objects.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HR_Pulse extends Application {

    public static void main(String[] args) {
        initDatabase();
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HR_Pulse.class.getResource("login-view.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, 600, 500);
        stage.setResizable(false);
        stage.setTitle("Hr-Pulse");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.show();
    }

    // Initialize the database and perform Hibernate operations
    private static void initDatabase() {
        // Perform database initialization here
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            // Create a new Employee instance
            Employee newEmployee = new Employee();
            newEmployee.setFirstName("John");
            newEmployee.setLastName("Doe");

            // Save the employee to the database
            session.save(newEmployee);

            // Commit the transaction
            transaction.commit();

            // Retrieve the saved employee
            Employee retrievedEmployee = session.get(Employee.class, newEmployee.getId());
            if (retrievedEmployee != null) {
                System.out.println("Employee retrieved from the database:");
                System.out.println("ID: " + retrievedEmployee.getId());
                System.out.println("Name: " + retrievedEmployee.getFirstName() + " " + retrievedEmployee.getLastName());
            } else {
                System.out.println("Employee not found in the database.");
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }

        // Close the Hibernate session factory when done
        HibernateUtil.shutdown();
    }

    // Example method for performing database operations in response to user actions


    public static void performDatabaseOperations() {
        initDatabase(); // Call this method when you want to interact with the database

        // Print the employee again or perform other database operations here
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            // Retrieve the saved employee
            Employee retrievedEmployee = session.get(Employee.class, 1); // Replace '1' with the actual employee ID
            if (retrievedEmployee != null) {
                System.out.println("Employee retrieved from the database:");
                System.out.println("ID: " + retrievedEmployee.getId());
                System.out.println("Name: " + retrievedEmployee.getFirstName() + " " + retrievedEmployee.getLastName());
            } else {
                System.out.println("Employee not found in the database.");
            }

            // Commit the transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }

        // Close the Hibernate session factory when done
        HibernateUtil.shutdown();
    }

}
