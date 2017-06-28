package pz.pomodoro.timer.properties;

import org.springframework.beans.factory.annotation.Value;

/**
 * Created by piotr on 30/05/2017.
 */
public class PomodoroProperties {

    @Value("${pomodoro.timer.profile}")
    private String profile;
    @Value("${pomodoro.timer.stay_top}")
    private boolean stayTop;


    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public boolean isStayTop() {
        return stayTop;
    }

    public void setStayTop(boolean stayTop) {
        this.stayTop = stayTop;
    }
}
