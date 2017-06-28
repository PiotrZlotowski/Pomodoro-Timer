package pz.pomodoro.timer.timer;

import pz.pomodoro.timer.model.TimerInterface;
import pz.pomodoro.timer.operation.IOperation;

import java.util.Collections;
import java.util.Set;

/**
 * Created by piotr on 07/05/2017.
 */
public abstract class AbstractPomodoro implements ITimer {

    private boolean isFirstTick = true;
    protected long remainingTime;
    protected long taskTime;
    protected long currentTick;
    protected boolean isPomodoro = true;
    protected int pomodoroCount;
    private Set<IOperation> tickActivities;
    private Set<IOperation> startActivities;
    private Set<IOperation> stopActivities;
    private Set<IOperation> firstTickActivities;

    public AbstractPomodoro(Set<IOperation> tickActivities, Set<IOperation> startActivities, Set<IOperation> stopActivities) {
        this.tickActivities = emptyIfNull(tickActivities);
        this.stopActivities = emptyIfNull(stopActivities);
        this.startActivities = emptyIfNull(startActivities);
        this.firstTickActivities = Collections.emptySet();
    }

    public AbstractPomodoro(Set<IOperation> tickActivities, Set<IOperation> startActivities, Set<IOperation> stopActivities, Set<IOperation> firstTickActivities) {
        this.tickActivities = emptyIfNull(tickActivities);
        this.startActivities = emptyIfNull(startActivities);
        this.stopActivities = emptyIfNull(stopActivities);
        this.firstTickActivities = emptyIfNull(firstTickActivities);
    }

    @Override
    public void tick(TimerInterface timerInterface) {
        if (isFirstTick) {
            taskTime = getTaskTime();
            performOperations(firstTickActivities, remainingTime, timerInterface);
            isFirstTick = false;
        }

        currentTick++;
        remainingTime = taskTime - currentTick;
        if (remainingTime == 0) {
            if (isPomodoro) {
                pomodoroCount++;
                performOperations(stopActivities, remainingTime, timerInterface);
                isPomodoro = false;
                if (pomodoroCount % getLongBreakOccurrence() == 0) {
                    resetTimer(getLongBreakTime());
                }
                else {
                    resetTimer(getBreakTime());
                }
            }
            else {
                isPomodoro = true;
                resetTimer(getTaskTime());
                performOperations(startActivities, remainingTime, timerInterface);
            }
        }
        performOperations(tickActivities, remainingTime, timerInterface);
    }

    @Override
    public void resetTimer(long newTaskTime) {
        currentTick = 0;
        taskTime = newTaskTime;
    }


    private Set<IOperation> emptyIfNull(Set<IOperation> set) {
        return set == null ? Collections.emptySet() : set;
    }


    private void performOperations(Set<IOperation> operations, long remainingTime, TimerInterface timerInterface) {
        operations.forEach(op -> op.performOperation(remainingTime, timerInterface));
    }
}
