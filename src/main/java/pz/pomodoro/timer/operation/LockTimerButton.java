package pz.pomodoro.timer.operation;

import pz.pomodoro.timer.model.TimerInterface;
import pz.pomodoro.timer.operation.IOperation;

/**
 * Created by piotr on 07/05/2017.
 */
public class LockTimerButton implements IOperation {

    @Override
    public void performOperation(long remainingTime, TimerInterface timerInterface) {
        timerInterface.getTimerButton().setDisable(true);
    }
}
