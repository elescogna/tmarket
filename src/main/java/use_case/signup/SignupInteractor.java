package use_case.signup;

import entities.Student;
import entities.StudentFactory;
import java.io.IOException;
import java.time.LocalDateTime;

public class SignupInteractor implements SignupInputBoundary {
    final SignupUserDataAccessInterface studentDataAccessObject;
    final SignupOutputBoundary userPresenter;
    final StudentFactory userFactory;

    public SignupInteractor(
            SignupUserDataAccessInterface signupDataAccessInterface,
            SignupOutputBoundary signupOutputBoundary, StudentFactory userFactory) {
        this.studentDataAccessObject = signupDataAccessInterface;
        this.userPresenter = signupOutputBoundary;
        this.userFactory = userFactory;
            }

    @Override
    public void execute(SignupInputData signupInputData) {
        boolean existsByEmail;

        try {
            existsByEmail =
                studentDataAccessObject.existsByEmail(signupInputData.getUoftEmail());
        } catch (IOException e) {
            userPresenter.prepareFailView("Cannot access Atlas database.");
            return;
        }

        if (existsByEmail) {
            userPresenter.prepareFailView("User already exists.");
        } else if (!signupInputData.getPassword().equals(
                    signupInputData.getRepeatPassword())) {
            userPresenter.prepareFailView("Passwords don't match.");
        } else {

            // LocalDateTime now = LocalDateTime.now();
            Student user = userFactory.create(
                    signupInputData.getUsername(), signupInputData.getPassword(),
                    signupInputData.getHomeAddress(), signupInputData.getUoftEmail());
            try {
                studentDataAccessObject.addStudent(user);
                SignupOutputData signupOutputData =
                    new SignupOutputData(user.getName());
                userPresenter.prepareSuccessView(signupOutputData);
            } catch (IOException e) {
                userPresenter.prepareFailView("Student not added");
            }
        }
    }
}
