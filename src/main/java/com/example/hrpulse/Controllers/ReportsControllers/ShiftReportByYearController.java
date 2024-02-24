package com.example.hrpulse.Controllers.ReportsControllers;

import com.example.hrpulse.Services.Database.DatabaseManager;
import com.example.hrpulse.Services.Interfaces.ReportsNavigators;
import com.example.hrpulse.Services.Objects.Employee;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import org.hibernate.SessionFactory;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.util.List;

import static com.example.hrpulse.Services.Database.DatabaseManager.retrieveEmployees;

public class ShiftReportByYearController implements ReportsNavigators {
    private DatabaseManager databaseManager;
    private SessionFactory sessionFactory;

    public ShiftReportByYearController() {
    }

    public ShiftReportByYearController(DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
        this.sessionFactory = null;
    }

    public ShiftReportByYearController(DatabaseManager databaseManager, SessionFactory sessionFactory) {
        this.databaseManager = databaseManager;
        this.sessionFactory = sessionFactory;
    }

    @FXML
    private ChoiceBox<Integer> cb_yearSelect;
    @FXML
    private ListView<Employee> lv_retrieveEmployees;
    private Integer selectedYear;
    private Employee selectedEmployee;
    private ObservableList<Employee> employeeObservableList;
    @FXML
    public void initialize() {
       int currentYear = LocalDate.now().getYear();
        // Populate the ChoiceBox with months
        ObservableList<Integer> year = FXCollections.observableArrayList(currentYear-1,currentYear-2,
                currentYear-3,currentYear-4,currentYear-5,
                currentYear-6,currentYear-7,currentYear-8,
                currentYear-9,currentYear-10,currentYear-11,currentYear-12);
        cb_yearSelect.setItems(year);
        // Initialize the employee list
        List<Employee> employeeList = retrieveEmployees();
        employeeObservableList = FXCollections.observableArrayList(employeeList);

        // Set up the ListView to display the employee details
        lv_retrieveEmployees.setItems(employeeObservableList);
        lv_retrieveEmployees.setCellFactory(param -> new ListCell<Employee>() {
            @Override
            protected void updateItem(Employee item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    GridPane gridPane = new GridPane();
                    gridPane.setHgap(10);

                    Label firstNameLabel = new Label("שם פרטי:");
                    Label lastNameLabel = new Label("שם משפחה:");
                    Label idLabel = new Label("ת.ז:");

                    Label firstNameValue = new Label(item.getFirstName());
                    Label lastNameValue = new Label(item.getLastName());
                    Label idValue = new Label(String.valueOf(item.getEmployeeId()));

                    gridPane.addColumn(0, firstNameLabel, lastNameLabel, idLabel);
                    gridPane.addColumn(1, firstNameValue, lastNameValue, idValue);

                    setText(null);
                    setGraphic(gridPane);
                }
            }
        });
        // Set up the layout direction to right-to-left
        lv_retrieveEmployees.setStyle("-fx-alignment: CENTER-LEFT;");

        // Set up the selection model for the ListView
        lv_retrieveEmployees.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            selectedEmployee = newValue;
        });

        // Set up the selection model for the ChoiceBox
        cb_yearSelect.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            selectedYear = newValue;
        });
    }
    @FXML
    void back_btn(ActionEvent event) throws IOException {
        navigateToProductionOfReportsPage(event);
    }
    //TODO: Show list need to implement the method that get the employee shift by year
    @FXML
    void showListClicked(ActionEvent event) {
        if (selectedYear == null || selectedEmployee == null) {
            showAlert("אנא בחר עובד  או שנה בכדי להמשיך");
            return;
        }
        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            // Establish a connection to the MySQL database
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/pulsedb", "root", "hrpulse123");

            // Path to the JR XML file (JasperReports XML template)
            String reportPath = "YearlyShiftReport.jrxml";

            JasperDesign jd = JRXmlLoader.load(reportPath);
            Integer employeeId = selectedEmployee.getEmployeeId();

            // Modified SQL query to include the selected year
            String sql = "SELECT e.employee_id AS employee_id, " +
                    "e.first_name, " +
                    "e.last_name, " +
                    "STR_TO_DATE(esd.date, '%d/%m/%Y') AS formatted_date, " +
                    "esd.start_of_shift, " +
                    "esd.end_of_shift, " +
                    "esd.total_work_hours " +
                    "FROM employees e " +
                    "JOIN employeeshiftdata esd ON e.employee_id = esd.employee_id " +
                    "WHERE e.employee_id = ? AND YEAR(STR_TO_DATE(esd.date, '%d/%m/%Y')) = ?";


            // Using prepared statement to avoid SQL injection
            try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
                pstmt.setInt(1, employeeId);
                pstmt.setInt(2, selectedYear);

                // Execute the query
                ResultSet resultSet = pstmt.executeQuery();


                JRDesignQuery newQuery = new JRDesignQuery();
                newQuery.setText(sql);
                jd.setQuery(newQuery);

                // Compile the JRXML file into a JasperReport object
                JasperReport jr = JasperCompileManager.compileReport(jd);

                // Fill the report with data and create a JasperPrint object
                JasperPrint jp = JasperFillManager.fillReport(jr, null, new JRResultSetDataSource(resultSet));

                // View the JasperPrint object using JasperViewer with 50% zoom
                JasperViewer viewer = new JasperViewer(jp, false);
                viewer.setZoomRatio(0.5f); // Set zoom level to 50%
                viewer.setVisible(true);
                // Close the database connection
                connection.close();
            }
        } catch (ClassNotFoundException | SQLException | JRException e) {
            throw new RuntimeException(e);
        }
    }
    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR, message, ButtonType.OK);
        alert.showAndWait();
    }
}
