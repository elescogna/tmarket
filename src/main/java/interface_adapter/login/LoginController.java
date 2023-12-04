package interface_adapter.login;

import use_case.login.LoginInputData;
import use_case.login.LoginInputBoundary;

public class LoginController {

    final LoginInputBoundary loginUseCaseInteractor;
    /**
     * Constructor for LoginController using the interactor given.
     *
     * @param loginUseCaseInteractor the interactor with which to make the LoginController
     */
    public LoginController(LoginInputBoundary loginUseCaseInteractor) {
        this.loginUseCaseInteractor = loginUseCaseInteractor;
    }


    /**
     *
     * Creates input data and calls the use case interactor with this input data.
     *
     * @param username the username with which to call the use case interactor
     * @param password the password with which to call the use case interactor
     */
    public void execute(String username, String password) {
        LoginInputData loginInputData = new LoginInputData(
                username, password);

        loginUseCaseInteractor.execute(loginInputData);
    }
}
