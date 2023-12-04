package use_case.login;

import data_access.AtlasStudentDataAccessObject;
import entities.CommonStudentFactory;
import entities.Student;
import entities.StudentFactory;
import org.junit.jupiter.api.Test;
import use_case.signup.SignupUserDataAccessInterface;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class LoginInteractorTest {

    @Test
    void successTest() {
        LoginInputData loginInputData = new LoginInputData("anna@mail.utoronto.ca", "abcd");
        LoginDataAccessInterface userRepository = new AtlasStudentDataAccessObject();
        // This creates a successPresenter that tests whether the test case is as we expect.
        StudentFactory factory = new CommonStudentFactory();
        SignupUserDataAccessInterface userRepositoryAdd = new AtlasStudentDataAccessObject();
        Student user = factory.create("Anna", "abcd", "91 Charles Street, Toronto", "anna@mail.utoronto.ca");
        try {
            userRepositoryAdd.addStudent(user);
        } catch (IOException e) {
            System.out.println("Should not happen");
        }
        LoginOutputBoundary successPresenter = new LoginOutputBoundary() {
            @Override
            public void prepareSuccessView(LoginOutputData user) {
                // 2 things to check: the output data is correct, and the user is in the DAO.
                Student currentStudent = user.getStudent();
                assertEquals("anna@mail.utoronto.ca", currentStudent.getUoftEmail());
                assertEquals("abcd", currentStudent.getPassword());
                try {
                    assertTrue(userRepository.existsByEmail("anna@mail.utoronto.ca"));
                } catch (IOException e) {
                    System.out.println("IOException");
                }
            }
            @Override
            public void prepareFailView(String error) {
                fail("Use case failure is unexpected.");
            }
        };
        LoginInputBoundary interactor = new LoginInteractor(userRepository, successPresenter) {
        };
        interactor.execute(loginInputData);
    }
    @Test
    void failureUserExistsTest() {
        LoginInputData inputData = new LoginInputData("paul94@gmail.com", "paul");
        LoginDataAccessInterface userRepository = new AtlasStudentDataAccessObject();

        // This creates a presenter that tests whether the test case is as we expect.
        LoginOutputBoundary failurePresenter = new LoginOutputBoundary() {
            @Override
            public void prepareSuccessView(LoginOutputData user) {
                // 2 things to check: the output data is correct, and the user has been created in the DAO.
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("Invalid username or password!", error);
            }
        };

        LoginInputBoundary interactor = new LoginInteractor(userRepository, failurePresenter);
        interactor.execute(inputData);
    }
}
