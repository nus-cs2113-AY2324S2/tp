package CantVasUI;

import java.util.Scanner;




public class UI {


    static final String divider = "------------------------------------------------";

    private Scanner UserInput;

    public UI(){
        this.UserInput = new Scanner(System.in);
    };

    public static void showErrorMsg(){
        System.out.println("Sorry I think you have input an invalid command.");
        System.out.println("Please check again on your input :D");
    }





}
