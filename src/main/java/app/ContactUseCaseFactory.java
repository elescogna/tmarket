package app;

import interface_adapter.ViewManagerModel;
import interface_adapter.contact.ContactController;
import interface_adapter.contact.ContactPresenter;
import interface_adapter.contact.ContactViewModel;
import interface_adapter.go_home.GoHomeController;
import interface_adapter.go_home.GoHomePresenter;
import interface_adapter.home.HomeViewModel;
import use_case.contact.ContactDataAccessInterface;
import use_case.contact.ContactInputBoundary;
import use_case.contact.ContactInteractor;
import use_case.contact.ContactOutputBoundary;
import use_case.go_home.GoHomeInputBoundary;
import use_case.go_home.GoHomeInteractor;
import use_case.go_home.GoHomeOutputBoundary;
import view.ContactView;

public class ContactUseCaseFactory {

    /** Prevent instantiation. */
    private ContactUseCaseFactory() {}

    public static ContactView
        create(ContactViewModel contactViewModel, ViewManagerModel viewManagerModel,
                HomeViewModel homeViewModel,
                ContactDataAccessInterface studentDataAccessObject) {

            ContactController contactController =
                createContactUseCase(contactViewModel, viewManagerModel, homeViewModel,
                        studentDataAccessObject);
            GoHomeController goHomeController =
                createGoHomeUseCase(viewManagerModel, homeViewModel);

            return new ContactView(contactViewModel, contactController,
                    goHomeController);
        }

    private static ContactController
        createContactUseCase(ContactViewModel contactViewModel,
                ViewManagerModel viewManagerModel,
                HomeViewModel homeViewModel,
                ContactDataAccessInterface studentDataAccessObject) {
            ContactOutputBoundary contactOutputBoundary =
                new ContactPresenter(contactViewModel, viewManagerModel, homeViewModel);

            ContactInputBoundary contactInteractor =
                new ContactInteractor(contactOutputBoundary, studentDataAccessObject);

            return new ContactController(contactInteractor);
        }

    private static GoHomeController
        createGoHomeUseCase(ViewManagerModel viewManagerModel,
                HomeViewModel homeViewModel) {
            GoHomeOutputBoundary goHomeOutputBoundary =
                new GoHomePresenter(viewManagerModel, homeViewModel);

            GoHomeInputBoundary goHomeInteractor =
                new GoHomeInteractor(goHomeOutputBoundary);

            return new GoHomeController(goHomeInteractor);
        }
}
