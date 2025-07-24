module com.example.javaproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    requires org.controlsfx.controls;
    requires mysql.connector.java;
    requires java.desktop;

    opens com.example.javaproject to javafx.fxml;
    exports com.example.javaproject;

    opens com.example.javaproject.papermaster to javafx.fxml;
    exports com.example.javaproject.papermaster;

    opens com.example.javaproject.hawkers to javafx.fxml;
    exports com.example.javaproject.hawkers;

    opens com.example.javaproject.areamaster to javafx.fxml;
    exports com.example.javaproject.areamaster;

    opens com.example.javaproject.customers to javafx.fxml;
    exports com.example.javaproject.customers;

    opens com.example.javaproject.billing to javafx.fxml;
    exports com.example.javaproject.billing;

    opens com.example.javaproject.billboard to javafx.fxml;
    exports com.example.javaproject.billboard;

    opens com.example.javaproject.billcollect to javafx.fxml;
    exports com.example.javaproject.billcollect;

    opens com.example.javaproject.customerboard to javafx.fxml;
    exports com.example.javaproject.customerboard;

    opens com.example.javaproject.dashboard to javafx.fxml;
    exports com.example.javaproject.dashboard;

    opens com.example.javaproject.analysis to javafx.fxml;
    exports com.example.javaproject.analysis;

    opens com.example.javaproject.adminlogin to javafx.fxml;
    exports com.example.javaproject.adminlogin;


}