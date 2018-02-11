package pz.time.timer.core.plugin;

import javafx.scene.control.Button;
import pz.timer.api.core.Panel;

public abstract class TimerButtonKeeper {

    void updateButtonText(Panel panel) {
        panel.getComponent("timerButton", Button.class).ifPresent(btn -> btn.setText(getText()));
    }

    protected abstract String getText();
}
