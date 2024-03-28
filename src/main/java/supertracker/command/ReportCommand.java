package supertracker.command;

import supertracker.ui.Ui;
import supertracker.item.Inventory;
import supertracker.item.Item;
import java.time.LocalDate;

import java.util.ArrayList;
import java.util.List;

public class ReportCommand implements Command{
    private String reportType;
    private int threshold;
    public ReportCommand(String reportType, int threshold) {
        this.reportType = reportType;
        this.threshold = threshold;
    }

    @Override
    public void execute() {
        List<Item> items = Inventory.getItems();
        List<Item> reportItems = new ArrayList<>();
        LocalDate currDate = LocalDate.now();
        if (reportType.equals("low stock")) {
            for (Item item : items) {
                if (item.getQuantity() < threshold) {
                    reportItems.add(item);
                }
            }
            Ui.reportCommandSuccess(reportItems, reportType);
        }
        /* for (Item item : items) {
                if (item.getExpiryDate() < currDate) {
                    reportItems.add(item);
                }
            }
            Ui.reportCommandSuccess(reportItems, reportType);
         */
    }

    @Override
    public boolean isQuit() {
        return false;
    }

}
