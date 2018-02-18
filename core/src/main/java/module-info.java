import pz.time.timer.core.plugin.*;
import pz.timer.api.core.Operation;

module pz.time.core {
    requires spring.context;
    requires spring.beans;
    requires java.sql;
    requires transitive pz.time.api;
    requires javafx.controls;
    requires javafx.media;
    opens pz.time.timer.properties to spring.core;
    exports pz.time.timer.core;
    exports pz.time.timer.factory;
    exports pz.time.timer.properties;
    exports pz.time.timer.TimerTask;
    exports pz.time.timer.core.timer to pz.time.ui;
    exports pz.time.timer.core.profile to pz.time.ui;
    exports pz.time.timer.parameters;

    uses Operation;
    provides Operation with RemainingTimeFormatter, TimerTitleKeeper, BluePanelBackground, GreenPanelBackground, StopTextTimerButton, PlayNotificationSound;

}