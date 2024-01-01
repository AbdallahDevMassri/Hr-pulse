package com.example.hrpulse.Services.CSV;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

    public class CSVData {
        private final StringProperty comments = new SimpleStringProperty();
        private final StringProperty breakTime = new SimpleStringProperty();
        private final StringProperty exitHour = new SimpleStringProperty();
        private final StringProperty enterHour = new SimpleStringProperty();
        private final StringProperty day = new SimpleStringProperty();

        public CSVData() {
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

        public String getBreakTime() {
            return breakTime.get();
        }

        public StringProperty breakTimeProperty() {
            return breakTime;
        }

        public void setBreakTime(String breakTime) {
            this.breakTime.set(breakTime);
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

        public String getEnterHour() {
            return enterHour.get();
        }

        public StringProperty enterHourProperty() {
            return enterHour;
        }

        public void setEnterHour(String enterHour) {
            this.enterHour.set(enterHour);
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




    }



