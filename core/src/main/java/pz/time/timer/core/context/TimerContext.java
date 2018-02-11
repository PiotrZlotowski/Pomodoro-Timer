package pz.time.timer.core.context;

import pz.timer.api.core.Context;
import pz.timer.api.core.Panel;

public class TimerContext extends Context {

    private Panel panel;
    private long remainingTime;

    public TimerContext(Panel panel) {
        super(panel);
        this.panel = panel;
    }

    public void updateTime(long remainingTime) {
        this.remainingTime = remainingTime;
    }

    public long getRemainingTime() {
        return remainingTime;
    }


}
