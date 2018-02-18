package pz.timer.panels;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Optional;

public class TimerPanel extends GenericFxPanel {

    private FXMLLoader loader;

    public TimerPanel(FXMLLoader loader) {
        this.loader = loader;
    }

    @Override
    public Object launch() {
        Object object = null;
        try {
            object = loader.load(getClass().getResourceAsStream("/forms/Timer.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return object;
    }
}
