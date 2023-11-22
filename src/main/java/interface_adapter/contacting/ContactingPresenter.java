package interface_adapter.contacting;
import interface_adapter.ViewManagerModel;
import interface_adapter.contact.ContactViewModel;
import use_case.contacting.ContactingOutputBoundary;
import use_case.contacting.ContactingOutputData;

public class ContactingPresenter implements ContactingOutputBoundary {
    private final ContactViewModel contactViewModel;
    private ViewManagerModel viewManagerModel;

    public ContactingPresenter(ContactViewModel contactViewModel,
            ViewManagerModel viewManagerModel) {
        this.contactViewModel = contactViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareContactView(ContactingOutputData viewItemOutputData) {
        contactViewModel.getState().setCurrentItem(
                viewItemOutputData.getItemToSell());
        // not firing state change because nothing needs to be done

        viewManagerModel.setActiveView(contactViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
