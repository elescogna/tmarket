package interface_adapter.profile;

import entities.Student;
import org.junit.jupiter.api.Test;
import use_case.profile.ProfileInputBoundary;
import use_case.profile.ProfileInputData;

import static org.junit.jupiter.api.Assertions.*;

class MockProfileInteractor implements ProfileInputBoundary {
    private boolean isExecuteCalled = false;
    private ProfileInputData inputData;
    @Override
    public void execute(ProfileInputData inputData) {
            this.isExecuteCalled = true;
            this.inputData = inputData;
    }

    public ProfileInputData getInputData() {
        return inputData;
    }

    public boolean isExecuteCalled() {
        return isExecuteCalled;
    }
}

public class ProfileControllerTest {
    @Test
    void successTest() {
        MockProfileInteractor interactor = new MockProfileInteractor();
        ProfileController controller = new ProfileController(interactor);
        Student student = new Student("someId", "John", "Doe", "700 University", "john.doe@mail.utoronto.ca");
        ProfileInputData profileInputData = new ProfileInputData(student);
        controller.execute(student);
        assertTrue(interactor.isExecuteCalled());
        assertEquals(profileInputData.getStudent(), interactor.getInputData().getStudent());
    }

}
