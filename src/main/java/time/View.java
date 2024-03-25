package time;

import data.TaskManager;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public abstract class View {
    protected LocalDate startOfView;
    protected final DateTimeFormatter dateFormatter;

    public View(LocalDate startOfView, DateTimeFormatter dateFormatter) {
        this.startOfView = startOfView;
        this.dateFormatter = dateFormatter;
    }

    public abstract void printView(TaskManager taskManager);

    public abstract void next();

    public abstract void previous();

    public LocalDate getStartOfView() {
        return startOfView;
    }
}
