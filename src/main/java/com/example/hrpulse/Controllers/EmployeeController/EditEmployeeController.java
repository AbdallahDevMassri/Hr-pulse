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
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import static com.example.hrpulse.HR_Pulse.retrieveEmployees;


public class EditEmployeeController implements EmployeeNavigators {

    private  HR_Pulse hrPulse;
    private  SessionFactory sessionFactory;

   public EditEmployeeController() {}
    public EditEmployeeController(HR_Pulse hrPulse) {
        this.hrPulse =hrPulse;
        this.sessionFactory= null;
    }

    public EditEmployeeController(HR_Pulse hrPulse, SessionFactory sessionFactory) {
        this.hrPulse = hrPulse;
        this.sessionFactory = sessionFactory;
    }
    @FXML
    private Button backButton;

    @FXML
    private ChoiceBox<?> cb_department;

    @FXML
    private ChoiceBox<String> cb_gender;

    @FXML
    private CheckBox cb_isHourly;

    @FXML
    private CheckBox cb_isPerMoth;

    @FXML
    private ChoiceBox<String> cb_retriveEmployee;

    @FXML
    private ChoiceBox<String> cb_role;

    @FXML
    private DatePicker dp_dateOfBirth;

    @FXML
    private DatePicker dp_startDate;

    @FXML
    private GridPane gp_editEmployee;

    @FXML
    private Button saveButton;

    @FXML
    private TextField tf_acountNumber;

    @FXML
    private TextField tf_bankNumber;

    @FXML
    private TextField tf_email;

    @FXML
    private TextField tf_employeeID;

    @FXML
    private TextField tf_firstName;

    @FXML
    private TextField tf_lastName;

    @FXML
    private TextField tf_password;

    @FXML
    private TextField tf_perMonth;

    @FXML
    private TextField tf_phoneNumber;

    @FXML
    private TextField tf_salaryPerHour;

    @FXML
    private TextField tf_salaryToTravel;

    @FXML
    private TextField tf_sneefBankCode;
    private List<Employee> employeeList;

    @FXML
    public void initialize (){
        employeeList = retrieveEmployees();
        List<String> employeFullName =retrieveEmployeesNames(employeeList);
        cb_retriveEmployee.getItems().addAll(employeFullName);

        // Set an event handler for the cb_retriveEmployee ChoiceBox
        gp_editEmployee.setVisible(false);
        cb_retriveEmployee.getSelectionModel().selectedItemProperty().addListener((observable,oldValue,newValue)->{
            if(newValue !=null){
                gp_editEmployee.setVisible(true);
                fillEmployeeDetails(newValue);
            }else {
                gp_editEmployee.setVisible(false);
            }
        });
//

    }

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

        // Additional fields
        cb_gender.setValue(selectedEmployee.getGender());
        cb_role.setValue(selectedEmployee.getEmployeeRole());

        // Check and set values for checkboxes
        cb_isHourly.setSelected(selectedEmployee.isHourly());
//        cb_isPerMoth.setSelected(selectedEmployee.isPerMonth());

        // Date pickers
//        if (selectedEmployee.getDateOfBirth() != null) {
//            dp_dateOfBirth.setValue(selectedEmployee.getDateOfBirth().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
//        }
//        if (selectedEmployee.getEmploymentStartDate() != null) {
//            dp_startDate.setValue(selectedEmployee.getEmploymentStartDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
//        }


        // Other text fields
        tf_password.setText(selectedEmployee.getPassword());
        tf_salaryPerHour.setText(String.valueOf(selectedEmployee.getHourlyRate()));
        tf_perMonth.setText(String.valueOf(selectedEmployee.getSalaryPerMonth()));
        tf_salaryToTravel.setText(String.valueOf(selectedEmployee.getSalaryToTravel()));
        tf_bankNumber.setText(String.valueOf(selectedEmployee.getBankInfo().getBankNumber()));
        tf_acountNumber.setText(selectedEmployee.getBankInfo().getAccountNumber());
        tf_sneefBankCode.setText(String.valueOf(selectedEmployee.getBankInfo().getBankSneefCode()));
    }

    private Employee findEmployeeByName(String firstName, String lastName) {
        for (Employee employee : employeeList) {
            if (employee.getFirstName().equals(firstName) && employee.getLastName().equals(lastName)) {
                return employee;
            }
        }
        return null; // Employee not found
    }

    private List<String> retrieveEmployeesNames(List<Employee> employeeList) {
      List<String> employeeName = new ArrayList<>();
        for (Employee employee:employeeList
             ) {
            employeeName.add(employee.getFirstName()+" "+employee.getLastName());
        }
        return employeeName;
    }


    @FXML
    public void backToManageEmployee(ActionEvent actionEvent) throws IOException{
        navigateToManageEmployeePage(actionEvent);
    }

    public void deletebtnclick(ActionEvent actionEvent) {

        String fullName = cb_retriveEmployee.getValue();
        if (fullName != null) {
            // Extract the first name and last name from the full name
            String[] names = fullName.split(" ");
            String firstName = names[0];
            String lastName = names[1];

            // Find the employee with the given first name and last name
            Employee selectedEmployee = findEmployeeByName(firstName, lastName);

            // Perform delete operation (you need to implement this in HR_Pulse or your data access layer)
            hrPulse.deleteEmployee(selectedEmployee);
//            hrPulse.employeePDO(selectedEmployee,false);

            // Clear the choice box and hide the grid pane
            cb_retriveEmployee.setValue(null);
            gp_editEmployee.setVisible(false);
        }
    }

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
            // ... continue filling other fields accordingly
//            selectedEmployee.setDateOfBirth(dp_dateOfBirth.getValue() != null ?
//                    Date.from(dp_dateOfBirth.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()) : null);

            selectedEmployee.setEmploymentStartDate(dp_startDate.getValue() != null ?
                    Date.from(dp_startDate.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()) : null);

            selectedEmployee.setPassword(tf_password.getText());
            selectedEmployee.setHourlyRate(Double.parseDouble(tf_salaryPerHour.getText()));
            selectedEmployee.setSalaryPerMonth(Double.parseDouble(tf_perMonth.getText()));
            selectedEmployee.setSalaryToTravel(Double.parseDouble(tf_salaryToTravel.getText()));
            selectedEmployee.getBankInfo().setBankNumber(Integer.parseInt(tf_bankNumber.getText()));
            selectedEmployee.getBankInfo().setAccountNumber(tf_acountNumber.getText());
            selectedEmployee.getBankInfo().setBankSneefCode(Integer.parseInt(tf_sneefBankCode.getText()));


            // Perform update operation (you need to implement this in HR_Pulse or your data access layer)
//            hrPulse.employeePDO(selectedEmployee ,true);
            hrPulse.employeePDO(selectedEmployee,true);

            // Clear the choice box and hide the grid pane
            cb_retriveEmployee.setValue(null);
            gp_editEmployee.setVisible(false);
        }
    }

    public void backbtn(ActionEvent actionEvent) throws IOException {
        navigateToManageEmployeePage(actionEvent);
    }
}
