package interface_adapter.login;

import interface_adapter.go_home.GoHomeController;
import interface_adapter.home.HomeState;
import interface_adapter.home.HomeViewModel;
import use_case.login.LoginOutputBoundary;
import use_case.login.LoginOutputData;

public class LoginPresenter implements LoginOutputBoundary {

    private LoginViewModel loginViewModel;
    private HomeViewModel homeViewModel;
    private GoHomeController goHomeController;

    public LoginPresenter(HomeViewModel homeViewModel,
                          LoginViewModel loginViewModel,
                          GoHomeController goHomeController) {
        this.goHomeController = goHomeController;
        this.homeViewModel = homeViewModel;
        this.loginViewModel = loginViewModel;
    }

    @Override
    public void prepareSuccessView(LoginOutputData response) {
        HomeState homeState = homeViewModel.getState();
        homeState.setUser(response.getStudent());
        this.goHomeController.execute();
    }

    @Override
    public void prepareFailView(String error) {
        LoginState loginState = loginViewModel.getState();
        loginState.setLoginError(error);
        loginViewModel.firePropertyChanged();
    }
}
