package pz.time.timer.core.plugin;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import pz.timer.api.core.Context;
import pz.timer.api.plugin.IterationEnd;

import java.io.File;
import java.net.URISyntaxException;

public class PlayNotificationSound implements IterationEnd<Void> {

    private Media sound = null;
    private MediaPlayer mediaPlayer;


    @Override
    public Void performOperation(Context context) {
        if (sound == null) {
            sound = new Media(getClass().getResource("/notifications/1.mp3").toString());
        }
        mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.setStopTime(Duration.seconds(3));
        mediaPlayer.play();
        return null;
    }
}
