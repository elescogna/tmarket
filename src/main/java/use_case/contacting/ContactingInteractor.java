package use_case.contacting;

public class ContactingInteractor implements ContactingInputBoundary {
    ContactingOutputBoundary contactingPresenter;

    public ContactingInteractor(ContactingOutputBoundary contactingPresenter) {
        this.contactingPresenter = contactingPresenter;
    }

    @Override
    public void execute(ContactingInputData contactingData) {
        contactingPresenter.prepareContactView(
                new ContactingOutputData(contactingData.getItemToSell()));
    }
}
