package use_case.login;

import entities.Student;
import java.io.IOException;

public class LoginInteractor implements LoginInputBoundary {
    final LoginDataAccessInterface studentDataAccessObject;
    final LoginOutputBoundary loginPresenter;

    public LoginInteractor(LoginDataAccessInterface studentDataAccessInterface,
            LoginOutputBoundary loginOutputBoundary) {
        this.studentDataAccessObject = studentDataAccessInterface;
        this.loginPresenter = loginOutputBoundary;
    }

    @Override
    public void execute(LoginInputData loginInputData) {
        String username = loginInputData.getUsername();
        String password = loginInputData.getPassword();

        boolean passwordCheck;

        try {passwordCheck = studentDataAccessObject.checkPassword(username, password);} catch (IOException e) {this.loginPresenter.prepareFailView("Cannot access Atlas database.");return;}

        if (!passwordCheck) {
            loginPresenter.prepareFailView("Invalid username or password!");
        } else {
            try {
                Student student = studentDataAccessObject.getStudentByEmail(
                        loginInputData.getUsername());
                LoginOutputData loginOutputData = new LoginOutputData(student);
                loginPresenter.prepareSuccessView(loginOutputData);
            } catch (IOException e) {throw new RuntimeException(e);}
        }
    }
}
