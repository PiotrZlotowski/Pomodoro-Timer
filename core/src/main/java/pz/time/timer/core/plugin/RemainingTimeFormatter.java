package pz.time.timer.core.plugin;

import javafx.scene.control.Label;
import pz.time.timer.core.context.TimerContext;
import pz.timer.api.core.Context;
import pz.timer.api.core.Panel;
import pz.timer.api.plugin.IterationBreak;
import pz.timer.api.plugin.IterationEnd;
import pz.timer.api.plugin.IterationStart;

import java.time.Duration;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * Created by piotr on 07/05/2017.
 */
public class RemainingTimeFormatter extends TimerContextTicker implements IterationEnd<Void>, IterationStart<Void>, IterationBreak<Void> {

    @Override
    public Void performOperation(Context context) {
        TimerContext timerContext = getTimerContext(context);
        long remainingTime = timerContext.getRemainingTime();
        Duration duration = Duration.ofSeconds(remainingTime);
        int minutesPart = duration.toMinutesPart();
        int secondsPart = duration.toSecondsPart();
        String formattedRemainingTime = String.format("%02d:%02d", minutesPart, secondsPart);
        Panel panel = context.getPanel();
        Optional<Label> currentTimeLabel = panel.getComponent("currentTimeLabel", Label.class);
        currentTimeLabel.ifPresent(lbl -> lbl.setText(formattedRemainingTime));
        return null;
    }
}
