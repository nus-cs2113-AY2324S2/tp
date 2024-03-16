package textbox;

import command.Command;
import map.AMap;

public class TextBox {
    protected String nextMessage;
    public void initTextBox(){
        this.nextMessage = " ";
    }
    public void nextTextBoxBasedOnMapAndCommand(Command userCommand, AMap map){

    }
    public String getNextMessage(){
        return nextMessage;
    }
}
