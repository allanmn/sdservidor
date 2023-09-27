module com.example.sdservidor {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.sql;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;
    requires jjwt.api;
    requires jbcrypt;
    requires java.naming;
    requires java.persistence;
    requires org.hibernate.orm.core;

    opens com.example.sdservidor to javafx.fxml;
    opens com.example.sdservidor.Controllers to javafx.fxml;
    opens com.example.sdservidor.Models to org.hibernate.orm.core;
    exports com.example.sdservidor.Senders.Data to com.fasterxml.jackson.databind;
    exports com.example.sdservidor.Senders to com.fasterxml.jackson.databind;
    exports com.example.sdservidor.Receivers.Data to com.fasterxml.jackson.databind;
    exports com.example.sdservidor.Receivers to com.fasterxml.jackson.databind;
    exports com.example.sdservidor;
}