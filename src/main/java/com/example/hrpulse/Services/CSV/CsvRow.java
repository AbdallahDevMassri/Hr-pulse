package com.example.hrpulse.Services.CSV;

import com.example.hrpulse.Services.Interfaces.DataModel;
import javax.persistence.*;
import java.util.Arrays;


@Entity
@Table(name = "employeeshiftdata")
public class CsvRow implements DataModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "compositeKey")
    private String compositeKey;

    @Column(name = "total_work_hours")
    private String totalWorkHours;

    @Column(name = "break_time")
    private String breakTime;

    @Column(name = "end_of_shift")
    private String exitHour;

    @Column(name = "start_of_shift")
    private String startHour;

    @Column(name = "date")
    private String date;

    @Column(name = "employee_id", unique = true)  // Ensure uniqueness
    private String employeeId;


    @Transient
    private String initialTotalWorkHours;

    @Transient
    private String initialBreakTime;

    @Transient
    private String initialExitHour;

    @Transient
    private String initialStartHour;

    @Transient
    private String initialDateTable;

    @Transient
    private String initialEmployeeId;

    @Transient
    private String initialComments;


    public CsvRow() {
        // Default constructor
    }

    public CsvRow(String totalWorkHours, String breakTime, String exitHour, String startHour,
                  String date, String employeeId) {
        initializeFields(totalWorkHours, breakTime, exitHour, startHour, date, employeeId);
        this.compositeKey = employeeId + "_" + date;
    }

    public CsvRow(String[] rowData) {
        if (rowData.length >= 5) {
            initializeFields(
                    rowData[0], rowData[1], rowData[2], rowData[3], rowData[4], "");
        } else {
            System.err.println("Invalid CSV row: " + Arrays.toString(rowData));
        }
    }

    public CsvRow(String totalWorkHours, String breakTime, String exitHour, String startHour,
                  String date) {
        this();
        initializeFields(totalWorkHours, breakTime, exitHour, startHour, date, "");
    }

    private void initializeFields(String totalWorkHours, String breakTime, String exitHour, String startHour,
                                  String dateTable, String employeeId) {
        setTotalWorkHours(totalWorkHours);
        setBreakTime(breakTime);
        setExitHour(exitHour);
        setStartHour(startHour);
        setDate(dateTable);
        setEmployeeId(employeeId);
        initializeInitialValues();
    }

    private void initializeInitialValues() {
        initialTotalWorkHours = getTotalWorkHours();
        initialBreakTime = getBreakTime();
        initialExitHour = getExitHour();
        initialStartHour = getStartHour();
        initialDateTable = getDate();
        initialEmployeeId = getEmployeeId();
    }

    @Override
    public String[] getDataAsArray() {
        return new String[]{totalWorkHours, breakTime, exitHour, startHour, date, employeeId };
    }

    @Override
    public void initializeFromCsvData(String[] data) {
        setTotalWorkHours(data[0]);
        setBreakTime(data[1]);
        setExitHour(data[2]);
        setStartHour(data[3]);
        setDate(data[4]);
        setEmployeeId(data[5]);
    }

    public String[] toArray() {
        return new String[]{
                totalWorkHours,
                breakTime,
                exitHour,
                startHour,
                date,
                employeeId,
        };
    }



    public String getCompositeKey() {
        return employeeId + "_" + date;
    }

    public void setCompositeKey(String compositeKey) {
        this.compositeKey = compositeKey;
    }

    public String getTotalWorkHours() {
        return totalWorkHours;
    }

    public void setTotalWorkHours(String totalWorkHours) {
        this.totalWorkHours = totalWorkHours;
    }

    public String getBreakTime() {
        return breakTime;
    }

    public void setBreakTime(String breakTime) {
        this.breakTime = breakTime;
    }

    public String getExitHour() {
        return exitHour;
    }

    public void setExitHour(String exitHour) {
        this.exitHour = exitHour;
    }

    public String getStartHour() {
        return startHour;
    }

    public void setStartHour(String startHour) {
        this.startHour = startHour;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


}
