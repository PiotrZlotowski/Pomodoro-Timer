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
import pz.time.timer.properties.PomodoroProperties;
import pz.timer.api.core.Panel;
import pz.timer.api.core.PanelAware;
import pz.timer.api.core.ParameterAware;
import pz.time.timer.parameters.CommandLineInput;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicBoolean;

public class TimerController implements ParameterAware<CommandLineInput>, PanelAware {

    @FXML
    private Label lblCurrentTime;
    @FXML
    private Button btnTimer;
    @Autowired
    private ApplicationContext applicationContext;
    private PomodoroProfileFactory pomodoroProfileFactory;
    private PomodoroProperties pomodoroProperties;
    private Timer timer;
    private CommandLineInput commandLineInput;
    private Panel panel;

    public TimerController() {
    }

    public TimerController(PomodoroProperties pomodoroProperties) {
        this.pomodoroProperties = pomodoroProperties;
    }

    public void handleTimerButton(ActionEvent event) {
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
//        timerWatcher.start();

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

    @Override
    public void handleCommandLineParameters(CommandLineInput commandLineInput) {
        this.commandLineInput = commandLineInput;
    }

    @Override
    public Panel getPanel() {
        return panel;
    }

    @Override
    public void setPanel(Panel panel) {
        panel.addComponent("timerButton", btnTimer);
        panel.addComponent("currentTimeLabel", lblCurrentTime);
        this.panel = panel;
    }
}
