package command;

import activeedge.userdetails.LogHeight;
import static activeedge.userdetails.UserDetailsList.detailsList;

public class AddHeightCommand {
    protected Integer height;

    public AddHeightCommand (Integer height) {
        this.height = height;
    }

    public void execute() throws ActiveEdgeException {
        LogHeight logHeight = new LogHeight(height);
        detailsList.add(logHeight);
    }
}
