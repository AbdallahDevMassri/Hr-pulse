package com.example.hrpulse.Controllers.DepartmentController;

import com.example.hrpulse.Services.Interfaces.Navigators;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

/**
 * The `ManageDepartmentController` class handles navigation related to department management.
 */
public class ManageDepartmentController implements Navigators {

    /**
     * Navigates to the edit department page.
     *
     * @param event The ActionEvent triggered by the user.
     * @throws IOException If an I/O error occurs.
     */
    @FXML
    void navigateToEditDepartment(ActionEvent event) throws IOException {
        navigateEditDepartment(event);
    }

    /**
     * Navigates to the create department page.
     *
     * @param event The ActionEvent triggered by the user.
     * @throws IOException If an I/O error occurs.
     */
    @FXML
    void createDepartmentClicked(ActionEvent event) throws IOException {
        navigateToCreateDepartment(event);
    }

    /**
     * Navigates to the department details page (report of department).
     *
     * @param event The ActionEvent triggered by the user.
     * @throws IOException If an I/O error occurs.
     */
    @FXML
    void navigateToDepartmentDetails(ActionEvent event) throws IOException {
        navigateToReportOfDepartment(event);
    }

    /**
     * Navigates back to the main page.
     *
     * @param event The ActionEvent triggered by the user.
     * @throws IOException If an I/O error occurs.
     */
    @FXML
    void BackToMainClicked(ActionEvent event) throws IOException {
        navigateToMainPage(event);
    }
}
