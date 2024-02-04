package com.example.hrpulse.Controllers.EmployeeController;

import com.example.hrpulse.Services.Database.DatabaseManager;
import com.example.hrpulse.Services.Interfaces.EmployeeNavigators;
import com.example.hrpulse.Services.Objects.Employee;
import com.example.hrpulse.Session.UserSession;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.hibernate.SessionFactory;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

import static com.example.hrpulse.Services.Database.DatabaseManager.retrieveEmployees;


/**
 * The `feedBackEmployeeController` class manages the user interface for providing feedback to employees.
 */
public class feedBackEmployeeController implements EmployeeNavigators {

    // Constructors to allow for different ways of initializing the controller
    private DatabaseManager databaseManager;
    private SessionFactory sessionFactory;

    @FXML
    private Button backButtonClicked;

    @FXML
    private ListView<String> lst_employee;

    @FXML
    private TextField label_message;

    @FXML
    private Button send_button;

    @FXML
    public void initialize() {
        // Initialization logic for the controller
        List<Employee> employeeList = retrieveEmployees();
        List<String> employeeNames = employeeList.stream()
                .map(employee -> employee.getFirstName() + " " + employee.getLastName() + " :" + employee.getEmployeeId())
                .collect(Collectors.toList());

        lst_employee.getItems().addAll(employeeNames);
    }

    @FXML
    void backButton(ActionEvent event) throws IOException {
        // Method to handle the back button click
        navigateToManageEmployeePage(event);
    }

    @FXML
    void sendButtonClicked(ActionEvent event) {
        // Method to handle sending feedback
        // get the current user from UserSession
        UserSession userSession = UserSession.getInstance();
        Employee currentUser = userSession.getCurrentUser();
        // Get the selected employee from the list view
        MultipleSelectionModel<String> selectionModel = lst_employee.getSelectionModel();
        ObservableList<String> selectedEmployees = selectionModel.getSelectedItems();

        if (selectedEmployees.isEmpty()) {
            // No employee selected, handle this case accordingly
            showConfirmationDialog("אנה בחר עובד כדי לשלוח אימייל");
            System.out.println("אנה בחר עובד");
            return;
        }
        // Assuming your Employee class has an getEmail() method
        String selectedEmployeeId = selectedEmployees.get(0).split(":")[1].trim();
        String selectedEmployeeEmail = getEmailById(selectedEmployeeId, retrieveEmployees());

        String userInput = label_message.getText();
        String messageContent = "  this is a message from " + currentUser.getFirstName() + " " + currentUser.getLastName() + "\n" + userInput;

        // Send the email using JavaMail
        sendEmail(selectedEmployeeEmail, "Feedback from HR Pulse", messageContent);

        System.out.println(messageContent);
        showConfirmationDialog("המשוב נשלח בהצלחה.");
        System.out.println("האימייל נשלח בהצלחה");
    }

    private void sendEmail(String selectedEmployeeEmail, String feedback_from_hr_pulse, String messageContent) {
        // Method to send an email using JavaMail
        String host = "smtp.gmail.com";
        String username = "massriabdallahdev@gmail.com";
        String password = "fqtc dvkj qqqs tsip";

        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "587");

        Session session = Session.getDefaultInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
        session.setDebug(true);
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("massriabdallahdev@gmail.com"));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(selectedEmployeeEmail));
            message.setSubject(feedback_from_hr_pulse);
            message.setText(messageContent);
            Transport.send(message);
        } catch (Exception e) {
            System.out.println("שגיאה : " + e.getMessage());
        }
    }

    private String getEmailById(String selectedEmployeeId, List<Employee> retrieveEmployees) {
        // Method to get an employee's email by their ID
        String email = "";
        for (Employee value : retrieveEmployees
        ) {
            if (value.getEmployeeId() == Integer.parseInt(selectedEmployeeId))
                email = value.getEmail();
        }
        return email;
    }

    private void showConfirmationDialog(String message) {
        // Helper method to display a confirmation dialog
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(" אישור או ביטול");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
