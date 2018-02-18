package pz.timer.panels;

import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.stage.Stage;
import pz.timer.api.core.Panel;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

public abstract class GenericFxPanel implements Panel {

    Map<String, Control> components = new HashMap<>();

    @Override
    public String getTitle() {
        return getCurrentStage().getTitle();
    }

    @Override
    public void setTitle(String title) {
        getCurrentStage().setTitle(title);
    }

    @Override
    public <T extends Control> void addComponent(String name, T component) {
        if (name == null || name.isEmpty()) {
            return;
        }
        components.put(name, component);
    }

    @Override
    public <T extends Control> Optional<T> getComponent(String name, Class<T> clazz) {
        if (name == null || name.isEmpty()) {
            return Optional.empty();
        }
        return Optional.ofNullable((T) components.get(name));
    }


    private Stage getCurrentStage() {
        return components.values().stream().map(component -> (Stage)component.getScene().getWindow()).findFirst().orElseThrow(NoSuchElementException::new);
    }
}
