package interface_adapter.view_item;

import entities.Student;
import org.junit.jupiter.api.Test;
import use_case.view_item.ViewItemInputBoundary;
import use_case.view_item.ViewItemInputData;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MockViewItemInteractor implements ViewItemInputBoundary {
    private boolean executeCalled = false;
    private ViewItemInputData inputData;

    @Override
    public void execute(ViewItemInputData input) {
        this.executeCalled = true;
        this.inputData = input;
    }

    public boolean isExecuteCalled() {
        return executeCalled;
    }

    public ViewItemInputData getInputData() {
        return inputData;
    }
}

class ViewItemControllerTest {

    @Test
    void executeTest() {
        // Create a mock implementation of ViewItemInputBoundary
        MockViewItemInteractor mockViewItemInteractor = new MockViewItemInteractor();

        // Create ViewItemController with the mock ViewItemInputBoundary
        ViewItemController viewItemController = new ViewItemController(mockViewItemInteractor);

        // Call the execute method on ViewItemController
        String itemId = "12345";
        Student currentStudent = new Student("clary", "clary", "Art Gallery of Ontario", "clary@mail.utoronto.ca");

        viewItemController.execute(itemId, currentStudent);

        // Verify that the execute method of ViewItemInputBoundary is called with the expected ViewItemInputData
        assertTrue(mockViewItemInteractor.isExecuteCalled());
        ViewItemInputData expectedInputData = new ViewItemInputData(itemId, currentStudent);
        assertEquals(expectedInputData.getItemId(), mockViewItemInteractor.getInputData().getItemId());
        assertEquals(expectedInputData.getCurrentStudent(), mockViewItemInteractor.getInputData().getCurrentStudent());
    }
}
