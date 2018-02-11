package pz.timer.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import pz.time.timer.TimerTask.Ticker;
import pz.time.timer.core.profile.Profile;
import pz.time.timer.core.timer.AbstractIterationTimer;
import pz.time.timer.core.timer.CommandLineTimer;
import pz.time.timer.core.timer.ProfileTimer;
import pz.time.timer.factory.PomodoroProfileFactory;
import pz.time.timer.model.TimerInterface;
import pz.time.timer.properties.PomodoroProperties;
import pz.timer.api.core.Panel;
import pz.timer.panels.GenericPanel;
import pz.timer.api.core.ParameterAware;
import pz.time.timer.parameters.CommandLineInput;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicBoolean;

public class TimerController implements ParameterAware<CommandLineInput> {

    @FXML
    private Label lblCurrentTime;
    @FXML
    private Button btnTimer;
    private TimerInterface timerInterface;
    @Autowired
    private ApplicationContext applicationContext;
    private PomodoroProfileFactory pomodoroProfileFactory;
    private PomodoroProperties pomodoroProperties;
    private Timer timer;
    private CommandLineInput commandLineInput;

    public TimerController() {
    }

    public TimerController(PomodoroProperties pomodoroProperties) {
        this.pomodoroProperties = pomodoroProperties;
    }

    public void handleTimerButton(ActionEvent event) {
        Panel panel = initializeTimerInterface();
        AbstractIterationTimer iterationTimer = getInitializedTimer();


        TimerTask timerTask = new Ticker(panel, iterationTimer);
        timer = new Timer(true);
        timer.scheduleAtFixedRate(timerTask, 0, 1000);

        Thread timerWatcher = new Thread(() -> {
            while(timer != null) {
                AtomicBoolean keepRunning = ((Ticker) timerTask).getKeepRunning();
                if (!keepRunning.get()) {
                    timer.cancel();
                    timer.purge();
                    timer = null;
                }
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        });
        timerWatcher.setDaemon(true);
        timerWatcher.start();

    }

    private AbstractIterationTimer getInitializedTimer() {
        AbstractIterationTimer iterationTimer;
        if (commandLineInput == null || CommandLineInput.EMPTY_INPUT.equals(commandLineInput)) {
            iterationTimer = new ProfileTimer(Profile.NORMAL_ITERATION);
        }
        else {
            iterationTimer = new CommandLineTimer(commandLineInput.getIterationTime(), commandLineInput.getBreakTime(), commandLineInput.isContinuous());
        }
        return iterationTimer;
    }

    private Panel initializeTimerInterface() {
        Panel timerPanel = new GenericPanel();

        timerPanel.addComponent("timerButton", btnTimer);
        timerPanel.addComponent("currentTimeLabel", lblCurrentTime);

        return timerPanel;
//        if (timerInterface == null) {
//            Stage mainStage = (Stage) btnTimer.getScene().getWindow();
//            Pane pane = (Pane) btnTimer.getParent();
//            timerInterface = new TimerInterface();
//            timerInterface.setStage(mainStage);
//            timerInterface.setPane(pane);
//            timerInterface.setLblCurrentTime(lblCurrentTime);
//            timerInterface.setTimerButton(btnTimer);
//        }
    }

    @Override
    public void handleCommandLineParameters(CommandLineInput commandLineInput) {
        this.commandLineInput = commandLineInput;
    }
}
