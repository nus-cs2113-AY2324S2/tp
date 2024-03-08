package user;

public class Authentication {
    private String password;
    String username;

    public Authentication(String password, String username) {
        this.password = password;
        this.username = username;
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
}
