package com.example.hrpulse.Controllers;

import com.example.hrpulse.Services.Database.DatabaseManager;
import com.example.hrpulse.Services.Objects.Employee;
import com.example.hrpulse.Services.Interfaces.Navigators;
import com.example.hrpulse.Session.UserSession;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.util.Duration;
import org.hibernate.SessionFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static com.example.hrpulse.Services.Database.DatabaseManager.retrieveEmployees;

/**
 * The `loginController` class manages the user interface for the login functionality.
 */
public class loginController implements Navigators {
    private DatabaseManager databaseManager;
    private SessionFactory sessionFactory;

    @FXML
    private Label wrongLogin;

    private int loginAttempts = 0;
    private final int maxLoginAttempts = 3;
    private final Duration lockoutDuration = Duration.minutes(5);
    private ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);


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

        if (loginAttempts >= maxLoginAttempts) {
            wrongLogin.setText("יותר מידיי ניסיונות כושלים - אנה נסה שנית בעוד 5 דק");
            return;
        }

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
        } else if (employee != null && password.equals(employee.getPassword())) {
            // Successful login
            UserSession.getInstance().setCurrentUser(employee);
            navigateToMainPage(event);
        } else {
            // Incorrect username or password
            loginAttempts++;
            if (loginAttempts >= maxLoginAttempts) {
                scheduleUnlock();
            }
            wrongLogin.setText("שם משתמש או סיסמה שגויים, ניסיונות שנותרו: " +
                    (maxLoginAttempts - loginAttempts));
        }
    }

    private void scheduleUnlock() {
        Task<Void> unlockTask = new Task<>() {
            @Override
            protected Void call() throws Exception {
                TimeUnit.MINUTES.sleep((long) lockoutDuration.toMinutes());
                loginAttempts = 0;
                return null;
            }
        };

        unlockTask.addEventHandler(WorkerStateEvent.WORKER_STATE_SUCCEEDED, event -> {
            wrongLogin.setText("");
        });

        scheduler.schedule(unlockTask, 0, TimeUnit.SECONDS);
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
