package command;

import activeedge.userdetails.LogBMI;
import activeedge.userdetails.LogHeight;
import static activeedge.userdetails.UserDetailsList.DETAILS_LIST;
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
        DETAILS_LIST.add(logBMI);
    }
}
