package pz.time.timer.core.profile;

public enum Profile {


    POMODORO(1500, 1500, true), NORMAL_ITERATION(5, 5, true);
    long iterationTime;
    long breakTime;
    boolean isContinuous;

    Profile(long iterationTime, long breakTime, boolean isContinuous) {
        this.iterationTime = iterationTime;
        this.breakTime = breakTime;
        this.isContinuous = isContinuous;
    }

    public long getIterationTime() {
        return iterationTime;
    }

    public long getBreakTime() {
        return breakTime;
    }

    public boolean isContinuous() {
        return isContinuous;
    }
}
