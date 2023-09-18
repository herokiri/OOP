module ru.dstu.sergey.lab1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires javafx.swing;
    requires javafx.media;


    opens ru.dstu.sergey.lab1 to javafx.fxml;
    exports ru.dstu.sergey.lab1;
    exports ru.dstu.sergey.lab1.controllers;
    opens ru.dstu.sergey.lab1.controllers to javafx.fxml;
}