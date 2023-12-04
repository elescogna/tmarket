package interface_adapter.login;

import interface_adapter.login.LoginController;
import org.junit.jupiter.api.Test;
import use_case.login.LoginInputBoundary;
import use_case.login.LoginInputData;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MockLoginInteractor implements LoginInputBoundary {
    private boolean executeCalled = false;
    private LoginInputData inputData;

    @Override
    public void execute(LoginInputData input) {
        this.executeCalled = true;
        this.inputData = input;
    }

    public boolean isExecuteCalled() {
        return executeCalled;
    }

    public LoginInputData getInputData() {
        return inputData;
    }
}

class LoginControllerTest {

    @Test
    void executeTest() {
        // Create a mock implementation of LoginInputBoundary
        MockLoginInteractor mockLoginInteractor = new MockLoginInteractor();

        // Create LoginController with the mock LoginInputBoundary
        LoginController loginController = new LoginController(mockLoginInteractor);

        // Call the execute method on LoginController
        String username = "John";
        String password = "password";

        loginController.execute(username, password);

        // Verify that the LoginInputData passed to the LoginInputBoundary is as expected
        assertTrue(mockLoginInteractor.isExecuteCalled());
        LoginInputData expectedInputData = new LoginInputData(username, password);
        assertEquals(expectedInputData.getUsername(), mockLoginInteractor.getInputData().getUsername());
        assertEquals(expectedInputData.getPassword(), mockLoginInteractor.getInputData().getPassword());
    }
}
