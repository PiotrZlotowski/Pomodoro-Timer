package pz.pomodoro.timer.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import pz.pomodoro.timer.TimerTask.PomodoroTimerTask;
import pz.pomodoro.timer.factory.PomodoroProfileFactory;
import pz.pomodoro.timer.model.TimerInterface;
import pz.pomodoro.timer.properties.PomodoroProperties;
import pz.pomodoro.timer.timer.AbstractPomodoro;
import pz.pomodoro.timer.timer.SpringPomodoro;

import java.util.Timer;
import java.util.TimerTask;

public class TimerController {

    @FXML
    private Label lblCurrentTime;
    @FXML
    private Button btnTimer;
    private TimerInterface timerInterface;
    private AbstractPomodoro pomodoro;
    private Timer timer;
    @Autowired
    private ApplicationContext applicationContext;
    private PomodoroProfileFactory pomodoroProfileFactory;
    private PomodoroProperties pomodoroProperties;


    public TimerController(PomodoroProfileFactory pomodoroProfileFactory, PomodoroProperties pomodoroProperties) {
        this.pomodoroProfileFactory = pomodoroProfileFactory;
        this.pomodoroProperties = pomodoroProperties;
    }

    public void handleTimerButton(ActionEvent event) {
        initializeTimerInterface();
        AbstractPomodoro pomodoro = pomodoroProfileFactory.forProfileName(pomodoroProperties.getProfile());
        TimerTask timerTask = new PomodoroTimerTask(pomodoro, timerInterface);
        timer = new Timer(true);
        timer.scheduleAtFixedRate(timerTask, 0, 1);
    }

    private void initializeTimerInterface() {
        if (timerInterface == null) {
            Stage mainStage = (Stage) btnTimer.getScene().getWindow();
            Pane pane = (Pane) btnTimer.getParent();
            timerInterface = new TimerInterface();
            timerInterface.setStage(mainStage);
            timerInterface.setPane(pane);
            timerInterface.setLblCurrentTime(lblCurrentTime);
            timerInterface.setTimerButton(btnTimer);
        }
    }

}
