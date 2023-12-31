package com.example.hrpulse.Controllers.EmployeeController;

import com.example.hrpulse.HR_Pulse;
import com.example.hrpulse.Services.Interfaces.EmployeeNavigators;
import com.example.hrpulse.Services.Objects.Employee;
import com.example.hrpulse.Session.UserSession;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.hibernate.SessionFactory;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import javafx.scene.control.MultipleSelectionModel;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import static com.example.hrpulse.HR_Pulse.retrieveEmployees;

public class feedBackEmployeeController implements EmployeeNavigators {
    private HR_Pulse hrPulse;
    private SessionFactory sessionFactory;

    public feedBackEmployeeController() {
    }

    public feedBackEmployeeController(HR_Pulse hrPulse) {
        this.hrPulse = hrPulse;
        this.sessionFactory = null;
    }

    public feedBackEmployeeController(HR_Pulse hrPulse, SessionFactory sessionFactory) {
        this.hrPulse = hrPulse;
        this.sessionFactory = sessionFactory;
    }


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
        List<Employee> employeeList= retrieveEmployees();
        List<String> employeeNames = employeeList.stream()
                .map(employee -> employee.getFirstName() + " " +employee.getLastName() +" :" +employee.getEmployeeId())
                .collect(Collectors.toList());

        lst_employee.getItems().addAll(employeeNames);
    }


    @FXML
    void backButton(ActionEvent event) throws IOException {
        navigateToManageEmployeePage(event);
    }

    @FXML
    void sendButtonClicked(ActionEvent event) {
        // get the current user from UserSession
        UserSession userSession = UserSession.getInstance();
        Employee currentUser = userSession.getCurrentUser();
        // Get the selected employee from the list view
        MultipleSelectionModel<String> selectionModel = lst_employee.getSelectionModel();
        ObservableList<String> selectedEmployees = selectionModel.getSelectedItems();

        if (selectedEmployees.isEmpty()) {
            // No employee selected, handle this case accordingly
            System.out.println("Please select an employee.");
            return;
        }
        // Assuming your Employee class has an getEmail() method
        String selectedEmployeeId = selectedEmployees.get(0).split(":")[1].trim();
        String selectedEmployeeEmail = getEmailById(selectedEmployeeId,retrieveEmployees());

        String userInput = label_message.getText();
            String messageContent = "  this is a message from "+currentUser.getFirstName()+" " + currentUser.getLastName()+"\n" + userInput;

        // Send the email using JavaMail
        sendEmail( selectedEmployeeEmail, "Feedback from HR Pulse", messageContent);

        System.out.println(messageContent);
        showConfirmationDialog("The feedback sent successfully");
        System.out.println("Email sent successfully.");
    }

    private void sendEmail( String selectedEmployeeEmail, String feedback_from_hr_pulse, String messageContent) {


        String host = "smtp.gmail.com";
        String username = "massriabdallahdev@gmail.com";
        String password = "fqtc dvkj qqqs tsip";

        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "587");

        Session session = Session.getDefaultInstance(properties,new javax.mail.Authenticator(){
           protected PasswordAuthentication getPasswordAuthentication(){
               return new PasswordAuthentication(username,password);
           }
        });
        session.setDebug(true);
        try{
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress("massriabdallahdev@gmail.com"));
        message.setRecipient(Message.RecipientType.TO,new InternetAddress(selectedEmployeeEmail));
        message.setSubject(feedback_from_hr_pulse);
        message.setText(messageContent);
        Transport.send(message);
        }catch (Exception e){
            System.out.println("the error is : "+e.getMessage());
        }
    }

    private String getEmailById(String selectedEmployeeId, List<Employee> retrieveEmployees) {
        String email="";
        for (Employee value: retrieveEmployees
             ) {
            if(value.getEmployeeId()==Integer.parseInt(selectedEmployeeId))
                email=value.getEmail();
        }
        return email;
    }
    private void showConfirmationDialog(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(" Confirm ");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}
