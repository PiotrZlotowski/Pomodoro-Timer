package pz.app.launcher;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.commons.cli.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import pz.time.timer.properties.PomodoroProperties;
import pz.timer.api.core.Panel;
import pz.timer.api.core.PanelAware;
import pz.timer.controller.TimerController;
import pz.time.timer.parameters.CommandLineInput;
import pz.timer.api.core.ParameterAware;
import pz.timer.panels.TimerPanel;

import java.io.InputStream;
import java.util.NoSuchElementException;

public class Main extends Application {

    public static final String BREAK = "break";
    public static final String CONTINUOUS = "continuous";
    public static final String ITERATION = "iteration";
    private static CommandLineInput commandLineInput;

    @Override
    public void start(Stage primaryStage) throws Exception {
//        InputStream timerFormStreamResource = ModuleLayer.boot().findModule("pz.time.ui").orElseThrow(NoSuchElementException::new).getResourceAsStream("/forms/Timer.fxml");

        ApplicationContext context = new ClassPathXmlApplicationContext("spring-pomodoro.xml");
        FXMLLoader fxmlLoader = new FXMLLoader();

        Panel timerPanel = new TimerPanel(fxmlLoader);



        fxmlLoader.setControllerFactory(context::getBean);
        Parent root = timerPanel.launch();

//        Parent root = fxmlLoader.load(timerFormStreamResource);
        primaryStage.setTitle("Pomodoro");
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(root, 210, 80));
        setStayStopSettings(primaryStage, context);

        TimerController timerController = context.getBean("timerController", TimerController.class);

        if (timerController instanceof ParameterAware) {
            timerController.handleCommandLineParameters(commandLineInput);
        }


        if (timerController instanceof PanelAware) {
            timerController.setPanel(timerPanel);
        }


        primaryStage.show();
    }

    private void setStayStopSettings(Stage primaryStage, ApplicationContext context) {
        PomodoroProperties pomodoroProperties = context.getBean("pomodoroProperties", PomodoroProperties.class);
        primaryStage.setAlwaysOnTop(pomodoroProperties.isStayTop());
    }


    public static void main(String[] args) {
        final Options options = getOptions();
        parseArguments(args, options);

        launch(args);
    }

    private static void parseArguments(String[] args, Options options) {
        final CommandLineParser cmdLineParser = new DefaultParser();
        CommandLine commandLine = null;
        try {
            commandLine = cmdLineParser.parse(options, args);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (commandLine.hasOption(ITERATION) || commandLine.hasOption(BREAK) || commandLine.hasOption(CONTINUOUS)) {
            int iterationTime = Integer.valueOf(commandLine.getOptionValue(ITERATION));
            int breakTime = Integer.valueOf(commandLine.getOptionValue(BREAK));
            boolean isContinuous = Boolean.valueOf(commandLine.getOptionValue(CONTINUOUS));
            commandLineInput = new CommandLineInput(iterationTime, breakTime, isContinuous);
        }
    }

    private static Options getOptions() {
        final Option iterationOption = Option.builder("i")
                .required(false)
                .hasArg(true)
                .longOpt(ITERATION)
                .desc("Amount of iterations")
                .build();
        final Option breakOption = Option.builder("b")
                .required(false)
                .hasArg(true)
                .longOpt(BREAK)
                .desc("Time of the break")
                .build();
        final Option continuousOption = Option.builder("c")
                .required(false)
                .hasArg(true)
                .longOpt(CONTINUOUS)
                .desc("Decides whether iteration is continuous or not")
                .build();

        final Options options = new Options();
        options.addOption(iterationOption);
        options.addOption(breakOption);
        options.addOption(continuousOption);
        return options;
    }
}
