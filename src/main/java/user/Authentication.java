package user;

import customexceptions.SecurityException;
import userinterface.UI;

public class Authentication {
    String username;
    UI ui;
    private String password;
    private int wrongAttempts;
    

    public Authentication(String password, String username, UI ui) {
        this.password = password;
        this.username = username;
        this.ui = ui;
        this.wrongAttempts = 0;
    }

    public String getUsername() {
        return this.username;
    }

    public boolean checkPassword(String username, String password) throws SecurityException {
        boolean isMatch = this.password.equals(password) && this.username.equals(username);
        if (!isMatch) {
            wrongAttempts++;
            if (wrongAttempts == 3) {
                throw new SecurityException();
            }
        }
        return isMatch;
    }

    public boolean changePassword(String username, String oldPassword, String newPassword) throws SecurityException {
        if (!checkPassword(username, oldPassword)) {
            return false;
        }
        this.password = newPassword;
        return true;
    }

    public boolean authenticate() throws SecurityException {
        System.out.println("username: ");
        String inputUsername = this.ui.readInput();
        System.out.println("password: ");
        String inputPassword = this.ui.readInput();
        return this.checkPassword(inputUsername, inputPassword);
    }


}
