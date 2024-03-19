package health;

import utility.Constant;
import utility.CustomExceptions;
import utility.Parser;

import java.time.LocalDate;

import static health.HealthList.periods;

public class PeriodCycle extends Health {
    protected LocalDate startDate;
    protected LocalDate endDate = null;
    protected int length;

    public PeriodCycle(LocalDate startDate, String stringEndDate) {
        this.startDate = startDate;
        this.endDate = Parser.parseDate(stringEndDate);
        //this.length = getPeriodLength();
    }
    public PeriodCycle(String stringStartDate, String stringEndDate) {
        this.startDate = Parser.parseDate(stringStartDate);
        this.endDate = Parser.parseDate(stringEndDate);
    }

    public PeriodCycle(String stringStartDate) {
        this.startDate = Parser.parseDate(stringStartDate);
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public static String[] getPeriodCycle(String input) throws CustomExceptions.InvalidInput {
        String[] results = new String[Constant.PERIOD_CYCLE_PARAMETERS];

        if (!input.contains("/h") || !input.contains("/start:") || !input.contains("/end:")) {
            throw new CustomExceptions.InvalidInput(Constant.MISSING_PARAMETERS);
        }

        int indexH = input.indexOf("/h");
        int indexStart = input.indexOf("/start");
        int indexEnd = input.indexOf("/end");

        String command = input.substring(indexH + Constant.PERIOD_CYCLE_H_OFFSET, indexStart).trim();
        String startSubstring = input.substring(indexStart + Constant.PERIOD_CYCLE_START_OFFSET, indexEnd).trim();
        String endSubstring = input.substring(indexEnd + Constant.PERIOD_CYCLE_END_OFFSET).trim();

        if (command.isEmpty() || startSubstring.isEmpty() || endSubstring.isEmpty()) {
            // throw new CustomExceptions(Constant.UNSPECIFIED_PARAMETERS;
        }

        results[0] = command;
        results[1] = startSubstring;
        results[2] = endSubstring;

        return results;
    }

    @Override
    public String toString() {
        return "Period Start: " + this.getStartDate() + " Period End: " + this.endDate;
    }
/*
    public long getPeriodLength() {
        return (startDate, endDate);
    }

    public int getCycleLength(String todayDate) {
        int currentIndex = periods.size();

    }
*/

}
