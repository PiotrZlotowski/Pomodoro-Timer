package pz.time.timer.core.timer;

import pz.time.timer.core.profile.Profile;
import pz.time.timer.core.timer.AbstractIterationTimer;

/**
 * Created by piotr on 07/05/2017.
 */
public class ProfileTimer extends AbstractIterationTimer {

    private Profile profile;
    public ProfileTimer(Profile profile) {
        this.profile = profile;
    }


    @Override
    public boolean isContinuous() {
        return profile.isContinuous();
    }

    @Override
    public long getBreakTime() {
        return profile.getBreakTime();
    }

    @Override
    public long getIterationTime() {
        return profile.getIterationTime();
    }
}
