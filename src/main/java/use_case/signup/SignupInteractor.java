package use_case.signup;

import entities.Student;
import entities.StudentFactory;

import java.io.IOException;
import java.time.LocalDateTime;


public class SignupInteractor implements SignupInputBoundary {
    final SignupUserDataAccessInterface userDataAccessObject;
    final SignupOutputBoundary userPresenter;
    final StudentFactory userFactory;

    public SignupInteractor(SignupUserDataAccessInterface signupDataAccessInterface,
                            SignupOutputBoundary signupOutputBoundary,
                            StudentFactory userFactory) {
        this.userDataAccessObject = signupDataAccessInterface;
        this.userPresenter = signupOutputBoundary;
        this.userFactory = userFactory;
    }

    @Override
    public void execute(SignupInputData signupInputData) {
        if (userDataAccessObject.existsByEmail(signupInputData.getUoftEmail())) {
            userPresenter.prepareFailView("User already exists.");
        } else if (!signupInputData.getPassword().equals(signupInputData.getRepeatPassword())) {
            userPresenter.prepareFailView("Passwords don't match.");
        } else {

            LocalDateTime now = LocalDateTime.now();
            Student user = userFactory.create(signupInputData.getUsername(), signupInputData.getPassword(), signupInputData.getHomeAddress(), signupInputData.getUoftEmail());
            try {
                userDataAccessObject.addStudent(user);
                SignupOutputData signupOutputData = new SignupOutputData(user.getName());
                userPresenter.prepareSuccessView(signupOutputData);
            } catch (IOException e) {
                userPresenter.prepareFailView("Student not added");
            }
        }
    }
}