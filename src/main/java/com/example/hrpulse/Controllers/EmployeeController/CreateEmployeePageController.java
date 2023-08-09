package com.example.hrpulse.Controllers.EmployeeController;

import com.example.hrpulse.Service.Intefaces.EmployeeNavigators;
import com.example.hrpulse.Service.Intefaces.Navigators;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.io.IOException;

public class CreateEmployeePageController implements EmployeeNavigators {


        @FXML
        private Button backButton;

        @FXML
        private ChoiceBox<?> cb_department;

        @FXML
        private ChoiceBox<?> cb_gender;

        @FXML
        private CheckBox cb_isHourly;

        @FXML
        private ChoiceBox<?> cb_role;

        @FXML
        private ChoiceBox<?> cb_status;

        @FXML
        private ChoiceBox<?> sc_head;

        @FXML
        private TextField tf_Id;

        @FXML
        private TextField tf_acountNumber;

        @FXML
        private TextField tf_email;

        @FXML
        private TextField tf_firstName;

        @FXML
        private TextField tf_lastName;

        @FXML
        private TextField tf_password;

        @FXML
        private TextField tf_phoneNumber;

        @FXML
        private TextField tf_repeatedPassword;

        @FXML
        private TextField tf_salaryPerHour;

        @FXML
        private TextField tf_salaryToTravel;

        @FXML
        private TextField tf_sneefBankCode;



        @FXML
        void saveButtonClicked(ActionEvent event) {

        }




    @FXML
    void goToMain(ActionEvent event) throws IOException {
    navigateToManageEmployeePage(event);
    }

}
