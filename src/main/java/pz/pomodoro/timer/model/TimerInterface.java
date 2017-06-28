package pz.pomodoro.timer.model;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * Created by piotr on 23.04.2017.
 */
public class TimerInterface {
    private Label lblCurrentTime;
    private Button timerButton;
    private Stage stage;
    private Pane pane;

    public TimerInterface() {
    }

    public TimerInterface(Label lblCurrentTime, Button timerButton) {
        this.lblCurrentTime = lblCurrentTime;
        this.timerButton = timerButton;
    }

    public TimerInterface(Label lblCurrentTime, Button timerButton, Stage stage) {
        this.lblCurrentTime = lblCurrentTime;
        this.timerButton = timerButton;
        this.stage = stage;
    }

    public TimerInterface(Label lblCurrentTime, Button timerButton, Stage stage, Pane pane) {
        this.lblCurrentTime = lblCurrentTime;
        this.timerButton = timerButton;
        this.stage = stage;
        this.pane = pane;
    }

    public Label getLblCurrentTime() {
        return lblCurrentTime;
    }

    public Button getTimerButton() {
        return timerButton;
    }

    public Stage getStage() {
        return stage;
    }

    public Pane getPane() {
        return pane;
    }


    public void setLblCurrentTime(Label lblCurrentTime) {
        this.lblCurrentTime = lblCurrentTime;
    }

    public void setTimerButton(Button timerButton) {
        this.timerButton = timerButton;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setPane(Pane pane) {
        this.pane = pane;
    }
}
