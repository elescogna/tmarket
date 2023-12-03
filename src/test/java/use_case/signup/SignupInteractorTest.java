package use_case.signup;

import data_access.AtlasStudentDataAccessObject;
import entities.CommonStudentFactory;
import entities.Student;
import entities.StudentFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class SignupInteractorTest {
    @Test
    void successTest() {
        SignupInputData inputData = new SignupInputData("Emma", "abcd", "abcd", "111 St. George Street, Toronto", "emma@mail.utoronto.ca");
        SignupUserDataAccessInterface userRepository = new AtlasStudentDataAccessObject();
        // This creates a successPresenter that tests whether the test case is as we expect.
        SignupOutputBoundary successPresenter = new SignupOutputBoundary() {
            @Override
            public void prepareSuccessView(SignupOutputData user) {
                // 2 things to check: the output data is correct, and the user has been created in the DAO.
                assertEquals("Emma", user.getUsername());
                try {
                    assertTrue(userRepository.existsByEmail("emma@mail.utoronto.ca"));
                } catch (IOException e) {
                    System.out.println("IOException");
                }
            }
            @Override
            public void prepareFailView(String error) {
                fail("Use case failure is unexpected.");
            }
        };
        SignupInputBoundary interactor = new SignupInteractor(userRepository, successPresenter, new CommonStudentFactory() {
        });
        interactor.execute(inputData);
    }
    @Test
    void failureUserExistsTest() {
        SignupInputData inputData = new SignupInputData("Paul", "paul", "paul", "112 St. George Street, Toronto", "paul@mail.utoronto.ca");
        SignupUserDataAccessInterface userRepository = new AtlasStudentDataAccessObject();

        // Add Paul to the repo so that when we check later they already exist
        StudentFactory factory = new CommonStudentFactory();
        Student user = factory.create("Paul", "paul", "112 St. George Street, Toronto", "paul@mail.utoronto.ca");
        try {
            userRepository.addStudent(user);
        } catch (IOException e) {
            System.out.println("Should not happen");
        }

        // This creates a presenter that tests whether the test case is as we expect.
        SignupOutputBoundary failurePresenter = new SignupOutputBoundary() {
            @Override
            public void prepareSuccessView(SignupOutputData user) {
                // 2 things to check: the output data is correct, and the user has been created in the DAO.
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("User already exists.", error);
            }
        };

        SignupInputBoundary interactor = new SignupInteractor(userRepository, failurePresenter, new CommonStudentFactory());
        interactor.execute(inputData);
    }
    @Test
    void passwordsDontMatchTest() {
        SignupInputData inputData = new SignupInputData("Clary", "clary", "izzy", "Art Gallery of Ontario", "clary@mail.utoronto.ca");
        SignupUserDataAccessInterface userRepository = new AtlasStudentDataAccessObject();

        // This creates a presenter that tests whether the test case is as we expect.
        SignupOutputBoundary failurePresenter = new SignupOutputBoundary() {
            @Override
            public void prepareSuccessView(SignupOutputData user) {
                // 2 things to check: the output data is correct, and the user has been created in the DAO.
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("Passwords don't match.", error);
            }
        };

        SignupInputBoundary interactor = new SignupInteractor(userRepository, failurePresenter, new CommonStudentFactory());
        interactor.execute(inputData);
    }
}