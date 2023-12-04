package interface_adapter.signup;

import interface_adapter.ViewManagerModel;
import interface_adapter.login.LoginState;
import interface_adapter.login.LoginViewModel;
import org.junit.jupiter.api.Test;
import use_case.signup.SignupOutputData;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SignupPresenterTest {

    @Test
    void prepareSuccessViewTest() {
        // Create instances of classes
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        SignupViewModel signupViewModel = new SignupViewModel();
        LoginViewModel loginViewModel = new LoginViewModel();

        // Create SignupPresenter
        SignupPresenter signupPresenter = new SignupPresenter(viewManagerModel, signupViewModel, loginViewModel);

        // Create dummy SignupOutputData
        SignupOutputData signupOutputData = new SignupOutputData("John");

        // Call the method to be tested
        signupPresenter.prepareSuccessView(signupOutputData);

        // Verify the expected state changes
        LoginState loginState = loginViewModel.getState();
        assertEquals("John", loginState.getUsername());
        assertEquals(loginState, loginViewModel.getState());
        assertEquals(loginViewModel.getViewName(), viewManagerModel.getActiveView());
    }

    @Test
    void prepareFailViewTest() {
        // Create instances of classes
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        SignupViewModel signupViewModel = new SignupViewModel();
        LoginViewModel loginViewModel = new LoginViewModel();

        // Create SignupPresenter
        SignupPresenter signupPresenter = new SignupPresenter(viewManagerModel, signupViewModel, loginViewModel);

        // Call the method to be tested
        signupPresenter.prepareFailView("Username already exists");

        // Verify the expected state changes
        SignupState signupState = signupViewModel.getState();
        assertEquals("Username already exists", signupState.getUsernameError());
    }
}
