package command;

import activeedge.userdetails.LogWeight;
import static activeedge.userdetails.UserDetailsList.detailsList;
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
        detailsList.add(logWeight);
    }
}

