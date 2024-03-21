package user;

import userinterface.UI;

public class Authentication {
    String username;
    UI ui;
    private String password;
    

    public Authentication(String password, String username, UI ui) {
        this.password = password;
        this.username = username;
        this.ui = ui;
    }

    public String getUsername() {
        return this.username;
    }

    public Boolean checkPassword(String username, String password) {
        return this.password.equals(password) && this.username.equals(username);
    }

    public Boolean changePassword(String username, String oldPassword, String newPassword) {
        if (!checkPassword(username, oldPassword)) {
            return false;
        }
        this.password = newPassword;
        return true;
    }

    public Boolean authenticate(){
        System.out.println("username: ");
        String inputUsername = this.ui.readInput();
        System.out.println("password: ");
        String inputPassword = this.ui.readInput();
        return this.checkPassword(inputUsername, inputPassword);
    }


}
