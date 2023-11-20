package interface_adapter.contact;

import entities.Item;
import use_case.contact.ContactInputBoundary;
import use_case.contact.ContactInputData;

public class ContactController {
    final ContactInputBoundary contactUseCaseInteractor;

    public ContactController(ContactInputBoundary contactUseCaseInteractor) {
        this.contactUseCaseInteractor = contactUseCaseInteractor;
    }

    public void execute(Item itemToSell, String subject, String body) {
        ContactInputData contactInputData =
            new ContactInputData(itemToSell, subject, body);

        contactUseCaseInteractor.execute(contactInputData);
    }
}
