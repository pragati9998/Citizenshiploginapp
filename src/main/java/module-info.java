module com.example.mainlogin {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
            requires com.dlsc.formsfx;
                        
    opens com.example.mainlogin to javafx.fxml;
    exports com.example.mainlogin;
}