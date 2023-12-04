package interface_adapter.contact;

import interface_adapter.ViewManagerModel;
import interface_adapter.home.HomeViewModel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ContactPresenterTest {

    @Test
    void prepareSuccessViewTest() {
        // Create instances of classes
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        ContactViewModel contactViewModel = new ContactViewModel();
        HomeViewModel homeViewModel = new HomeViewModel();

        // Create ContactPresenter
        ContactPresenter contactPresenter = new ContactPresenter(contactViewModel, viewManagerModel, homeViewModel);

        // Call the method to be tested
        contactPresenter.prepareSuccessView();

        // Verify the expected state changes
        assertEquals("", contactViewModel.getState().getError());
        assertEquals(contactViewModel.getState(), contactViewModel.getState());
        assertEquals(homeViewModel.getViewName(), viewManagerModel.getActiveView());
    }

    @Test
    void prepareFailViewTest() {
        // Create instances of classes
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        ContactViewModel contactViewModel = new ContactViewModel();
        HomeViewModel homeViewModel = new HomeViewModel();

        // Create ContactPresenter
        ContactPresenter contactPresenter = new ContactPresenter(contactViewModel, viewManagerModel, homeViewModel);

        // Call the method to be tested
        contactPresenter.prepareFailView("Error sending message");

        // Verify the expected state changes
        assertEquals("Error sending message", contactViewModel.getState().getError());
        assertEquals(contactViewModel.getState(), contactViewModel.getState());
    }
}
