package com.example.hrpulse.Controllers.EmployeeController;

import com.example.hrpulse.Services.Hibernate.HibernateUtil;
import com.example.hrpulse.Services.Interfaces.EmployeeNavigators;
import com.example.hrpulse.Services.Objects.Employee;
import com.example.hrpulse.HR_Pulse;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.io.IOException;

public class RemoveEmployeeController implements EmployeeNavigators {

    @FXML
    private TextField tf_employeeId;

    @FXML
    private Label lblResult;

    private  HR_Pulse hrPulse;
    private  SessionFactory sessionFactory;

    public void initialize() {
        this.sessionFactory = HibernateUtil.getSessionFactory();
    }

    public RemoveEmployeeController() {

    }

    public RemoveEmployeeController(HR_Pulse hrPulse, SessionFactory sessionFactory) {
        this.hrPulse = hrPulse;

        // Initialize the SessionFactory here
        this.sessionFactory = HibernateUtil.getSessionFactory();
    }


    @FXML
    void backToManageEmployee(ActionEvent event) throws IOException {
        navigateToManageEmployeePage(event);
    }

    @FXML
    void deleteEmployee(ActionEvent event) {
        // Get the employee ID from the text field
        String employeeIdText = tf_employeeId.getText().trim();

        // Check if the employee ID is empty
        if (employeeIdText.isEmpty()) {
            lblResult.setText("Please enter an Employee ID.");
            return;
        }

        // Parse the employee ID as an integer
        int employeeId;
        try {
            employeeId = Integer.parseInt(employeeIdText);
        } catch (NumberFormatException e) {
            lblResult.setText("Invalid Employee ID. Please enter a valid number.");
            return;
        }

        // Try to delete the employee with the given ID
        boolean deleted = deleteEmployeeByEmployeeId(employeeId);

        if (deleted) {
            lblResult.setText("Employee with ID " + employeeId + " has been deleted successfully.");
        } else {
            lblResult.setText("No employee found with ID " + employeeId + ".");
        }
    }

    private boolean deleteEmployeeByEmployeeId(int employeeId) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            // Attempt to retrieve the employee by employeeId
            Query<Employee> query = session.createQuery("FROM Employee WHERE employeeId = :employeeId", Employee.class);
            query.setParameter("employeeId", employeeId);
            Employee employee = query.uniqueResult();

            if (employee != null) {
                // Employee found, delete it
                session.delete(employee);
                session.getTransaction().commit();
                return true;
            } else {
                // Employee not found
                session.getTransaction().rollback();
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
