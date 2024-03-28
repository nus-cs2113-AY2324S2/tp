package command;

import activeedge.userdetails.LogHeight;
import static activeedge.userdetails.UserDetailsList.detailsList;
import java.time.LocalDateTime;


public class AddHeightCommand {
    protected Integer height;
    protected LocalDateTime dateTime;


    public AddHeightCommand (Integer height, LocalDateTime dateTime) {
        this.height = height;
        this.dateTime = dateTime;

    }

    public void execute() throws ActiveEdgeException {
        LogHeight logHeight = new LogHeight(height,dateTime);
        detailsList.add(logHeight);
    }
}
