module com.example.hrpulse {
    requires javafx.controls;
    requires javafx.fxml;
    requires opencsv;
    requires org.hibernate.orm.core;
    requires java.sql;
    requires java.mail;
    requires java.naming;
    requires java.persistence;
    requires jasperreports;


    opens com.example.hrpulse to javafx.fxml;
    exports com.example.hrpulse;
    exports com.example.hrpulse.Controllers.RoleControllers.HeadOfDepartment;
    opens com.example.hrpulse.Controllers.RoleControllers.HeadOfDepartment to javafx.fxml;
    exports com.example.hrpulse.Controllers.RoleControllers.Manager;
    opens com.example.hrpulse.Controllers.RoleControllers.Manager to javafx.fxml;
    exports com.example.hrpulse.Controllers.RoleControllers.Secreteriat;
    opens com.example.hrpulse.Controllers.RoleControllers.Secreteriat to javafx.fxml;
    exports com.example.hrpulse.Controllers;
    opens com.example.hrpulse.Controllers to javafx.fxml;
    exports com.example.hrpulse.Controllers.DepartmentController;
    opens com.example.hrpulse.Controllers.DepartmentController to javafx.fxml;
    exports com.example.hrpulse.Controllers.EmployeeController;
    opens    com.example.hrpulse.Controllers.EmployeeController to javafx.fxml;
    exports com.example.hrpulse.Controllers.ReportsControllers;
    opens com.example.hrpulse.Controllers.ReportsControllers to javafx.fxml;
    opens com.example.hrpulse.Services.Objects to org.hibernate.orm.core;
    opens com.example.hrpulse.Services.CSV;


}