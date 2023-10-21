package com.example.hrpulse.Controllers.DepartmentController;

import com.example.hrpulse.HR_Pulse;
import com.example.hrpulse.Services.Interfaces.Navigators;
import com.example.hrpulse.Services.Objects.Department;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import org.hibernate.SessionFactory;

import java.io.IOException;
import java.util.List;

import static com.example.hrpulse.HR_Pulse.retrieveDepartments;

public class ReportDepartmentController implements Navigators {

    private HR_Pulse hrPulse;
    private SessionFactory sessionFactory;

    public ReportDepartmentController() {
    }

    public ReportDepartmentController(HR_Pulse hrPulse) {
        this.hrPulse = hrPulse;
        this.sessionFactory = null;
    }

    public ReportDepartmentController(HR_Pulse hrPulse, SessionFactory sessionFactory) {
        this.hrPulse = hrPulse;
        this.sessionFactory = sessionFactory;
    }

    private List<Department> departmens;

    @FXML
    private ListView<Department> lv_departments;
    @FXML
    private Button viewDetailsButton;

    @FXML
    private Button countEmployeesButton;
    private ObservableList<Department> departmentList = FXCollections.observableArrayList();
    public void initialize() {
        departmens= retrieveDepartments(); // import the static method from HR_PULSE
        departmentList.addAll(departmens);
        lv_departments.setItems(departmentList);
    }



    @FXML
    void backToManageDepartment(ActionEvent event) throws IOException {
        navigateToManageDepartment(event);

    }


}
