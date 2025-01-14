module com.assignment.service.mds2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.jfoenix;
    requires java.sql;
    requires java.desktop;


    opens com.assignment.service.mds2 to javafx.fxml;
    opens com.assignment.service.mds2.controller to javafx.fxml;
    exports com.assignment.service.mds2;
}