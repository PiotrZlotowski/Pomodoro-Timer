package pz.timer.api.core;

import javafx.scene.control.Control;

import java.util.Optional;

public interface Panel {
    String getTitle();
    void setTitle(String title);
    <T> void addComponent(String name, T component);
    <T extends Control> Optional<T> getComponent(String name, Class<T> clazz);
}
