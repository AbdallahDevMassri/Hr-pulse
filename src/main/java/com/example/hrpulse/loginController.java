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


public class loginController implements Navigators {
    public static Employee manager = new Employee("Admin", "Massri", "000000000", "A.v.e@live.com", "0523239955", "1234");

    public static Employee secerteia = new Employee("secerter", "Massri", "000000000", "A.v.e@live.com", "0523239955", "1234");
    public static Employee headOfDepratment = new Employee("head", "Massri", "000000000", "A.v.e@live.com", "0523239955", "1234");
    @FXML
    private PasswordField tf_Password;
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

        String username = tf_UserName.getText();
        String password = tf_Password.getText();
        System.out.println("user name is    :" + username);
        System.out.println("Password is     :" + password);

    if(username.equals(manager.getFirstName())&&password.equals("1234")){
        wrongLogin.setText("בוצע");
        navigateToManagerPage(event);

    } else if (username.equals(secerteia.getFirstName())&&password.equals("1234")) {
        wrongLogin.setText("בוצע");
        navigateToSecerterPage(event);


    } else if (username.equals(headOfDepratment.getFirstName())&&password.equals("1234") ) {
        wrongLogin.setText("בוצע");
        navigateToHeadPage(event);

    } else if (username.trim().isEmpty()&&password.trim().isEmpty()) {

        wrongLogin.setText("נא הכנס את שם המשתמש והסיסמה שלך");
    }
    else {
        navigateToRegularEmployeesPage(event);
        wrongLogin.setText("שם משתמש או סיסמה שוגיים ");
    }

    }




}


