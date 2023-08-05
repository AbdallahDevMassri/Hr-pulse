package com.example.hrpulse;

import com.example.hrpulse.Service.Employee;
import com.example.hrpulse.Service.Navigators;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class loginController implements Navigators  {
    private static Map<String, Employee> employees = new HashMap<>();

    static {
        employees.put("manager", new Employee("manager", "Globalvpsm@gmail.com", "0535216838", "1234"));
        employees.put("manager2", new Employee("manager2", "A.v.e@live.com", "0523239955", "12345"));
        employees.put("secretary",  new Employee("secretary", "Globalvpsm@gmail.com", "0535216838", "1234"));
        employees.put("secretary2", new Employee("secretary1", "A.v.e@live.com", "0523239955", "12345"));
        employees.put("head", new Employee("head", "Globalvpsm@gmail.com", "0535216838", "1234"));
        employees.put("head2", new Employee("head1", "A.v.e@live.com", "0523239955", "12345"));
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
        Platform.exit();
    }
    @FXML
    void userLogin(ActionEvent event) throws IOException {
        String username = tf_UserName.getText().trim();
        String password = tf_Password.getText();

        Employee employee = employees.get(username);

        if (employee != null && password.equals(employee.getPassword())) {
            if (username.equals("manager")) {
                wrongLogin.setText("שגיאת מערכת");
                navigateToManagerPage(event);
            } else if (username.equals("secretary")) {
                wrongLogin.setText("שגיאת מערכת");
                navigateToSecretaryPage(event);
            } else if (username.equals("head")) {
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


