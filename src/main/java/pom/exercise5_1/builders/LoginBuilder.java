package pom.exercise5_1.builders;

public class LoginBuilder {
    private String username;
    private String password;

    public LoginBuilder withUsername(String username) {
        this.username = username;
        return this;
    }

    public LoginBuilder withPassword(String password) {
        this.password = password;
        return this;
    }

    public LoginData build() {
        return new LoginData(username, password);
    }
}
