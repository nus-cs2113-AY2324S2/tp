package textbox;

import command.Command;
import map.AMap;

public class TextBox {
    protected static String nextInstruction;
    protected static String  nextNarration;
    protected static String nextDialogue;
    protected static String nextError;
    public void initTextBox(){
        nextInstruction = "Type 'h' to get the help menu.";
        nextDialogue = " ";
        nextNarration = "Welcome to Calcula: Chronicles of the Algorithmic Kingdom";
        nextError = "";
    }
    public void nextTextBoxBasedOnMapAndCommand(Command userCommand, AMap map){

    }

    public void setNextInstruction(String message){
        nextInstruction = message;
    }
    public void setNextNarration(String message){
        nextNarration = message;
    }

    public void setNextDialogue(String message) {
        nextDialogue = message;
    }

    public void setNextError(String message){
        nextError = message;
    }

    public String getNextDialogue() {
        return nextDialogue;
    }

    public String getNextInstruction() {
        return nextInstruction;
    }

    public String getNextNarration() {
        return nextNarration;
    }

    public String getNextError() {
        return nextError;
    }

    public void clearAll(){
        nextNarration = "";
        nextInstruction = "";
        nextDialogue = "";
        nextError = "";
    }
}
