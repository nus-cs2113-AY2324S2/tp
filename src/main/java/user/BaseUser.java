package user;

public class BaseUser {
    String name;
    Authentication auth;

    public BaseUser(String name) {
        this.name = name;
        String username = name.replace(" ", "_");
        this.auth = new Authentication("password", username);
    }

    public String getName() {
        return name;
    }

    public Authentication getAuthentication(){
        return this.auth;
    }
}
