package use_case.login;

public class LoginInputData {

    final private String username;
    final private String password;

    /**
     * Creates a new LoginInputData with the given parameters.
     *
     * @param username the username with which to make the new LoginInputData
     * @param password the password with which to make the LoginInputData
     */
    public LoginInputData(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

}
