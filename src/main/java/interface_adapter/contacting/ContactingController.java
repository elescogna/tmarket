package interface_adapter.contacting;

import entities.Item;
import use_case.contacting.ContactingInputBoundary;
import use_case.contacting.ContactingInputData;

public class ContactingController {
    final ContactingInputBoundary contactingInteractor;

    public ContactingController(ContactingInputBoundary contactingInteractor) {
        this.contactingInteractor = contactingInteractor;
    }

    public void execute(Item itemToSell) {
        ContactingInputData contactingInputData =
            new ContactingInputData(itemToSell);

        this.contactingInteractor.execute(contactingInputData);
    }
}
