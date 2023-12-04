package interface_adapter.login;

public class LoginState {
    private String username = "";
    private String password = "";
    private String loginError = null;

    /**
     * Constructor for the LoginState that makes a new state from a copy.
     *
     * @param copy A LoginState from which to make this LoginState.
     */
    public LoginState(LoginState copy) {
        username = copy.username;
        password = copy.password;
        loginError = copy.loginError;
    }

    /**
     * Default constructor for LoginState.
     */
    public LoginState() {}

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getLoginError() {
        return loginError;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setLoginError(String loginError) {
        this.loginError = loginError;
    }
}
