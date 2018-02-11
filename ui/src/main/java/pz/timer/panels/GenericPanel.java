package pz.timer.panels;

import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.stage.Stage;
import pz.timer.api.core.Panel;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

public class GenericPanel implements Panel {

    Map<String, Object> components = new HashMap<>();

    @Override
    public String getTitle() {
        return getCurrentStage().getTitle();
    }

    @Override
    public void setTitle(String title) {
        getCurrentStage().setTitle(title);
    }

    @Override
    public <T> void addComponent(String name, T component) {
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


    protected Stage getCurrentStage() {
        Optional<Button> btnTimer = getComponent("btnTimer", Button.class);
        return btnTimer.stream().map(btn -> (Stage) btn.getScene().getWindow()).findFirst().orElseThrow(NoSuchElementException::new);
    }

}
