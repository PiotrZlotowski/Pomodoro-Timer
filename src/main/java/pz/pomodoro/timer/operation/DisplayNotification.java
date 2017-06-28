package pz.pomodoro.timer.operation;

import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import pz.pomodoro.timer.model.TimerInterface;
import pz.pomodoro.timer.operation.IOperation;

/**
 * Created by piotr on 07/05/2017.
 */
public class DisplayNotification implements IOperation {

    private String title;
    private String message;
    private int duration = 30;

    public DisplayNotification(String title, String message, int duration) {
        this.title = title;
        this.message = message;
        this.duration = duration;
    }

    public DisplayNotification(String title, String message) {
        this.title = title;
        this.message = message;
    }

    @Override
    public void performOperation(long remainingTime, TimerInterface timerInterface) {
        Notifications.create().title(title).text(message).hideAfter(Duration.seconds(duration)).show();
    }
}
