package com.example.hrpulse.Services.CSV;

import com.example.hrpulse.Services.Interfaces.DataModel;
import javax.persistence.*;
import java.util.Arrays;

/**
 * Represents a row of the employee shift information.
 * Implements the DataModel interface.
 */

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

    /**
     * Default constructor.
     */
    public CsvRow() {
        // Default constructor
    }

    /**
     * Parameterized constructor for initializing CsvRow with specified values.
     * Sets the compositeKey based on employeeId and date.
     *
     * @param totalWorkHours The total work hours.
     * @param breakTime      The break time.
     * @param exitHour       The end of shift hour.
     * @param startHour      The start of shift hour.
     * @param date           The date of the shift.
     * @param employeeId     The employee ID.
     */
    public CsvRow(String totalWorkHours, String breakTime, String exitHour, String startHour,
                  String date, String employeeId) {
        initializeFields(totalWorkHours, breakTime, exitHour, startHour, date, employeeId);
        this.compositeKey = employeeId + "_" + date;
    }

    /**
     * Constructor for creating CsvRow from a String array.
     * Initializes fields with values from the array.
     * Prints an error message for invalid CSV row data.
     *
     * @param rowData The String array containing CSV row data.
     */
    public CsvRow(String[] rowData) {
        if (rowData.length >= 5) {
            initializeFields(rowData[0], rowData[1], rowData[2], rowData[3], rowData[4], "");
        } else {
            System.err.println("Invalid CSV row: " + Arrays.toString(rowData));
        }
    }

    /**
     * Constructor for creating CsvRow with specified values and an empty employeeId.
     * Initializes fields and sets the compositeKey based on employeeId and date.
     *
     * @param totalWorkHours The total work hours.
     * @param breakTime      The break time.
     * @param exitHour       The end of shift hour.
     * @param startHour      The start of shift hour.
     * @param date           The date of the shift.
     */
    public CsvRow(String totalWorkHours, String breakTime, String exitHour, String startHour,
                  String date) {
        this();
        initializeFields(totalWorkHours, breakTime, exitHour, startHour, date, "");
    }

    /**
     * Initializes the CsvRow fields with the provided values and sets initial values for tracking changes.
     *
     * @param totalWorkHours The total work hours.
     * @param breakTime      The break time.
     * @param exitHour       The end of shift hour.
     * @param startHour      The start of shift hour.
     * @param dateTable      The date of the shift.
     * @param employeeId     The employee ID.
     */
    private void initializeFields(String totalWorkHours, String breakTime, String exitHour, String startHour,
                                  String dateTable, String employeeId) {
        setTotalWorkHours(totalWorkHours);
        setBreakTime(breakTime);
        setExitHour(exitHour);
        setStartHour(startHour);
        setDate(dateTable);
        setEmployeeId(employeeId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String[] getDataAsArray() {
        return new String[]{totalWorkHours, breakTime, exitHour, startHour, date, employeeId};
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void initializeFromCsvData(String[] data) {
        setTotalWorkHours(data[0]);
        setBreakTime(data[1]);
        setExitHour(data[2]);
        setStartHour(data[3]);
        setDate(data[4]);
        setEmployeeId(data[5]);
    }

    /**
     * Converts the CsvRow to a String array.
     */
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
