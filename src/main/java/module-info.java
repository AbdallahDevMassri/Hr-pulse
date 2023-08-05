module com.example.hrpulse {
    requires javafx.controls;
    requires javafx.fxml;
    requires opencsv;
    requires org.hibernate.orm.core;
    requires java.sql;
    requires java.mail;


    opens com.example.hrpulse to javafx.fxml;
    exports com.example.hrpulse;

}