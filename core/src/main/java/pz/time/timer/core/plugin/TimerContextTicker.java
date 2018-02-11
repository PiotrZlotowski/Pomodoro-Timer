package pz.time.timer.core.plugin;

import pz.time.timer.core.context.TimerContext;
import pz.timer.api.core.Context;
import pz.timer.api.plugin.TimerTick;

public abstract class TimerContextTicker implements TimerTick<Void> {
    
    protected TimerContext getTimerContext(Context context) {
        TimerContext timerContext = null;
        if (context instanceof TimerContext) {
            timerContext = (TimerContext) context;
        }
        return timerContext;
    }


}
