package user;

import userinterface.UI;

public class BaseUser {
    String name;
    Authentication auth;
    UI ui;

    public BaseUser(String name, UI ui) {
        this.name = name;
        String username = name.replace(" ", "_");
        this.auth = new Authentication("password", username, ui);
        this.ui = ui;
    }

    public String getName() {
        return name;
    }

    public Authentication getAuthentication(){
        return this.auth;
    }
}
