package pz.time.timer.TimerTask;

import javafx.application.Platform;
import pz.time.timer.core.context.TimerContext;
import pz.time.timer.core.profile.Profile;
import pz.time.timer.core.timer.AbstractIterationTimer;
import pz.time.timer.core.timer.ProfileTimer;
import pz.timer.api.core.Context;
import pz.timer.api.core.Panel;
import pz.timer.api.core.Timer;

import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by piotr on 07/05/2017.
 */
public class Ticker extends TimerTask {

    private long remainingTime;
    private Timer timer;
    private TimerContext context;
    private AtomicBoolean keepRunning;

    public Ticker(Panel timerPanel, Timer timer) {
        context = createContext(timerPanel);
        keepRunning = new AtomicBoolean(true);
        this.timer = timer;
        remainingTime = Profile.NORMAL_ITERATION.getIterationTime();
    }

    private TimerContext createContext(Panel panel) {
        return new TimerContext(panel);
    }

    @Override
    public void run() {
        if (0 >= remainingTime) {
            keepRunning.set(false);
            return;
        }
        Platform.runLater(this::tick);
    }

    private void tick() {
        remainingTime = timer.tick(context);
    }

    public AtomicBoolean getKeepRunning() {
        return keepRunning;
    }
}
