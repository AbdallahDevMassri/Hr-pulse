package com.example.hrpulse.Services.Database;

import com.example.hrpulse.Services.Database.ShiftData;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ShiftDataModel {
    private final StringProperty day;
    private final StringProperty enterHour;
    private final StringProperty exitHour;
    private final StringProperty breakTime;
    private final StringProperty comments;

    public ShiftDataModel(ShiftData shiftData) {
        this.day = new SimpleStringProperty(shiftData.getDay());
        this.enterHour = new SimpleStringProperty(shiftData.getEnterHour());
        this.exitHour = new SimpleStringProperty(shiftData.getExitHour());
        this.breakTime = new SimpleStringProperty(shiftData.getBreakTime());
        this.comments = new SimpleStringProperty(shiftData.getComments());
    }

    public String getDay() {
        return day.get();
    }

    public StringProperty dayProperty() {
        return day;
    }

    public void setDay(String day) {
        this.day.set(day);
    }

    public String getEnterHour() {
        return enterHour.get();
    }

    public StringProperty enterHourProperty() {
        return enterHour;
    }

    public void setEnterHour(String enterHour) {
        this.enterHour.set(enterHour);
    }

    public String getExitHour() {
        return exitHour.get();
    }

    public StringProperty exitHourProperty() {
        return exitHour;
    }

    public void setExitHour(String exitHour) {
        this.exitHour.set(exitHour);
    }

    public String getBreakTime() {
        return breakTime.get();
    }

    public StringProperty breakTimeProperty() {
        return breakTime;
    }

    public void setBreakTime(String breakTime) {
        this.breakTime.set(breakTime);
    }

    public String getComments() {
        return comments.get();
    }

    public StringProperty commentsProperty() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments.set(comments);
    }
}
