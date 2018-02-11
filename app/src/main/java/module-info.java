module pz.time.app {
    requires commons.cli;
    requires spring.context;
    requires javafx.fxml;
    requires javafx.graphics;
    requires pz.time.ui;
    requires transitive pz.time.core;
    exports pz.app.launcher to javafx.graphics;
}