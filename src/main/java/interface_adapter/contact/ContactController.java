package interface_adapter.contact;

import entities.Item;
import use_case.contact.ContactInputBoundary;
import use_case.contact.ContactInputData;

public class ContactController {
    final ContactInputBoundary contactUseCaseInteractor;

    /**
     * Constructor for the ContactController class.
     *
     * @param contactInteractor the ContactInteractor with which to make the ContactController
     */
    public ContactController(ContactInputBoundary contactInteractor) {
        this.contactUseCaseInteractor = contactInteractor;
    }

    /**
     * Creates input data from the parameters given and calls the ContactInteractor with that data.
     *
     * @param itemToSell the item with which to call the ContactInteractor
     * @param subject the email subject with which to call the ContactInteractor
     * @param body the email body with which to call the ContactInteractor
     */
    public void execute(Item itemToSell, String subject, String body) {
        ContactInputData contactInputData =
            new ContactInputData(itemToSell, subject, body);

        contactUseCaseInteractor.execute(contactInputData);
    }
}
