package app;

import interface_adapter.ViewManagerModel;
import interface_adapter.go_home.GoHomeController;
import interface_adapter.home.HomeViewModel;
import interface_adapter.login.LoginController;
import interface_adapter.login.LoginPresenter;
import interface_adapter.login.LoginViewModel;
import java.io.IOException;
import javax.swing.*;
import use_case.login.LoginDataAccessInterface;
import use_case.login.LoginInputBoundary;
import use_case.login.LoginInteractor;
import use_case.login.LoginOutputBoundary;
import view.LoginView;

public class LoginUseCaseFactory {

    /** Prevent instantiation. */
    private LoginUseCaseFactory() {}

    public static LoginView
        create(LoginViewModel loginViewModel, HomeViewModel homeViewModel,
                LoginDataAccessInterface studentDataAccessObject,
                ViewManagerModel viewManagerModel) {
            try {
                LoginController loginController =
                    createLoginUseCase(loginViewModel, homeViewModel, viewManagerModel,
                            studentDataAccessObject);
                return new LoginView(loginViewModel, loginController);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Could not create use case.");
            }

            return null;
        }

    private static LoginController
        createLoginUseCase(LoginViewModel loginViewModel, HomeViewModel homeViewModel,
                ViewManagerModel viewManagerModel,
                LoginDataAccessInterface studentDataAccessObject)
            throws IOException {

            // Notice how we pass this method's parameters to the Presenter.
            LoginOutputBoundary loginOutputBoundary =
                new LoginPresenter(homeViewModel, loginViewModel, viewManagerModel);

            LoginInputBoundary loginInteractor =
                new LoginInteractor(studentDataAccessObject, loginOutputBoundary);

            return new LoginController(loginInteractor);
        }
}
