package com.example.hrpulse.Controllers.EmployeeController;

import com.example.hrpulse.Services.Database.DatabaseManager;
import com.example.hrpulse.Services.Interfaces.EmployeeNavigators;
import com.example.hrpulse.Services.Objects.Department;
import com.example.hrpulse.Services.Objects.Employee;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.hibernate.SessionFactory;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import static com.example.hrpulse.Controllers.DepartmentController.EditDepartmentController.retrieveDepartmentNames;
import static com.example.hrpulse.Services.Database.DatabaseManager.retrieveDepartments;
import static com.example.hrpulse.Services.Database.DatabaseManager.retrieveEmployees;


/**
 * The CreateEmployeePageController handles the logic for creating a new employee.
 */
public class CreateEmployeePageController implements EmployeeNavigators {

    // Constructors to allow for different ways of initializing the controller
    private DatabaseManager databaseManager;
    private SessionFactory sessionFactory;

    public CreateEmployeePageController() {
        // Default constructor
    }

    public CreateEmployeePageController(DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
        // Initialize the controller here if needed
        this.sessionFactory = null; // Or initialize it if needed
    }

    public CreateEmployeePageController(DatabaseManager databaseManager, SessionFactory sessionFactory) {
        this.databaseManager = databaseManager;
        this.sessionFactory = sessionFactory;
    }

    // FXML elements injected from the corresponding FXML file
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
    private DatePicker dp_startDate;
    @FXML
    private CheckBox cb_isHourly;
    @FXML
    private CheckBox cb_isPerMoth;
    @FXML
    private TextField tf_salaryPerHour;
    @FXML
    private TextField tf_perMonth;
    @FXML
    private TextField tf_salaryToTravel;
    @FXML
    private TextField tf_bankNumber;
    @FXML
    private TextField tf_accountNumber;
    @FXML
    private TextField tf_sneefBankCode;

    @FXML
    void initialize() {
        // Initialization logic for the controller


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
        // declare a list of departments
        List<Department> departments;
        // retrieve the department from database by using the static method from the hr_pulse class
        departments = retrieveDepartments();

        // Check if departments are not null before populating the ComboBox
        if (departments != null) {
            // Retrieve department names using the static method that is presumably in the EditDepartment class
            List<String> departmentNames = retrieveDepartmentNames(departments);

            // Populate the ComboBox with department names
            cb_department.getItems().addAll(departmentNames);
        }

    }

    @FXML
    void goToMain(ActionEvent event) throws IOException {
        navigateToManageEmployeePage(event);
    }


    @FXML
    void saveButtonClicked(ActionEvent event) {
        // Logic for saving employee data
        //      Collect data from input fields
        //  Validate first name
        String firstName = tf_firstName.getText().trim();
        if (firstName.isEmpty()) {
            showErrorDialog("נא הכנס שם עובד ");
            return;
        }
        if (firstName.length() < 2) {
            showErrorDialog("שם עובד קצר מדי ");
            return;
        }

        // Validate last name
        String lastName = tf_lastName.getText().trim();
        if (lastName.isEmpty()) {
            showErrorDialog("נא הכנס שם משפחה של עובד ");
            return;
        }
        if (lastName.length() < 2) {
            showErrorDialog("שם משפחה קצר מדי ");
            return;
        }

        // Validate employee ID
        String employeeIDText = tf_employeeID.getText().trim();
        if (employeeIDText.isEmpty()) {
            showErrorDialog("נא הכנס ת.ז .");
            return;
        }
        int employeeID = 0;

        try {
            employeeID = Integer.parseInt(employeeIDText);
            if (employeeIDText.trim().length() != 9) {
                showErrorDialog("ת.ז חייבת להיות 9 ספרות / רק מספרים");
                return;
            }
        } catch (NumberFormatException e) {
            showErrorDialog("ת.ז חייבת להיות 9 ספרות / רק מספרים");
            return;
        }

        // Check if the employee ID is unique
        if (!isEmployeeIDUnique(employeeID)) {
            showErrorDialog("ת.ז כבר נמצאת במערכת .");
            return;
        }
        String email = tf_email.getText();
        if (email.isEmpty()) {
            showErrorDialog("נא הכנס דואר אלקטרוני .");
            return;
        }

        // Validate phone number
        String phoneNumber = tf_phoneNumber.getText().trim();
        if (phoneNumber.isEmpty()) {
            showErrorDialog("נא הכנס מספר נייד .");
            return;
        }
        String department = cb_department.getValue();
        // Check if a department name is selected
        if (department == null || department.isEmpty()) {
            showErrorDialog("נא הכנס מחלקה .");
            return; // Exit the method
        }
        String gender = cb_gender.getValue();
        // Validate role
        String role = cb_role.getValue();
        if (role == null || role.isEmpty()) {
            showErrorDialog("נא הכנס תפקיד .");
            return;
        }
        // If role requires a password, validate the password field
        if ("secretary".equals(role) || "headOfDepartment".equals(role)) {
            String password = tf_password.getText().trim();
            if (password.isEmpty()) {
                showErrorDialog("נא בחר סיסמה לעובד זה !!");
                return;
            }
        }
        String password = tf_password.getText();


        // Retrieve the selected date from the DatePicker
        LocalDate dateOfBirth = dp_dateOfBirth.getValue();

        LocalDate employmentStartDate = dp_startDate.getValue();

        boolean isHourly = cb_isHourly.isSelected();
        //-----------------------------------------------------------------------
        String salaryPerHourText = tf_salaryPerHour.getText();
        int salaryPerHour = 0;
        try {
            salaryPerHour = Integer.parseInt(salaryPerHourText);
        } catch (NumberFormatException e) {
            System.err.println("error convert salaryPerHour");

        }

        boolean isPerMonth = cb_isPerMoth.isSelected();
        //------------------------------------------------------------------------
        String salaryPerMonthText = tf_perMonth.getText();
        double salaryPerMonth = 0.0;
        try {
            salaryPerMonth = Double.parseDouble(salaryPerMonthText);
        } catch (NumberFormatException e) {
            System.err.println("error convert salaryPerMonth");
        }
        //------------------------------------------------------------------------
        String salaryToTravelText = tf_salaryToTravel.getText();
        double salaryToTravel = 0.0;
        try {
            salaryToTravel = Double.parseDouble(salaryToTravelText);
        } catch (NumberFormatException e) {
            System.err.println("error convert salaryToTravel");
        }
        //------------------------------------------------------------------------
        String bankNumberText = tf_bankNumber.getText();
        int bankNumber = 0;
        try {
            bankNumber = Integer.parseInt(bankNumberText);
        } catch (NumberFormatException e) {
            System.err.println("error convert bankNumber");
        }
        //------------------------------------------------------------------------
        String acountNumber = tf_accountNumber.getText();
        //-----------------validate that sneef has just a numbers
        String sneefBankCodeText = tf_sneefBankCode.getText();
        int sneefBankCode = 0;
        try {
            sneefBankCode = Integer.parseInt(sneefBankCodeText);
        } catch (NumberFormatException e) {
            System.err.println("error convert sneefBankCode");
        }
        //------------------------------------------------------------------------
        // Create an Employee object
        Employee employee = new Employee();
        employee.setFirstName(firstName);
        employee.setLastName(lastName);
        employee.setEmployeeId(employeeID);
        employee.setPhoneNumber(phoneNumber);
        employee.setEmail(email);
        employee.setGender(gender);
        employee.setEmployeeRole(role);
        employee.setDateOfBirth(dateOfBirth);
        employee.setPassword(password);
        employee.setHourly(isHourly);
        employee.setHourlyRate(salaryPerHour);
        employee.setSalaryPerMonth(salaryPerMonth);
        employee.setSalaryToTravel(salaryToTravel);
        employee.getBankInfo().setBankNumber(bankNumber);
        employee.getBankInfo().setAccountNumber(acountNumber);
        employee.getBankInfo().setBankSneefCode(sneefBankCode);
        if (employmentStartDate != null) {
            employee.setEmploymentStartDate(Date.from(employmentStartDate.atStartOfDay(ZoneId.systemDefault()).toInstant()));
        } else {
            // Set the employment start date to the current day
            LocalDate currentDay = LocalDate.now();
            employee.setEmploymentStartDate(Date.from(currentDay.atStartOfDay(ZoneId.systemDefault()).toInstant()));
        }

        String departmentName = cb_department.getValue(); // Get the selected department name

        // Retrieve the selected department based on its name
        Department selectedDepartment = getDepartmentByName(departmentName);

        // Associate the employee with the selected department
        employee.setDepartment(selectedDepartment.getDepartmentName());

        // Save the data to the database using HR_Pulse's method

        databaseManager.performDatabaseOperations(employee);

        // Optionally, display a confirmation message
        showConfirmationDialog("נתוני העובד התווספו בהצלחה .");
        clearInputFields();

    }

    // Method to retrieve a department by its name
    private Department getDepartmentByName(String departmentName) {
        // declare a list of departments
        List<Department> allDepartments;
        // retrieve the department from database by using the static method from the hr_pulse class
        allDepartments = retrieveDepartments();
        for (Department department : allDepartments) {
            if (department.getDepartmentName().equals(departmentName)) {
                return department;
            }
        }
        return null; // Department not found

    }


    // Helper method to show a confirmation dialog
    private void showConfirmationDialog(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(" Confirm ");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    // Helper method to show an error dialog
    private void showErrorDialog(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(" Error ");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    // Method to check if an employee ID is unique
    private boolean isEmployeeIDUnique(int employeeID) {

        List<Employee> employees;

        employees = retrieveEmployees();
        for (Employee employee : employees
        ) {
            if (employee.getEmployeeId() == employeeID) return false;
        }
        return true; // Replace with your actual logic
    }

    // Method to clear input fields after saving
    private void clearInputFields() {
        tf_firstName.clear();
        tf_lastName.clear();
        tf_employeeID.clear();
        tf_email.clear();
        tf_phoneNumber.clear();
        cb_gender.getSelectionModel().clearSelection();
        cb_role.getSelectionModel().clearSelection();
        tf_password.clear();
        cb_department.getSelectionModel().clearSelection();
        dp_dateOfBirth.setValue(null);
        dp_startDate.setValue(null);
        cb_isHourly.setSelected(false);
        tf_salaryPerHour.clear();
        cb_isPerMoth.setSelected(false);
        tf_perMonth.clear();
        tf_salaryToTravel.clear();
        tf_bankNumber.clear();
        tf_accountNumber.clear();
        tf_sneefBankCode.clear();
    }
}



