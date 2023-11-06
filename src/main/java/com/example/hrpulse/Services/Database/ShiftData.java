package com.example.hrpulse.Services.Database;

import javax.persistence.*;

@Entity
@Table(name = "shift_data")
public class ShiftData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String day;
    private String enterHour;
    private String exitHour;
    private String breakTime;
    private String comments;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getEnterHour() {
        return enterHour;
    }

    public void setEnterHour(String enterHour) {
        this.enterHour = enterHour;
    }

    public String getExitHour() {
        return exitHour;
    }

    public void setExitHour(String exitHour) {
        this.exitHour = exitHour;
    }

    public String getBreakTime() {
        return breakTime;
    }

    public void setBreakTime(String breakTime) {
        this.breakTime = breakTime;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }


    // Getters and setters
}

