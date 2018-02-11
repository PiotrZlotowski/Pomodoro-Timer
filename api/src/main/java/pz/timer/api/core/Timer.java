package pz.timer.api.core;


/**
 * Created by piotr on 07/05/2017.
 */
public interface Timer {

    boolean isContinuous();
    long getBreakTime();
    long getIterationTime();
    long tick(Context context);
    void firstTickOperations(Context context);
    void iterationTickOperations(Context context);
    void iterationEndOperations(Context context);
    void breakStartOperations(Context context);
}
