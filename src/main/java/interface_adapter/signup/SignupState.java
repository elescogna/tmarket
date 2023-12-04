package interface_adapter.signup;

public class SignupState {
    private String username = "";
    private String usernameError = null;
    private String password = "";
    private String repeatPassword = "";
    private String pickupAddress = "";
    private String uoftEmail = "";

    /**
     * Default empty constructor for SignupState.
     */
    public SignupState() {
    }

    public String getUsername() {
        return username;
    }

    public String getUsernameError() {
        return usernameError;
    }

    public String getPassword() {
        return password;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setUsernameError(String usernameError) {
        this.usernameError = usernameError;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }
    public String getPickupAddress(){
        return pickupAddress;
    }
    public void setPickupAddress(String text) {
        this.pickupAddress = text;
    }
    public String getUoftEmail() { return  uoftEmail;}
    public void setUoftEmail(String uoftEmail) { this.uoftEmail = uoftEmail; }

    @Override
    public String toString() {
        return "SignupState{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", repeatPassword='" + repeatPassword + '\'' +
                '}';
    }



}
