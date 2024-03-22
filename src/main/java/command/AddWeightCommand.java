package command;

import activeedge.userdetails.LogWeight;
import static activeedge.userdetails.UserDetailsList.detailsList;

public class AddWeightCommand {
    protected Integer weight;

    public AddWeightCommand(Integer weight) {
        this.weight = weight;
    }

    public void execute() throws ActiveEdgeException {
        LogWeight logWeight = new LogWeight(weight);
        detailsList.add(logWeight);
    }
}

