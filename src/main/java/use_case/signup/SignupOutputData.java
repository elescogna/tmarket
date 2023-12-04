package use_case.signup;

public class SignupOutputData {

    private final String username;

    /**
     * Creates a new SignupOutputData with the username given.
     *
     * @param username the username with which to create the SignupOutputData
     */
    public SignupOutputData(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

}
