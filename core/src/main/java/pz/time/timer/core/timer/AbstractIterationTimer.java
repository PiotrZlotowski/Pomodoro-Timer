package pz.time.timer.core.timer;

import pz.time.timer.core.PluginManager;
import pz.time.timer.core.context.TimerContext;
import pz.time.timer.model.TimerInterface;
import pz.timer.api.core.Context;
import pz.timer.api.core.Operation;
import pz.timer.api.core.Timer;
import pz.timer.api.plugin.IterationBreak;
import pz.timer.api.plugin.IterationEnd;
import pz.timer.api.plugin.IterationStart;
import pz.timer.api.plugin.TimerTick;

import java.util.Map;
import java.util.Set;

/**
 * Created by piotr on 07/05/2017.
 */
public abstract class AbstractIterationTimer implements Timer {

    private boolean isFirstTick = true;
    protected long iterationTime;
    protected long currentIterationTick;
    protected boolean isBreak = false;
    private Map<Class, Set<Operation>> iterationPlugins;

    public AbstractIterationTimer() {
        PluginManager pluginManager = new PluginManager();
        iterationPlugins = pluginManager.retrievePlugins();
    }

    @Override
    public void firstTickOperations(Context context) {
        iterationPlugins.entrySet().stream().flatMap(v -> v.getValue().stream()).filter(k -> k instanceof IterationStart).forEach(o -> o.performOperation(context));
    }

    @Override
    public void iterationTickOperations(Context context) {
        iterationPlugins.entrySet().stream().flatMap(v -> v.getValue().stream()).filter(k -> k instanceof TimerTick).forEach(o -> o.performOperation(context));
    }

    @Override
    public void iterationEndOperations(Context context) {
        currentIterationTick = 0;
        iterationPlugins.entrySet().stream().flatMap(v -> v.getValue().stream()).filter(k -> k instanceof IterationEnd).forEach(o -> o.performOperation(context));
    }

    @Override
    public void breakStartOperations(Context context) {
        iterationPlugins.entrySet().stream().flatMap(v -> v.getValue().stream()).filter(k -> k instanceof IterationBreak).forEach(o -> o.performOperation(context));
    }

    @Override
    public long tick(Context context) {
        currentIterationTick++;
        if (isFirstTick) {
            iterationTime = getIterationTime() + 1;
            firstTickOperations(context);
            isFirstTick = false;
        }

        long remainingTime = calculateRemainingTime(context);
        if (remainingTime <= 0) {
            iterationEndOperations(context);

            if (getBreakTime() <= 0 && !isContinuous()) {
                return 0;
            }
            // the end of the break (not continuous)
            if (getBreakTime() <= 0 && isBreak && !isContinuous()) {
                return 0;
            }
            // the end of the break (continuous)
            if (isBreak && isContinuous()) {
                iterationTime = getIterationTime();
                long newIterationRemainingTime = calculateRemainingTime(context);
                firstTickOperations(context);
                isBreak = false;
                return newIterationRemainingTime;
            }
            // the start of the break
            if (!isBreak && getBreakTime() > 0) {
                iterationTime = getBreakTime();
                long breakRemainingTime = calculateRemainingTime(context);
                currentIterationTick = 0;
                isBreak = true;
                breakStartOperations(context);
                return breakRemainingTime;
            }
        }
        iterationTickOperations(context);
        return remainingTime;
    }

    private long calculateRemainingTime(Context context) {
        long remainingTime = iterationTime - currentIterationTick;
        if (context instanceof TimerContext) {
            ((TimerContext) context).updateTime(remainingTime);
        }
        return remainingTime;
    }
}
