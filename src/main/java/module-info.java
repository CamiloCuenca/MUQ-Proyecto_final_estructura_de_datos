module project.muq {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires jgrapht.core;



    opens Controller to javafx.fxml;

    exports Controller;
    exports Model.objetos;
    opens Model.objetos to javafx.fxml;
    //opens Controller to javafx.fxml;
}