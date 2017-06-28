package pz.pomodoro.timer.timer;

import pz.pomodoro.timer.operation.IOperation;

import java.util.Set;

/**
 * Created by piotr on 17/05/2017.
 */
public class SpringPomodoro extends AbstractPomodoro {

    public static long MINUTE_MULTIPLIER = 1000 * 60;
    private long taskTime;
    private long breakTime;
    private long longBreakTime;
    private long longBreakOccurrence;


    public SpringPomodoro(Set<IOperation> tickActivities, Set<IOperation> startActivities, Set<IOperation> stopActivities) {
        super(tickActivities, startActivities, stopActivities);
    }

    public SpringPomodoro(Set<IOperation> tickActivities, Set<IOperation> startActivities, Set<IOperation> stopActivities, Set<IOperation> firstTickActivities) {
        super(tickActivities, startActivities, stopActivities, firstTickActivities);
    }

    @Override
    public long getTaskTime() {
        return taskTime * MINUTE_MULTIPLIER;
    }

    @Override
    public long getBreakTime() {
        return breakTime * MINUTE_MULTIPLIER;
    }

    @Override
    public long getLongBreakTime() {
        return longBreakTime * MINUTE_MULTIPLIER;
    }

    @Override
    public long getLongBreakOccurrence() {
        return  longBreakOccurrence;
    }

    @Override
    public long getCurrentTaskTime() {
        return 0;
    }


    public void setTaskTime(long taskTime) {
        this.taskTime = taskTime;
    }

    public void setBreakTime(long breakTime) {
        this.breakTime = breakTime;
    }

    public void setLongBreakTime(long longBreakTime) {
        this.longBreakTime = longBreakTime;
    }

    public void setLongBreakOccurrence(long longBreakOccurrence) {
        this.longBreakOccurrence = longBreakOccurrence;
    }
}


