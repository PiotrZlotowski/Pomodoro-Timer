package pz.time.timer.core.plugin;

import pz.time.timer.core.context.TimerContext;
import pz.timer.api.core.Context;
import pz.timer.api.plugin.TimerTick;

import java.util.concurrent.TimeUnit;

/**
 * Created by piotr on 07/05/2017.
 */
public class TimerTitleKeeper extends TimerContextTicker {

    @Override
    public Void performOperation(Context context) {
        TimerContext timerContext = getTimerContext(context);
        long remainingTime = timerContext.getRemainingTime();

        String.format("%02d:%02d",  TimeUnit.MILLISECONDS.toMinutes(remainingTime) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(remainingTime)),
                TimeUnit.MILLISECONDS.toSeconds(remainingTime) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(remainingTime)));

        return null;
    }
}
