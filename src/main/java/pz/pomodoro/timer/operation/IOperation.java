package pz.pomodoro.timer.operation;

import pz.pomodoro.timer.model.TimerInterface;

/**
 * Created by piotr on 21/05/2017.
 */
public interface IOperation {

    void performOperation(long remainingTime, TimerInterface timerInterface);

}
