package interface_adapter.view_order;

import org.junit.jupiter.api.Test;
import use_case.view_order.ViewOrderInputBoundary;
import use_case.view_order.ViewOrderInputData;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MockViewOrderInteractor implements ViewOrderInputBoundary {
    private boolean executeCalled = false;
    private ViewOrderInputData inputData;

    @Override
    public void execute(ViewOrderInputData input) {
        this.executeCalled = true;
        this.inputData = input;
    }

    public boolean isExecuteCalled() {
        return executeCalled;
    }

    public ViewOrderInputData getInputData() {
        return inputData;
    }
}

class ViewOrderControllerTest {

    @Test
    void executeTest() {
        // Create a mock implementation of ViewOrderInputBoundary
        MockViewOrderInteractor mockViewOrderInteractor = new MockViewOrderInteractor();

        // Create ViewOrderController with the mock ViewOrderInputBoundary
        ViewOrderController viewOrderController = new ViewOrderController(mockViewOrderInteractor);

        // Call the execute method on ViewOrderController
        String orderId = "12345";
        String studentEmail = "john@example.com";
        String studentAddress = "123 Main St";

        viewOrderController.execute(orderId, studentEmail, studentAddress);

        // Verify that the execute method of ViewOrderInputBoundary is called with the expected ViewOrderInputData
        assertTrue(mockViewOrderInteractor.isExecuteCalled());
        ViewOrderInputData expectedInputData = new ViewOrderInputData(orderId, studentEmail, studentAddress);
        assertEquals(expectedInputData.getOrderId(), mockViewOrderInteractor.getInputData().getOrderId());
        assertEquals(expectedInputData.getStudentEmail(), mockViewOrderInteractor.getInputData().getStudentEmail());
        assertEquals(expectedInputData.getStudentAddress(), mockViewOrderInteractor.getInputData().getStudentAddress());
    }
}
