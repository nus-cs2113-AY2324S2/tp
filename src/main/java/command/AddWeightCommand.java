package command;

import activeedge.userdetails.LogWeight;
import static activeedge.userdetails.UserDetailsList.DETAILS_LIST;
import java.time.LocalDateTime;


public class AddWeightCommand {
    protected Integer weight;
    protected LocalDateTime dateTime;


    public AddWeightCommand(Integer weight, LocalDateTime dateTime) {
        this.weight = weight;
        this.dateTime = dateTime;
    }

    public void execute() throws ActiveEdgeException {
        LogWeight logWeight = new LogWeight(weight, dateTime);
        DETAILS_LIST.add(logWeight);
    }
}

