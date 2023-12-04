package interface_adapter.signup;
import org.junit.jupiter.api.Test;
import use_case.signup.SignupInputBoundary;
import use_case.signup.SignupInputData;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


class MockSignupInteractor implements SignupInputBoundary {
    private boolean executeCalled = false;
    private SignupInputData inputData;

    @Override
    public void execute(SignupInputData input) {
        this.executeCalled = true;
        this.inputData = input;
    }

    public boolean isExecuteCalled() {
        return executeCalled;
    }

    public SignupInputData getInputData() {
        return inputData;
    }
}

class SignupControllerTest {

    @Test
    void executeTest() {
        // Create a mock implementation of SignupInputBoundary
        MockSignupInteractor mockSignupInteractor = new MockSignupInteractor();

        // Create SignupController with the mock SignupInputBoundary
        SignupController signupController = new SignupController(mockSignupInteractor);

        // Call the execute method on SignupController
        String username = "John";
        String password1 = "password";
        String password2 = "password";
        String homeAddress = "123 Main St";
        String uoftEmail = "john@example.com";

        signupController.execute(username, password1, password2, homeAddress, uoftEmail);

        // Verify that the execute method of SignupInputBoundary is called with the expected SignupInputData
        assertTrue(mockSignupInteractor.isExecuteCalled());
        SignupInputData expectedInputData = new SignupInputData(username, password1, password2, homeAddress, uoftEmail);
        assertEquals(expectedInputData.getUsername(), mockSignupInteractor.getInputData().getUsername());
        assertEquals(expectedInputData.getHomeAddress(), mockSignupInteractor.getInputData().getHomeAddress());
        assertEquals(expectedInputData.getUoftEmail(), mockSignupInteractor.getInputData().getUoftEmail());
        assertEquals(expectedInputData.getPassword(), mockSignupInteractor.getInputData().getPassword());
        assertEquals(expectedInputData.getRepeatPassword(), mockSignupInteractor.getInputData().getRepeatPassword());
    }
}
