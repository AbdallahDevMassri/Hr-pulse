package com.example.hrpulse.Services.CSV;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class CsvRow {
    private final StringProperty totalWorkHours;
    private final StringProperty breakTime;
    private final StringProperty exitHour;
    private final StringProperty enterHour;
    private final StringProperty dateTable;
    private final StringProperty employeeId;

    public CsvRow(String totalWorkHours, String breakTime, String exitHour, String enterHour, String dateTable, String employeeId) {
        this.totalWorkHours = new SimpleStringProperty(totalWorkHours);
        this.breakTime = new SimpleStringProperty(breakTime);
        this.exitHour = new SimpleStringProperty(exitHour);
        this.enterHour = new SimpleStringProperty(enterHour);
        this.dateTable = new SimpleStringProperty(dateTable);
        this.employeeId = new SimpleStringProperty(employeeId);
    }

    public String getTotalWorkHours() {
        return totalWorkHours.get();
    }

    public void setTotalWorkHours(String totalWorkHours) {
        this.totalWorkHours.set(totalWorkHours);
    }

    public String getBreakTime() {
        return breakTime.get();
    }

    public void setBreakTime(String breakTime) {
        this.breakTime.set(breakTime);
    }

    public String getExitHour() {
        return exitHour.get();
    }

    public void setExitHour(String exitHour) {
        this.exitHour.set(exitHour);
    }

    public String getEnterHour() {
        return enterHour.get();
    }

    public void setEnterHour(String enterHour) {
        this.enterHour.set(enterHour);
    }

    public String getDateTable() {
        return dateTable.get();
    }

    public void setDateTable(String dateTable) {
        this.dateTable.set(dateTable);
    }

    public String getEmployeeId() {
        return employeeId.get();
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId.set(employeeId);
    }

    // Getters for properties
    public StringProperty totalWorkHoursProperty() {
        return totalWorkHours;
    }

    public StringProperty breakTimeProperty() {
        return breakTime;
    }

    public StringProperty exitHourProperty() {
        return exitHour;
    }

    public StringProperty enterHourProperty() {
        return enterHour;
    }

    public StringProperty dateTableProperty() {
        return dateTable;
    }

    public StringProperty employeeIdProperty() {
        return employeeId;
    }
}
