package command;

import activeedge.userdetails.LogBMI;
import static activeedge.userdetails.UserDetailsList.detailsList;
import java.time.LocalDateTime;

public class AddBMICommand {
    protected Integer bmi;
    protected LocalDateTime dateTime;

    public AddBMICommand (Integer bmi, LocalDateTime dateTime) {
        this.bmi = bmi;
        this.dateTime = dateTime;
    }

    public void execute() throws ActiveEdgeException {
        LogBMI logBMI = new LogBMI(bmi, dateTime);
        detailsList.add(logBMI);
    }
}
