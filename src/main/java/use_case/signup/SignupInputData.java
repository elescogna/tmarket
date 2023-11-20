package use_case.signup;

public class SignupInputData {

    final private String username;
    final private String password;
    final private String repeatPassword;
    final private String homeAddress;
    final private String uoftEmail;

    public SignupInputData(String username, String password, String repeatPassword, String homeAddress, String uoftEmail) {
        this.username = username;
        this.password = password;
        this.repeatPassword = repeatPassword;
        this.homeAddress = homeAddress;
        this.uoftEmail = uoftEmail;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public String getHomeAddress() {
        return homeAddress;
    }

    public String getUoftEmail() {
        return uoftEmail;
    }
}