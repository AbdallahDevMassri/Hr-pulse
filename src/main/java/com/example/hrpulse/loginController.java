package com.example.hrpulse;

import com.example.hrpulse.Service.Employee;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;


public class loginController {
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
        wrongLogin.setText("שם משתמש או סיסמה שוגיים ");
    }

    }

    // create a methods to navigate for every page
    private void navigateToManagerPage(ActionEvent event) throws IOException {
        Parent managerViewParent = FXMLLoader.load(getClass().getResource("manager_view.fxml"));
        Scene managerViewScene = new Scene(managerViewParent);
        Stage mainStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        mainStage.setScene(managerViewScene);
        mainStage.show();
    }
    private void navigateToSecerterPage(ActionEvent event) throws IOException {
        Parent managerViewParent = FXMLLoader.load(getClass().getResource("secerteria_view.fxml"));
        Scene managerViewScene = new Scene(managerViewParent);
        Stage mainStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        mainStage.setScene(managerViewScene);
        mainStage.show();
    }
    private void navigateToHeadPage(ActionEvent event) throws IOException {
        Parent managerViewParent = FXMLLoader.load(getClass().getResource("head_view.fxml"));
        Scene managerViewScene = new Scene(managerViewParent);
        Stage mainStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        mainStage.setScene(managerViewScene);
        mainStage.show();
    }

}


