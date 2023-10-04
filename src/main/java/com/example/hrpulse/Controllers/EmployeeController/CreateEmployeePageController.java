package com.example.hrpulse.Controllers.EmployeeController;

import com.example.hrpulse.HR_Pulse;
import com.example.hrpulse.Services.CSV.CsvService;
import com.example.hrpulse.Services.Interfaces.EmployeeNavigators;
import com.example.hrpulse.Services.Objects.Employee;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class CreateEmployeePageController implements EmployeeNavigators {

        private  HR_Pulse hrPulse;
        // Inject the SessionFactory
        private  SessionFactory sessionFactory;

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
        @FXML
        private ChoiceBox<String> cb_department;

        @FXML
        private ChoiceBox<String> cb_gender;

        @FXML
        private CheckBox cb_isHourly;

        @FXML
        private CheckBox cb_isPerMoth;

        @FXML
        private ChoiceBox<String> cb_role;

        @FXML
        private ChoiceBox<String> cb_status;

        @FXML
        private TextField id;

        @FXML
        private TextField tf_Password;

        @FXML
        private TextField tf_acountNumber;

        @FXML
        private TextField tf_bankNumber;

        @FXML
        private TextField tf_dateOFBirth;

        @FXML
        private TextField tf_email;

        @FXML
        private TextField tf_employeeID;

        @FXML
        private TextField tf_firstName;

        @FXML
        private TextField tf_lastName;

        @FXML
        private TextField tf_phoneNumber;

        @FXML
        private TextField tf_salaryPerHour;

        @FXML
        private TextField tf_salaryToTravel;

        @FXML
        private TextField tf_sneefBankCode;
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
                String phoneNumber = tf_phoneNumber.getText();
                String email = tf_email.getText();
                String gender = cb_gender.getValue();
                String role = cb_role.getValue();
                String department = cb_department.getValue();
                String status = cb_status.getValue();

                // Parse date of birth
                Date dateOfBirth = null;
                try {
                        String dobText = tf_dateOFBirth.getText();
                        if (!dobText.isEmpty()) {
                                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                                dateOfBirth = dateFormat.parse(dobText);
                        }
                } catch (ParseException e) {
                        showErrorDialog("Invalid date of birth format. Use yyyy-MM-dd.");
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
                employee.setStatus(Boolean.parseBoolean(status));
                employee.setDateOfBirth(dateOfBirth);

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




