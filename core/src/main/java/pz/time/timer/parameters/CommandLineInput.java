package pz.time.timer.parameters;

import java.util.Objects;

public final class CommandLineInput {

    public static CommandLineInput EMPTY_INPUT = new CommandLineInput();
    private final int iterationTime;
    private final int breakTime;
    private final boolean isContinuous;

    private CommandLineInput() {
        iterationTime = 0;
        breakTime = 0;
        isContinuous = false;
    }

    public CommandLineInput(int iterationTime, int breakTime, boolean isContinuous) {
        this.iterationTime = iterationTime;
        this.breakTime = breakTime;
        this.isContinuous = isContinuous;
    }


    public int getIterationTime() {
        return iterationTime;
    }

    public int getBreakTime() {
        return breakTime;
    }

    public boolean isContinuous() {
        return isContinuous;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommandLineInput that = (CommandLineInput) o;
        return getIterationTime() == that.getIterationTime() &&
                getBreakTime() == that.getBreakTime() &&
                isContinuous() == that.isContinuous();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIterationTime(), getBreakTime(), isContinuous());
    }
}
