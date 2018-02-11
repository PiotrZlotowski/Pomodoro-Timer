package pz.time.timer.core.plugin;

import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import pz.timer.api.core.Panel;

public abstract class PanelBackgroundColorSwitcher {

    void updatePanelBackgroundColor(Panel panel) {
        StringBuilder style = new StringBuilder();
        style.append("-fx-background-color:").append(" ").append(getHexColor());
        panel.getComponent("timerButton", Button.class).ifPresent(btn -> ((Pane) btn.getParent()).setStyle(style.toString()));
    }

    protected abstract String getHexColor();
}
