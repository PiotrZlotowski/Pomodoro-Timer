module pz.time.ui {
    requires javafx.base;
    requires javafx.fxml;
    requires javafx.controls;
    requires spring.beans;
    requires spring.context;
    requires transitive pz.time.core;
    requires pz.time.api;
    exports pz.timer.controller to pz.time.app;
    opens pz.timer.controller to spring.beans, spring.core, javafx.fxml;
    opens forms;
}