package pz.time.timer.factory;

import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import pz.time.timer.core.timer.AbstractIterationTimer;

/**
 * Created by piotr on 21/05/2017.
 */
public class PomodoroProfileFactory {

    public static final String DEFAULT_POMODORO = "pomodoro";
    @Autowired
    private ApplicationContext context;


    public AbstractIterationTimer forProfileName(String profileName) {
        AbstractIterationTimer pomodoro;
        if (profileName == null || profileName.isEmpty()) {
            return getDefaultPomodoro();
        }
        try {
              pomodoro = context.getBean(profileName, AbstractIterationTimer.class);
        } catch (NoSuchBeanDefinitionException ex) {
            pomodoro = getDefaultPomodoro();

        }
        return pomodoro;
    }

    private AbstractIterationTimer getDefaultPomodoro() {
        return context.getBean(DEFAULT_POMODORO, AbstractIterationTimer.class);
    }


}
