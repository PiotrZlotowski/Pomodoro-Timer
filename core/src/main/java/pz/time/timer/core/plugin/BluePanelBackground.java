package pz.time.timer.core.plugin;

import pz.timer.api.core.Context;
import pz.timer.api.plugin.IterationStart;

public class BluePanelBackground extends PanelBackgroundColorSwitcher implements IterationStart<Void> {
    @Override
    protected String getHexColor() {
        return "#3F51B5";
    }

    @Override
    public Void performOperation(Context context) {
        updatePanelBackgroundColor(context.getPanel());
        return null;
    }
}
