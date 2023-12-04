package interface_adapter.login;

import interface_adapter.ViewManagerModel;
import interface_adapter.home.HomeState;
import interface_adapter.home.HomeViewModel;
import use_case.login.LoginOutputBoundary;
import use_case.login.LoginOutputData;

public class LoginPresenter implements LoginOutputBoundary {

    private LoginViewModel loginViewModel;
    private HomeViewModel homeViewModel;
    private ViewManagerModel viewManagerModel;

    public LoginPresenter(HomeViewModel homeViewModel,
                          LoginViewModel loginViewModel, ViewManagerModel viewManagerModel) {
        this.homeViewModel = homeViewModel;
        this.loginViewModel = loginViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    /**
     * Prepares the login view with the info from the output data given.
     *
     * @param response the LoginOutputData containing the correct info to
     * display on the login screen.
     */
    @Override
    public void prepareSuccessView(LoginOutputData response) {
        HomeState homeState = homeViewModel.getState();
        homeState.setCurrentStudent(response.getStudent());

        this.viewManagerModel.setActiveView("home");
        this.viewManagerModel.firePropertyChanged();
    }

    /**
     * Prepares to display the error message given.
     *
     * @param error the error to display
     */
    @Override
    public void prepareFailView(String error) {
        LoginState loginState = loginViewModel.getState();
        loginState.setLoginError(error);
        loginViewModel.firePropertyChanged();
    }
}
