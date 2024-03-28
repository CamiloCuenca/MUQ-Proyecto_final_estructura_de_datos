module project.muq {
    requires javafx.controls;
    requires javafx.fxml;


    opens project.muq to javafx.fxml;

    exports Controller;
    opens Controller to javafx.fxml;
}