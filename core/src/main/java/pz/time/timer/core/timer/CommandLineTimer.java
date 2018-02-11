package pz.time.timer.core.timer;

import pz.time.timer.core.profile.Profile;

public class CommandLineTimer extends AbstractIterationTimer {

    private long iterationTime;
    private long breakTime;
    private boolean isContinuous;

    public CommandLineTimer(long iterationTime, long breakTime, boolean isContinuous) {
        this.iterationTime = iterationTime;
        this.breakTime = breakTime;
        this.isContinuous = isContinuous;
    }

    @Override
    public boolean isContinuous() {
        return isContinuous;
    }

    @Override
    public long getBreakTime() {
        return breakTime;
    }

    @Override
    public long getIterationTime() {
        return iterationTime;
    }
}
