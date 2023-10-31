package com.example.hrpulse.Controllers;

import com.example.hrpulse.HR_Pulse;
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
import static com.example.hrpulse.HR_Pulse.retrieveEmployees;

public class loginController implements Navigators {
    private HR_Pulse hrPulse;
    private SessionFactory sessionFactory;

    public loginController() {
        // Default constructor
    }
    public loginController(HR_Pulse hrPulse) {
        this.hrPulse = hrPulse;
        this.sessionFactory = null;
    }
    public loginController(HR_Pulse hrPulse, SessionFactory sessionFactory) {
        this.hrPulse = hrPulse;
        this.sessionFactory = sessionFactory;
    }
    private static Map<String, Employee> employees = new HashMap<>();

    static {
        employees.put("admin_m", new Employee("manager", "Globalvpsm@gmail.com", "0535216838", "1234"));
        employees.put("admin_s", new Employee("secretary", "Globalvpsm@gmail.com", "0535216838", "1234"));
        employees.put("admin_h", new Employee("head", "A.v.e@live.com", "0535216838", "1234"));
    }
    @FXML
    private PasswordField tf_Password;
    @FXML
    private TextField tf_UserName;
    @FXML
    private Label wrongLogin;
    private static List<Employee> employeesList = getEmployees();
    private static List<Employee> getEmployees() {

        return retrieveEmployees();
    }

    @FXML
    void ExitButtonClicked(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    void userLogin(ActionEvent event) throws IOException {
        String username = tf_UserName.getText().trim().toLowerCase();
        String password = tf_Password.getText().toLowerCase();
        Employee employee = employees.get(username);
        Employee employeeDb = getEmployeeByUsernameAndPassword(username, password);
        UserSession.getInstance().setCurrentUser(employeeDb);
        Employee currentUser = UserSession.getInstance().getCurrentUser();

        if (currentUser != null) {
            // Employee found in the database
            String employeeRole = currentUser.getEmployeeRole();

            switch (employeeRole) {
                case "manager":
                    navigateToManagerPage(event);
                    break;
                case "secretary":
                    navigateToSecretaryPage(event);
                    break;
                case "headOfDepartment":
                    navigateToHeadPage(event);
                    break;
                default:
                    wrongLogin.setText("תפקיד אינו תואם ");
                    break;
            }
        }

        if (employee != null && password.equals(employee.getPassword())) {
            if (username.equals("admin_m")) {
                wrongLogin.setText("שגיאת מערכת");
                UserSession.getInstance().setCurrentUser(employee);
                navigateToManagerPage(event);
            } else if (username.equals("admin_s")) {
                wrongLogin.setText("שגיאת מערכת");
                UserSession.getInstance().setCurrentUser(employee);
                navigateToSecretaryPage(event);
            } else if (username.equals("admin_h")) {
                wrongLogin.setText("שגיאת מערכת");
                UserSession.getInstance().setCurrentUser(employee);
                navigateToHeadPage(event);
            } else {
                wrongLogin.setText("שגיאת מערכת"); //print error if unsuccessful log-in
            }
        } else if (username.trim().isEmpty() && password.trim().isEmpty()) {
            wrongLogin.setText("נא הכנס את שם המשתמש והסיסמה שלך"); // print that the user needs to enter user&pass
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


