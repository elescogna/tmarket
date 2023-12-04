package interface_adapter.contact;

import entities.Furniture;
import entities.Item;
import org.junit.jupiter.api.Test;
import use_case.contact.ContactInputBoundary;
import use_case.contact.ContactInputData;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MockContactInteractor implements ContactInputBoundary {
    private boolean executeCalled = false;
    private ContactInputData inputData;

    @Override
    public void execute(ContactInputData input) {
        this.executeCalled = true;
        this.inputData = input;
    }

    public boolean isExecuteCalled() {
        return executeCalled;
    }

    public ContactInputData getInputData() {
        return inputData;
    }
}

class ContactControllerTest {

    @Test
    void executeTest() {
        // Create a mock implementation of ContactInputBoundary
        MockContactInteractor mockContactInteractor = new MockContactInteractor();

        // Create ContactController with the mock ContactInputBoundary
        ContactController contactController = new ContactController(mockContactInteractor);

        // Call the execute method on ContactController
        Item itemToSell = new Furniture("Chair", "Vintage Teak Chair", 4, 100, 10, false, "91 Charles Street", "", "chair", "", LocalDateTime.now(), 10.0, 10.0, 10.0 );
        String subject = "Interested in buying";
        String body = "I'm interested in purchasing the laptop. Please provide more details.";

        contactController.execute(itemToSell, subject, body);

        // Verify that the execute method of ContactInputBoundary is called with the expected ContactInputData
        assertTrue(mockContactInteractor.isExecuteCalled());
        ContactInputData expectedInputData = new ContactInputData(itemToSell, subject, body);
        assertEquals(expectedInputData.getItemToSell(), mockContactInteractor.getInputData().getItemToSell());
        assertEquals(expectedInputData.getSubject(), mockContactInteractor.getInputData().getSubject());
        assertEquals(expectedInputData.getBody(), mockContactInteractor.getInputData().getBody());
    }
}
