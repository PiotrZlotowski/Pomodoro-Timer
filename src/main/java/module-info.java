open module Pomodoro.Timer {
    requires controlsfx;
    requires javafx.base;
    requires javafx.controls;
    requires spring.context;
    requires spring.beans;
    requires javafx.fxml;
    requires java.sql;

    exports pz.pomodoro.timer to javafx.graphics;
}