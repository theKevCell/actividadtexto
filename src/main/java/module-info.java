module org.example.text {
    requires javafx.controls;
    requires javafx.fxml;

    opens org.example.text.controller to javafx.fxml;
    opens org.example.text to javafx.fxml;
    exports org.example.text;
}