package com.example.hrpulse.Controllers.EmployeeController;


import com.example.hrpulse.Services.Interfaces.EmployeeNavigators;
import com.example.hrpulse.Services.Objects.Employee;
import com.example.hrpulse.HR_Pulse;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import org.hibernate.SessionFactory;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import static com.example.hrpulse.HR_Pulse.retrieveEmployees;

/**
 * The EditEmployeeController handles the logic for editing employee details.
 */
public class EditEmployeeController implements EmployeeNavigators {

    // Constructors to allow for different ways of initializing the controller
    private HR_Pulse hrPulse;
    private SessionFactory sessionFactory;

    public EditEmployeeController() {
    }

    public EditEmployeeController(HR_Pulse hrPulse) {
        this.hrPulse = hrPulse;
        this.sessionFactory = null;
    }

    public EditEmployeeController(HR_Pulse hrPulse, SessionFactory sessionFactory) {
        this.hrPulse = hrPulse;
        this.sessionFactory = sessionFactory;
    }

    @FXML
    private TextField tf_firstName;
    @FXML
    private TextField tf_lastName;
    @FXML
    private TextField tf_employeeID;
    @FXML
    private TextField tf_email;
    @FXML
    private TextField tf_phoneNumber;
    @FXML
    private ChoiceBox<String> cb_gender;
    @FXML
    private ChoiceBox<String> cb_role;
    @FXML
    private TextField tf_password;

    @FXML
    private CheckBox cb_isHourly;
    @FXML
    private TextField tf_salaryPerHour;
    @FXML
    private CheckBox cb_isPerMoth;
    @FXML
    private TextField tf_perMonth;
    @FXML
    private TextField tf_salaryToTravel;
    @FXML
    private TextField tf_bankNumber;
    @FXML
    private TextField tf_acountNumber;
    @FXML
    private TextField tf_sneefBankCode;
    @FXML
    private ChoiceBox<String> cb_retriveEmployee;
    @FXML
    private GridPane gp_editEmployee;
    private List<Employee> employeeList;

    @FXML
    public void initialize() {
        // Initialization logic for the controller
        employeeList = retrieveEmployees();
        List<String> employeFullName = retrieveEmployeesNames(employeeList);
        cb_retriveEmployee.getItems().addAll(employeFullName);

        // Set an event handler for the cb_retriveEmployee ChoiceBox
        gp_editEmployee.setVisible(false);
        cb_retriveEmployee.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                gp_editEmployee.setVisible(true);
                fillEmployeeDetails(newValue);
            } else {
                gp_editEmployee.setVisible(false);
            }
        });

        // Add a change listener to cb_isPerMoth
        cb_isPerMoth.selectedProperty().addListener((observable, oldValue, newValue) -> {
            // Disable or enable cb_isHourly and tf_salaryPerHour based on newValue
            cb_isHourly.setDisable(newValue);
            tf_salaryPerHour.setDisable(newValue);
        });
        cb_isHourly.selectedProperty().addListener((observable, oldValue, newValue) -> {
            // Disable or enable cb_isHourly and tf_salaryPerHour based on newValue
            cb_isPerMoth.setDisable(newValue);
            tf_perMonth.setDisable(newValue);
        });
        cb_role.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            // Check if the selected role is "secretariat" or "headOfDepartment"
            if ("secretary".equals(newValue) || "headOfDepartment".equals(newValue)) {
                // Enable and show the disabled label
                tf_password.setDisable(false);
                tf_password.setVisible(true);
            } else {
                // Otherwise, disable and hide the label
                tf_password.setDisable(true);
                tf_password.setVisible(false);
            }
        });

    }

    // Method to fill the employee details in the form
    private void fillEmployeeDetails(String fullName) {
        // Extract the first name and last name from the full name
        String[] names = fullName.split(" ");
        String firstName = names[0];
        String lastName = names[1];
        // Find the employee with the given first name and last name
        Employee selectedEmployee = findEmployeeByName(firstName, lastName);
        // Fill the fields with the details of the selected employee
        tf_firstName.setText(selectedEmployee.getFirstName());
        tf_lastName.setText(selectedEmployee.getLastName());
        tf_employeeID.setText(String.valueOf(selectedEmployee.getEmployeeId()));
        tf_email.setText(selectedEmployee.getEmail());
        tf_phoneNumber.setText(selectedEmployee.getPhoneNumber());
        cb_gender.setValue(selectedEmployee.getGender());
        cb_role.setValue(selectedEmployee.getEmployeeRole());
        cb_isHourly.setSelected(selectedEmployee.isHourly());
        tf_password.setText(selectedEmployee.getPassword());
        tf_salaryPerHour.setText(String.valueOf(selectedEmployee.getHourlyRate()));
        tf_perMonth.setText(String.valueOf(selectedEmployee.getSalaryPerMonth()));
        tf_salaryToTravel.setText(String.valueOf(selectedEmployee.getSalaryToTravel()));
        tf_bankNumber.setText(String.valueOf(selectedEmployee.getBankInfo().getBankNumber()));
        tf_acountNumber.setText(selectedEmployee.getBankInfo().getAccountNumber());
        tf_sneefBankCode.setText(String.valueOf(selectedEmployee.getBankInfo().getBankSneefCode()));
    }

    // Method to find an employee by first name and last name
    private Employee findEmployeeByName(String firstName, String lastName) {
        for (Employee employee : employeeList) {
            if (employee.getFirstName().equals(firstName) && employee.getLastName().equals(lastName)) {
                return employee;
            }
        }
        return null; // Employee not found
    }

    // Method to retrieve employee names for the choice box
    private List<String> retrieveEmployeesNames(List<Employee> employeeList) {
        List<String> employeeName = new ArrayList<>();
        for (Employee employee : employeeList
        ) {
            employeeName.add(employee.getFirstName() + " " + employee.getLastName());
        }
        return employeeName;
    }


    // Method to handle employee deletion

    public void deletebtnclick(ActionEvent actionEvent) {

        String fullName = cb_retriveEmployee.getValue();
        if (fullName != null) {
            // Extract the first name and last name from the full name
            String[] names = fullName.split(" ");
            String firstName = names[0];
            String lastName = names[1];

            // Find the employee with the given first name and last name
            Employee selectedEmployee = findEmployeeByName(firstName, lastName);

            hrPulse.employeePDO(selectedEmployee, false);

            // Clear the choice box and hide the grid pane
            cb_retriveEmployee.setValue(null);
            gp_editEmployee.setVisible(false);
        }
    }

    // Method to handle saving updated employee details
    public void saveBtnClicked(ActionEvent actionEvent) {
        String fullName = cb_retriveEmployee.getValue();
        if (fullName != null) {
            // Extract the first name and last name from the full name
            String[] names = fullName.split(" ");
            String firstName = names[0];
            String lastName = names[1];

            // Find the employee with the given first name and last name
            Employee selectedEmployee = findEmployeeByName(firstName, lastName);

            // Update the selected employee with the new details
            selectedEmployee.setEmail(tf_email.getText());
            selectedEmployee.setPhoneNumber(tf_phoneNumber.getText());
            selectedEmployee.setGender(cb_gender.getValue());
            selectedEmployee.setEmployeeRole(cb_role.getValue());
            selectedEmployee.setHourly(cb_isHourly.isSelected());
            selectedEmployee.setPassword(tf_password.getText());
            selectedEmployee.setHourlyRate(Double.parseDouble(tf_salaryPerHour.getText()));
            selectedEmployee.setSalaryPerMonth(Double.parseDouble(tf_perMonth.getText()));
            selectedEmployee.setSalaryToTravel(Double.parseDouble(tf_salaryToTravel.getText()));
            selectedEmployee.getBankInfo().setBankNumber(Integer.parseInt(tf_bankNumber.getText()));
            selectedEmployee.getBankInfo().setAccountNumber(tf_acountNumber.getText());
            selectedEmployee.getBankInfo().setBankSneefCode(Integer.parseInt(tf_sneefBankCode.getText()));


            hrPulse.employeePDO(selectedEmployee, true);

            // Clear the choice box and hide the grid pane
            cb_retriveEmployee.setValue(null);
            gp_editEmployee.setVisible(false);
        }
    }

    // Method to navigate back to the manage employee page
    public void backbtn(ActionEvent actionEvent) throws IOException {
        navigateToManageEmployeePage(actionEvent);
    }

}
