package pz.pomodoro.timer.timer;

import pz.pomodoro.timer.model.TimerInterface;

/**
 * Created by piotr on 07/05/2017.
 */
public interface ITimer {
    void tick(TimerInterface timerInterface);
    void resetTimer(long newTaskTime);
    long getTaskTime();
    long getBreakTime();
    long getLongBreakTime();
    long getLongBreakOccurrence();
    long getCurrentTaskTime();
}
