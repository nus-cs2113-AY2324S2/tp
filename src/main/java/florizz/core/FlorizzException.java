package florizz.core;

public class FlorizzException extends Exception{
    public String errorMessage;

    public FlorizzException(String errorMessage){
        this.errorMessage = errorMessage;
    }
}
