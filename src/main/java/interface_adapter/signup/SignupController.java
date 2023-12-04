package interface_adapter.signup;

import use_case.signup.SignupInputBoundary;
import use_case.signup.SignupInputData;

public class SignupController {

    final SignupInputBoundary userSignupUseCaseInteractor;
    /**
     * Creates a new SignupController with the given SignupInteractor
     *
     * @param userSignupUseCaseInteractor the SignupInteractor with which to create this SignupController
     */
    public SignupController(SignupInputBoundary userSignupUseCaseInteractor) {
        this.userSignupUseCaseInteractor = userSignupUseCaseInteractor;
    }

    /**
     * Creates input data with the given parameters and then calls the SignupInteractor with that data.
     *
     * @param username the username with which to call the SignupInteractor
     * @param password1 the password with which to call the SignupInteractor
     * @param password2 the "confirm password" with which to call the SignupInteractor
     * @param homeAddress the home address with which to call the SignupInteractor
     * @param uoftEmail the UofT email with which to call the SignupInteractor
     */
    public void execute(String username, String password1, String password2, String homeAddress, String uoftEmail) {
        SignupInputData signupInputData = new SignupInputData(username, password1, password2, homeAddress, uoftEmail);

        userSignupUseCaseInteractor.execute(signupInputData);
    }
}
