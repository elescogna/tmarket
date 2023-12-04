package interface_adapter.login;

import entities.Student;
import interface_adapter.ViewManagerModel;
import interface_adapter.home.HomeState;
import interface_adapter.home.HomeViewModel;
import org.junit.jupiter.api.Test;
import use_case.login.LoginOutputData;

import static org.junit.jupiter.api.Assertions.*;

class LoginPresenterTest {

    @Test
    void prepareSuccessViewTest() {
        // Create instances of classes
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        LoginViewModel loginViewModel = new LoginViewModel();
        HomeViewModel homeViewModel = new HomeViewModel();

        // Create LoginPresenter
        LoginPresenter loginPresenter = new LoginPresenter(homeViewModel, loginViewModel, viewManagerModel);

        // Create dummy LoginOutputData
        Student student = new Student("John", "john", "89 Chestnut Street","john@gmail.com");
        LoginOutputData loginOutputData = new LoginOutputData(student);

        // Call the method to be tested
        loginPresenter.prepareSuccessView(loginOutputData);

        // Verify the expected state changes
        HomeState homeState = homeViewModel.getState();
        assertEquals(student, homeState.getStudent());
        assertEquals("home", viewManagerModel.getActiveView());
    }

    @Test
    void prepareFailViewTest() {
        // Create instances of classes
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        LoginViewModel loginViewModel = new LoginViewModel();
        HomeViewModel homeViewModel = new HomeViewModel();

        // Create LoginPresenter
        LoginPresenter loginPresenter = new LoginPresenter(homeViewModel, loginViewModel, viewManagerModel);

        // Call the method to be tested
        loginPresenter.prepareFailView("Invalid credentials");

        // Verify the expected state changes
        LoginState loginState = loginViewModel.getState();
        assertEquals("Invalid credentials", loginState.getLoginError());
    }
}