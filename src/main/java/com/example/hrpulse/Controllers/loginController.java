package com.example.hrpulse.Controllers;

import com.example.hrpulse.Services.Objects.Employee;
import com.example.hrpulse.Services.Interfaces.Navigators;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class loginController implements Navigators {
    private static Map<String, Employee> employees = new HashMap<>();


    static {
        employees.put("admin_m", new Employee("manager", "Globalvpsm@gmail.com", "0535216838", "1234"));
        employees.put("admin_s", new Employee("secretary", "Globalvpsm@gmail.com", "0535216838", "1234"));
        employees.put("admin_h", new Employee("head", "A.v.e@live.com", "0535216838", "1234"));

    }


    @FXML
    private PasswordField tf_Password;
    @FXML
    private Button exitButton;
    @FXML
    private TextField tf_UserName;

    @FXML
    private Label wrongLogin;


    @FXML
    void ExitButtonClicked(ActionEvent event) {
        // Hibernate configuration
        Configuration configuration = new Configuration().configure();
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties()).build();
        SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);

        // Open a Hibernate session
        Session session = sessionFactory.openSession();

        // Retrieve metadata and get table names
        String[] tableNames = session.getSessionFactory().getMetamodel().getEntities().stream()
                .map(e -> e.getName())
                .toArray(String[]::new);

        // Close the Hibernate session and session factory
        session.close();
        sessionFactory.close();

        // Display table names
        for (String tableName : tableNames) {
            System.out.println("Table Name: " + tableName);
        }
    }

    @FXML
    void userLogin(ActionEvent event) throws IOException {
        String username = tf_UserName.getText().trim().toLowerCase();
        String password = tf_Password.getText().toLowerCase();

        Employee employee = employees.get(username);

        if (employee != null && password.equals(employee.getPassword())) {
            if (username.equals("admin_m")) {
                wrongLogin.setText("שגיאת מערכת");
                navigateToManagerPage(event);
            } else if (username.equals("admin_s")) {
                wrongLogin.setText("שגיאת מערכת");
                navigateToSecretaryPage(event);
            } else if (username.equals("admin_h")) {
                wrongLogin.setText("שגיאת מערכת");
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


}


