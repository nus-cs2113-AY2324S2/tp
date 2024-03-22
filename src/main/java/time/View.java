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

    public void next() {
        // To be implemented by child classes
    }

    public void previous() {
        // To be implemented by child classes
    }

    public LocalDate getStartOfView() {
        return startOfView;
    }
}
