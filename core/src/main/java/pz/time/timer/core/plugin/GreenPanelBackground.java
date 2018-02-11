package pz.time.timer.core.plugin;

import pz.timer.api.core.Context;
import pz.timer.api.plugin.IterationBreak;
import pz.timer.api.plugin.IterationStart;

public class GreenPanelBackground extends PanelBackgroundColorSwitcher implements IterationBreak<Void> {

    @Override
    protected String getHexColor() {
        return "#4CAF50";
    }

    @Override
    public Void performOperation(Context context) {
        updatePanelBackgroundColor(context.getPanel());
        return null;
    }
}
