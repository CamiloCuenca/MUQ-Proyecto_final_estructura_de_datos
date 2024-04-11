module project.muq {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens Controller to javafx.fxml;

    exports Controller;
    //opens Controller to javafx.fxml;
}