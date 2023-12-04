package interface_adapter.home;

import org.junit.jupiter.api.Test;
import use_case.home.HomeInputBoundary;
import use_case.home.HomeInputData;

import static org.junit.jupiter.api.Assertions.*;

class MockHomeInteractor implements HomeInputBoundary {
    private boolean isExecuteCalled = false;
    private HomeInputData inputData;
    @Override
    public void execute(HomeInputData inputData) {
        this.isExecuteCalled = true;
        this.inputData = inputData;
    }

    public HomeInputData getInputData() {
        return inputData;
    }

    public boolean isExecuteCalled() {
        return isExecuteCalled;
    }
}

public class HomeControllerTest {
    @Test
    void successTest() {
        MockHomeInteractor interactor = new MockHomeInteractor();
        HomeController controller = new HomeController(interactor);

        HomeInputData homeInputData = new HomeInputData();

        controller.execute();
        assertTrue(interactor.isExecuteCalled());
    }
}


