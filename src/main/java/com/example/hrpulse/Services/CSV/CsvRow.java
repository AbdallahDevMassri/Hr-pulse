package com.example.hrpulse.Services.CSV;

import com.example.hrpulse.Services.Interfaces.DataModel;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "employeeshiftdata")
public class CsvRow implements DataModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
    private String dateTable;

    @Column(name = "employee_id", unique = true)  // Ensure uniqueness
    private String employeeId;

    @Transient
    private String timestamp;

    @Column(name = "last_editor")
    private String lastEditor;

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

    @Column(name = "comments")
    private String comments;

    public CsvRow() {
        // Default constructor
    }

    public CsvRow(String totalWorkHours, String breakTime, String exitHour, String startHour,
                  String dateTable, String employeeId, String comments, String lastEditor) {
        initializeFields(totalWorkHours, breakTime, exitHour, startHour, dateTable, employeeId, comments, lastEditor);
        this.compositeKey = employeeId + "_" + dateTable;

    }

    public CsvRow(String[] rowData) {
        if (rowData.length >= 6) {
            initializeFields(
                    rowData[0], rowData[1], rowData[2], rowData[3], rowData[4], "", "", ""
            );
        } else {
            System.err.println("Invalid CSV row: " + Arrays.toString(rowData));
        }
    }

    public CsvRow(String totalWorkHours, String breakTime, String exitHour, String startHour,
                  String dateTable, String comments, String lastEditor) {
        this();
        initializeFields(totalWorkHours, breakTime, exitHour, startHour, dateTable, "", comments, lastEditor);
    }

    private void initializeFields(String totalWorkHours, String breakTime, String exitHour, String startHour,
                                  String dateTable, String employeeId, String comments, String lastEditor) {
        setTotalWorkHours(totalWorkHours);
        setBreakTime(breakTime);
        setExitHour(exitHour);
        setStartHour(startHour);
        setDateTable(dateTable);
        setEmployeeId(employeeId);
        setComments((comments != null) ? comments : "");
        setLastEditor(lastEditor);
        setTimestamp(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        initializeInitialValues();
    }
    private void initializeInitialValues() {
        initialTotalWorkHours = getTotalWorkHours();
        initialBreakTime = getBreakTime();
        initialExitHour = getExitHour();
        initialStartHour = getStartHour();
        initialDateTable = getDateTable();
        initialEmployeeId = getEmployeeId();
        initialComments = getComments() != null ? getComments() : "";
    }


    public String[] toStringArray() {
        return new String[]{
                this.getTotalWorkHours(),
                this.getBreakTime(),
                this.getExitHour(),
                this.getStartHour(),
                this.getDateTable(),
                this.getEmployeeId(),
                this.getComments(),
                this.getLastEditor()
        };
    }

    @Override
    public String[] getDataAsArray() {
        return new String[]{totalWorkHours, breakTime, exitHour, startHour, dateTable, employeeId, comments, lastEditor};
    }

    @Override
    public void initializeFromCsvData(String[] data) {
        setTotalWorkHours(data[0]);
        setBreakTime(data[1]);
        setExitHour(data[2]);
        setStartHour(data[3]);
        setDateTable(data[4]);
        setEmployeeId(data[5]);
        setComments(data[6]);
    }

    public boolean isDirty() {
        return !getTotalWorkHours().equals(initialTotalWorkHours) ||
                !getBreakTime().equals(initialBreakTime) ||
                !getExitHour().equals(initialExitHour) ||
                !getStartHour().equals(initialStartHour) ||
                !getDateTable().equals(initialDateTable) ||
                !getEmployeeId().equals(initialEmployeeId) ||
                (!Objects.equals(getComments(), initialComments) && !"הערה".equals(initialComments));
    }



    public String getCompositeKey() {
        return employeeId + "_" + dateTable;
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

    public String getDateTable() {
        return dateTable;
    }

    public void setDateTable(String dateTable) {
        this.dateTable = dateTable;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }


    public String getLastEditor() {
        return lastEditor;
    }

    public void setLastEditor(String lastEditor) {
        this.lastEditor = lastEditor;
    }




    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
