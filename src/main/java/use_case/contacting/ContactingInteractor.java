package use_case.contacting;

import interface_adapter.contacting.ContactingPresenter;

public class ContactingInteractor implements ContactingInputBoundary {
    ContactingOutputBoundary contactingPresenter;

    public ContactingInteractor(ContactingPresenter contactingPresenter) {
        this.contactingPresenter = contactingPresenter;
    }

    @Override
    public void execute(ContactingInputData contactingData) {
        contactingPresenter.prepareContactView(
                new ContactingOutputData(contactingData.getItemToSell()));
    }
}
