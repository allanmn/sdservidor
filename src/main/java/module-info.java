module com.example.sdservidor {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.sql;


    opens com.example.sdservidor to javafx.fxml;
    opens com.example.sdservidor.Controllers to javafx.fxml;
    exports com.example.sdservidor;
}