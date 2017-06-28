package pz.pomodoro.timer.timer;

import pz.pomodoro.timer.operation.IOperation;

import java.util.Set;

/**
 * Created by piotr on 07/05/2017.
 */
public class ContinuousPomodoro extends AbstractPomodoro {

    public ContinuousPomodoro(Set<IOperation> tickActivities, Set<IOperation> stopActivities, Set<IOperation> startActivities) {
        super(tickActivities, stopActivities, startActivities);
    }

    public ContinuousPomodoro(Set<IOperation> tickActivities, Set<IOperation> startActivities, Set<IOperation> stopActivities, Set<IOperation> firstTickActivities) {
        super(tickActivities, startActivities, stopActivities, firstTickActivities);
    }

    @Override
    public long getTaskTime() {
        return 1500000;
    }

    @Override
    public long getBreakTime() {
        return 300000;
    }

    @Override
    public long getLongBreakTime() {
        return 900000;
    }

    @Override
    public long getLongBreakOccurrence() {
        return 4;
    }

    @Override
    public long getCurrentTaskTime() {
        return remainingTime;
    }
}
