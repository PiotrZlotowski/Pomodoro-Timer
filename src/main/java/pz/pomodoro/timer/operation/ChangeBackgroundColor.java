package pz.pomodoro.timer.operation;

import pz.pomodoro.timer.model.TimerInterface;
import pz.pomodoro.timer.operation.IOperation;

/**
 * Created by piotr on 07/05/2017.
 */
public class ChangeBackgroundColor implements IOperation {

    private String hexColor;

    public ChangeBackgroundColor(String hexColor) {
        this.hexColor = hexColor;
    }

    @Override
    public void performOperation(long remainingTime, TimerInterface timerInterface) {
        StringBuilder colorBuilder = new StringBuilder();
        colorBuilder.append("-fx-background-color:").append(" ").append(hexColor);
        timerInterface.getPane().setStyle(colorBuilder.toString());
    }
}
