package app;

import entities.CommonStudentFactory;
import entities.StudentFactory;
import interface_adapter.*;
import interface_adapter.login.LoginViewModel;
import interface_adapter.signup.SignupController;
import interface_adapter.signup.SignupPresenter;
import interface_adapter.signup.SignupViewModel;
import java.io.IOException;
import javax.swing.*;
import use_case.signup.SignupInputBoundary;
import use_case.signup.SignupInteractor;
import use_case.signup.SignupOutputBoundary;
import use_case.signup.SignupUserDataAccessInterface;
import view.SignupView;
public class SignupUseCaseFactory {
    private SignupUseCaseFactory() {}

    public static SignupView
        create(ViewManagerModel viewManagerModel, LoginViewModel loginViewModel,
                SignupViewModel signupViewModel,
                SignupUserDataAccessInterface userDataAccessObject) {

            try {
                SignupController signupController =
                    createUserSignupUseCase(viewManagerModel, signupViewModel,
                            loginViewModel, userDataAccessObject);
                return new SignupView(signupController, signupViewModel, loginViewModel, viewManagerModel);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Could not open user data file.");
            }

            return null;
        }
    private static SignupController createUserSignupUseCase(
            ViewManagerModel viewManagerModel, SignupViewModel signupViewModel,
            LoginViewModel loginViewModel,
            SignupUserDataAccessInterface userDataAccessObject) throws IOException {

        // Notice how we pass this method's parameters to the Presenter.
        SignupOutputBoundary signupOutputBoundary =
            new SignupPresenter(viewManagerModel, signupViewModel, loginViewModel);

        StudentFactory userFactory = new CommonStudentFactory();

        SignupInputBoundary userSignupInteractor = new SignupInteractor(
                userDataAccessObject, signupOutputBoundary, userFactory);

        return new SignupController(userSignupInteractor);
            }
}
