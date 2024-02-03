package com.example.hrpulse.Controllers;

import com.example.hrpulse.Services.Database.DatabaseManager;
import com.example.hrpulse.Services.Objects.Employee;
import com.example.hrpulse.Services.Interfaces.Navigators;
import com.example.hrpulse.Session.UserSession;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.hibernate.SessionFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.hrpulse.Services.Database.DatabaseManager.retrieveEmployees;

/**
 * The `loginController` class manages the user interface for the login functionality.
 */
public class loginController implements Navigators {
    private DatabaseManager databaseManager;
    private SessionFactory sessionFactory;

    public loginController() {
        // Default constructor
    }

    public loginController(DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
        this.sessionFactory = null;
    }

    public loginController(DatabaseManager databaseManager, SessionFactory sessionFactory) {
        this.databaseManager = databaseManager;
        this.sessionFactory = sessionFactory;
    }

    // Hard coded list of employees -  to be replaced with a database / bypass the database login
    private static final Map<String, Employee> employees = new HashMap<>();

    static {
        employees.put("admin_m", new Employee("manager", "Globalvpsm@gmail.com", "0535216838", "1234", "manager"));
        employees.put("admin_s", new Employee("secretary", "Globalvpsm@gmail.com", "0535216838", "1234", "secretary"));
        employees.put("admin_h", new Employee("head", "A.v.e@live.com", "0535216838", "1234", "headOfDepartment"));
    }

    @FXML
    private PasswordField tf_Password;

    @FXML
    private TextField tf_UserName;

    @FXML
    private Label wrongLogin;

    private static final List<Employee> employeesList = getEmployees();

    private static List<Employee> getEmployees() {
        return retrieveEmployees();
    }

    @FXML
    void ExitButtonClicked() {
        Platform.exit();
    }

    @FXML
    void userLogin(ActionEvent event) throws IOException {
        String username = tf_UserName.getText().trim().toLowerCase();
        String password = tf_Password.getText().toLowerCase();
        // get the local employee that we declare if not found it returns null.
        Employee employee = employees.get(username);
        System.out.print("employee");
        System.out.println(employee);
        // get the employee from the database if not found return null.
        Employee employeeDb = getEmployeeByUsernameAndPassword(username, password);
        System.out.print("employeeDb");
        System.out.println(employeeDb);

        if (employeeDb != null) {
            // Employee found in the database
            // Declare a new User
            UserSession.getInstance().setCurrentUser(employeeDb);
            navigateToMainPage(event);
        }

        if (employee != null && password.equals(employee.getPassword())) {
            UserSession.getInstance().setCurrentUser(employee);
            navigateToMainPage(event);
        } else if (username.trim().isEmpty() && password.trim().isEmpty()) {
            wrongLogin.setText("נא הכנס את שם המשתמש והסיסמה שלך"); // print that the user needs to enter user & pass
        } else {
            wrongLogin.setText("שם משתמש או סיסמה שגויים"); // print an error for wrong user/pass
        }
    }

    private Employee getEmployeeByUsernameAndPassword(String username, String password) {
        for (Employee employee : employeesList) {
            if (String.valueOf(employee.getEmployeeId()).equals(username) && employee.getPassword().equals(password)) {
                return employee;
            }
        }
        return null;
    }
}
