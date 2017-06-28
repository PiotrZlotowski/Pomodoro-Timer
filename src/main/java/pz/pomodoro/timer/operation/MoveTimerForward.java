package pz.pomodoro.timer.operation;

import pz.pomodoro.timer.model.TimerInterface;
import pz.pomodoro.timer.operation.IOperation;

import java.util.concurrent.TimeUnit;

/**
 * Created by piotr on 07/05/2017.
 */
public class MoveTimerForward implements IOperation {

    @Override
    public void performOperation(long remainingTime, TimerInterface timerInterface) {
        String formattedRemainingTime = String.format("%02d:%02d",  TimeUnit.MILLISECONDS.toMinutes(remainingTime) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(remainingTime)),
                TimeUnit.MILLISECONDS.toSeconds(remainingTime) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(remainingTime)));
        timerInterface.getLblCurrentTime().setText(formattedRemainingTime);

    }
}
