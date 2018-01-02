package pz.pomodoro.timer;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import pz.pomodoro.timer.properties.PomodoroProperties;

import java.util.Map;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"beans/spring-pomodoro.xml"});
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/forms/Timer.fxml"));
        fxmlLoader.setControllerFactory(context::getBean);
        Parent root = fxmlLoader.load();
        Map<String, String> parameters = getParameters().getNamed();
        primaryStage.setTitle("Pomodoro");
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(root, 210, 80));
        setStayStopSettings(primaryStage, context);
        primaryStage.show();
    }

    private void setStayStopSettings(Stage primaryStage, ApplicationContext context) {
        PomodoroProperties pomodoroProperties = context.getBean("pomodoroProperties", PomodoroProperties.class);
        primaryStage.setAlwaysOnTop(pomodoroProperties.isStayTop());
    }


    public static void main(String[] args) {
        launch(args);
    }
}
