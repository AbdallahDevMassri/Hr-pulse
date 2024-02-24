module com.example.hrpulse {
    requires javafx.controls;
    requires javafx.fxml;
    requires opencsv;
    requires org.hibernate.orm.core;
    requires java.sql;
    requires java.mail;
<<<<<<< HEAD
    requires java.persistence;
    requires java.naming;
    requires jakarta.persistence;
=======
    requires java.naming;
    requires java.persistence;
>>>>>>> 8452ff04666fa1a352d6dd4e284d1b1d09392159


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
    opens com.example.hrpulse.Services.Objects to org.hibernate.orm.core;

}