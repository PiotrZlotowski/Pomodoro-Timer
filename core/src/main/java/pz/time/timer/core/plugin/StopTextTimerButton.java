package pz.time.timer.core.plugin;

import pz.timer.api.core.Context;
import pz.timer.api.plugin.IterationStart;

public class StopTextTimerButton extends TimerButtonKeeper implements IterationStart<Void> {
    @Override
    protected String getText() {
        return "Stop";
    }

    @Override
    public Void performOperation(Context context) {
        updateButtonText(context.getPanel());
        return null;
    }
}
