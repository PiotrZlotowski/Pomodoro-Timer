package pz.pomodoro.timer.factory;

import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import pz.pomodoro.timer.timer.AbstractPomodoro;

/**
 * Created by piotr on 21/05/2017.
 */
public class PomodoroProfileFactory {

    public static final String DEFAULT_POMODORO = "pomodoro";
    @Autowired
    private ApplicationContext context;


    public AbstractPomodoro forProfileName(String profileName) {
        AbstractPomodoro pomodoro;
        if (profileName == null || profileName.isEmpty()) {
            return getDefaultPomodoro();
        }
        try {
              pomodoro = context.getBean(profileName, AbstractPomodoro.class);
        } catch (NoSuchBeanDefinitionException ex) {
            pomodoro = getDefaultPomodoro();

        }
        return pomodoro;
    }

    private AbstractPomodoro getDefaultPomodoro() {
        return context.getBean(DEFAULT_POMODORO, AbstractPomodoro.class);
    }


}
