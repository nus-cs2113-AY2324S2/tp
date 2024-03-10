package textbox;

import command.Command;
import map.Map;

public class TextBox {
    protected String nextMessage;
    public void initTextBox(){
        this.nextMessage = " ";
    }
    public void nextTextBoxBasedOnMapAndCommand(Command userCommand, Map map){

    }
    public String getNextMessage(){
        return nextMessage;
    }
}
