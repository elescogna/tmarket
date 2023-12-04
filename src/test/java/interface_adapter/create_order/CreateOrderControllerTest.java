package interface_adapter.create_order;

import entities.Furniture;
import entities.Item;
import entities.Student;
import org.junit.jupiter.api.Test;
import use_case.create_order.CreateOrderInputBoundary;
import use_case.create_order.CreateOrderInputData;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MockCreateOrderInteractor implements CreateOrderInputBoundary {
    private boolean executeCalled = false;
    private CreateOrderInputData inputData;

    @Override
    public void execute(CreateOrderInputData input) {
        this.executeCalled = true;
        this.inputData = input;
    }

    public boolean isExecuteCalled() {
        return executeCalled;
    }

    public CreateOrderInputData getInputData() {
        return inputData;
    }
}

class CreateOrderControllerTest {

    @Test
    void executeTest() {
        // Create a mock implementation of CreateOrderInputBoundary
        MockCreateOrderInteractor mockCreateOrderInteractor = new MockCreateOrderInteractor();

        // Create CreateOrderController with the mock CreateOrderInputBoundary
        CreateOrderController createOrderController = new CreateOrderController(mockCreateOrderInteractor);

        // Call the execute method on CreateOrderController
        Item item = new Furniture("Chair", "Vintage Teak Chair", 4, 100, 10, false, "91 Charles Street", "", "chair", "", LocalDateTime.now(), 10.0, 10.0, 10.0 );
        Student student = new Student("john", "password", "123 Main St", "john@example.com");
        String buyerEmail = "buyer@example.com";
        String sameAddress = "Yes";
        String otherAddress = "456 Second St";

        createOrderController.execute(item, student, buyerEmail, sameAddress, otherAddress);

        // Verify that the execute method of CreateOrderInputBoundary is called with the expected CreateOrderInputData
        assertTrue(mockCreateOrderInteractor.isExecuteCalled());
        CreateOrderInputData expectedInputData = new CreateOrderInputData(item, student, buyerEmail, sameAddress, otherAddress);
        assertEquals(expectedInputData.getItem(), mockCreateOrderInteractor.getInputData().getItem());
        assertEquals(expectedInputData.getStudent(), mockCreateOrderInteractor.getInputData().getStudent());
        assertEquals(expectedInputData.getBuyerEmail(), mockCreateOrderInteractor.getInputData().getBuyerEmail());
        assertEquals(expectedInputData.getSameAddress(), mockCreateOrderInteractor.getInputData().getSameAddress());
        assertEquals(expectedInputData.getOtherAddress(), mockCreateOrderInteractor.getInputData().getOtherAddress());
    }
}
