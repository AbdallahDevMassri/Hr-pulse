package com.example.hrpulse.Controllers.EmployeeController;

import com.example.hrpulse.HR_Pulse;
import com.example.hrpulse.Services.CSV.CsvService;
import com.example.hrpulse.Services.Interfaces.EmployeeNavigators;
import com.example.hrpulse.Services.Objects.Department;
import com.example.hrpulse.Services.Objects.Employee;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.hibernate.SessionFactory;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class CreateEmployeePageController implements EmployeeNavigators {

    //----------------------------------------these constructors is to allow for different ways of initializing the controller-----------------------------------------------------------------------
    private HR_Pulse hrPulse;
    // Inject the SessionFactory
    private SessionFactory sessionFactory;

    public CreateEmployeePageController() {
        // Default constructor
    }

    public CreateEmployeePageController(HR_Pulse hrPulse) {
        this.hrPulse = hrPulse;
        // Initialize the controller here if needed
        this.sessionFactory = null; // Or initialize it if needed
    }

    public CreateEmployeePageController(HR_Pulse hrPulse, SessionFactory sessionFactory) {
        this.hrPulse = hrPulse;
        this.sessionFactory = sessionFactory;
    }

    //---------------------------------------------------------------------------------------------------------------
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
    private ChoiceBox<String> cb_department;
    @FXML
    private DatePicker dp_dateOfBirth;
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
    void initialize() {
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
            if ("secretariat".equals(newValue) || "headOfDepartment".equals(newValue)) {
                // Enable and show the disabled label
                tf_password.setDisable(false);
                tf_password.setVisible(true);
            } else {
                // Otherwise, disable and hide the label
                tf_password.setDisable(true);
                tf_password.setVisible(false);
            }
        });
//        List<Department> departments = HR_Pulse.fetchDepartments();
//        if (departments != null) {
//            List<String> departmentNames = departments.stream().map(Department::getDepartmentName).collect(Collectors.toList());
//            cb_department.setItems(FXCollections.observableArrayList(departmentNames));
//        }

    }
    @FXML
    void goToMain(ActionEvent event) throws IOException {
        navigateToManageEmployeePage(event);
    }


    @FXML
    void saveButtonClicked(ActionEvent event) {
        // Collect data from input fields
        String firstName = tf_firstName.getText();
        String lastName = tf_lastName.getText();
        String employeeIDText = tf_employeeID.getText();
        String email = tf_email.getText();
        String phoneNumber = tf_phoneNumber.getText();
        String gender = cb_gender.getValue();
        String role = cb_role.getValue();
        String password = tf_password.getText();
        String department = cb_department.getValue();
        // Retrieve the selected date from the DatePicker
        LocalDate dateOfBirth = dp_dateOfBirth.getValue();
        boolean isHourly = cb_isHourly.isSelected();
        //-----------------------------------------------------------------------
        String salaryPerHourText = tf_salaryPerHour.getText();
        int salaryPerHour=0;
        try {
             salaryPerHour = Integer.parseInt(salaryPerHourText);
        } catch (NumberFormatException e) {
            showErrorDialog("שגיאה בקלדת נתונים : שכר לשעה ");
        }

        boolean isPerMonth = cb_isPerMoth.isSelected();
        //-----------------------------------------------------------------------
        String salaryPerMonthText = tf_perMonth.getText();
        Double salaryPerMonth=0.0;
        try {
             salaryPerMonth = Double.parseDouble(salaryPerMonthText);
        } catch (NumberFormatException e) {
            showErrorDialog("שגיאה בקלדת נתונים : שכר חודשי ");
        }
        //------------------------------------------------------------------------
        String salaryToTravelText = tf_salaryToTravel.getText();
        Double salaryToTravel=0.0;
        try {
             salaryToTravel = Double.parseDouble(salaryToTravelText);
        } catch (NumberFormatException e) {
            showErrorDialog("שגיאה בקלדת נתונים : נסיעות חודשי ");
        }
        //------------------------BankNumber validate--------------------------------
        String bankNumberText = tf_bankNumber.getText();
        int bankNumber =0;
        try {
            bankNumber = Integer.parseInt(bankNumberText);
        } catch (NumberFormatException e) {
            showErrorDialog("שגיאה בקלדת נתונים : מספר בנק ");
        }
        //--------------------------------------------------------
        String acountNumber = tf_acountNumber.getText();
        //-----------------validate that sneef has just a numbers
        String sneefBankCodeText = tf_sneefBankCode.getText();
        int sneefBankCode=0;
        try {
             sneefBankCode = Integer.parseInt(sneefBankCodeText);
        } catch (NumberFormatException e) {
            showErrorDialog("שגיאה בקלדת נתונים : מספר סניף בנק");
        }
        //--------------------------------------------------------
        // Check if a date is selected
        if (dateOfBirth == null) {
            showErrorDialog("נא בחר תאירך לידה ");
            return; // Exit the method
        }

        // Parse employee ID as an integer
        int employeeID = 0;
        try {
            employeeID = Integer.parseInt(employeeIDText);
        } catch (NumberFormatException e) {
            showErrorDialog("Invalid employee ID. Please enter a valid number.");
            return; // Exit the method
        }

        // Create an Employee object
        Employee employee = new Employee();
        employee.setFirstName(firstName);
        employee.setLastName(lastName);
        employee.setEmployeeId(employeeID);
        employee.setPhoneNumber(phoneNumber);
        employee.setEmail(email);
        employee.setGender(gender);
        employee.setEmployeeRole(role);
        employee.setDepartment(department);
        employee.setDateOfBirth(dateOfBirth);
        employee.setPassword(password);
       employee.setHourly(isHourly);
       employee.setHourlyRate(salaryPerHour);
       employee.setSalaryPerMonth(salaryPerMonth);
       employee.setSalaryToTravel(salaryToTravel);
       employee.getBankInfo().setBankNumber(bankNumber);
       employee.getBankInfo().setAccountNumber(acountNumber);
       employee.getBankInfo().setBankSneefCode(sneefBankCode);

        // Save the data to the database using HR_Pulse's method

        hrPulse.performDatabaseOperations(employee);

        // Optionally, display a confirmation message
        showConfirmationDialog("נתוני העובד התווספו בהצלחה .");
    }

    // Helper method to save data to a CSV file
    private void saveToCSV(Employee employee, String filePath) {
        List<String[]> csvData = new ArrayList<>();

        // Convert employee data to a String array
        String[] employeeData = {
                String.valueOf(employee.getEmployeeId()),
                employee.getFirstName(),
                employee.getLastName(),
                // Add other fields here
        };

        // Add the employee data to the CSV data list
        csvData.add(employeeData);

        // Write the CSV data to the file
        CsvService.writeCsv(filePath, csvData);
    }

    // Helper method to show a confirmation dialog
    private void showConfirmationDialog(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Employee Saved");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    // Helper method to show an error dialog
    private void showErrorDialog(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    // Helper method to print employee details
    private void printEmployeeDetails(Employee employee) {
        System.out.println("Employee Details:");
        System.out.println("First Name: " + employee.getFirstName());
        System.out.println("Last Name: " + employee.getLastName());
        // Print other details as needed
    }
}




