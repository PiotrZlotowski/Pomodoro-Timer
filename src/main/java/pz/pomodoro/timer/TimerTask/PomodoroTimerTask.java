package pz.pomodoro.timer.TimerTask;

import javafx.application.Platform;
import pz.pomodoro.timer.model.TimerInterface;
import pz.pomodoro.timer.timer.ITimer;

import java.util.TimerTask;

/**
 * Created by piotr on 07/05/2017.
 */
public class PomodoroTimerTask extends TimerTask {

    private ITimer timer;
    private TimerInterface timerInterface;

    public PomodoroTimerTask(ITimer timer, TimerInterface timerInterface) {
        this.timer = timer;
        this.timerInterface = timerInterface;
    }


    @Override
    public void run() {
        Platform.runLater(this::tick);

    }

    private void tick() {
        timer.tick(timerInterface);
    }
}
