package interface_adapter.login;

import interface_adapter.ViewManagerModel;
import interface_adapter.go_home.GoHomeController;
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

    @Override
    public void prepareSuccessView(LoginOutputData response) {
        HomeState homeState = homeViewModel.getState();
        homeState.setCurrentStudent(response.getStudent());

        this.viewManagerModel.setActiveView("home");
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        LoginState loginState = loginViewModel.getState();
        loginState.setLoginError(error);
        loginViewModel.firePropertyChanged();
    }
}
