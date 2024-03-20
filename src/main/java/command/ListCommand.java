package command;

import ui.TextUi;

import java.util.ArrayList;

public class ListCommand<T> extends Command{

    protected ArrayList<T> arrayList;

    public ListCommand(ArrayList<T> arrayList) {
        this.arrayList = arrayList;
    }

    public void execute() {
        TextUi.showInventoryList(arrayList);
    }
}

