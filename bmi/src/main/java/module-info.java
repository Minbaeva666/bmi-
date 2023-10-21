module com.example.bmi {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.bmi to javafx.fxml;
    exports com.example.bmi;
}