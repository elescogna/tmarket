package app;

import interface_adapter.ViewManagerModel;
import interface_adapter.contact.ContactViewModel;
import interface_adapter.contacting.ContactingController;
import interface_adapter.contacting.ContactingPresenter;
import interface_adapter.go_home.GoHomeController;
import interface_adapter.go_home.GoHomePresenter;
import interface_adapter.home.HomeViewModel;
import interface_adapter.view_item.ViewItemController;
import interface_adapter.view_item.ViewItemPresenter;
import interface_adapter.view_item.ViewItemViewModel;
import java.io.IOException;
import javax.swing.JOptionPane;
import use_case.contacting.ContactingInputBoundary;
import use_case.contacting.ContactingInteractor;
import use_case.contacting.ContactingOutputBoundary;
import use_case.go_home.GoHomeInputBoundary;
import use_case.go_home.GoHomeInteractor;
import use_case.go_home.GoHomeOutputBoundary;
import use_case.home.HomeDataAccessInterface;
import use_case.view_item.ViewItemDataAccessInterface;
import use_case.view_item.ViewItemInputBoundary;
import use_case.view_item.ViewItemInteractor;
import use_case.view_item.ViewItemOutputBoundary;
import view.ViewItemView;

public class ViewItemUseCaseFactory {

    /** Prevent instantiation. */
    private ViewItemUseCaseFactory() {}

    public static ViewItemView
        create(ViewManagerModel viewManagerModel, HomeViewModel homeViewModel,
                ViewItemViewModel viewItemViewModel, ContactViewModel contactViewModel,
                HomeDataAccessInterface clothingHomeDataAccessObject,
                HomeDataAccessInterface furnitureHomeDataAccessObject,
                HomeDataAccessInterface schoolItemHomeDataAccessObject,
                HomeDataAccessInterface technologyHomeDataAccessObject,
                ViewItemDataAccessInterface clothingViewItemDataAccessObject,
                ViewItemDataAccessInterface furnitureViewItemDataAccessObject,
                ViewItemDataAccessInterface schoolItemViewItemDataAccessObject,
                ViewItemDataAccessInterface technologyViewItemDataAccessObject) {

            try {
                ViewItemController viewItemController = createViewItemUseCase(
                        viewManagerModel, viewItemViewModel, homeViewModel,
                        clothingViewItemDataAccessObject, furnitureViewItemDataAccessObject,
                        schoolItemViewItemDataAccessObject,
                        technologyViewItemDataAccessObject);
                ContactingController contactingController =
                    createContactingUseCase(contactViewModel, viewManagerModel);
                GoHomeController goHomeController =
                    createGoHomeUseCase(viewManagerModel, homeViewModel);

                return new ViewItemView(viewItemViewModel, viewItemController,
                        goHomeController, contactingController);

            } catch (IOException e) {
                // TODO: what should this actually print out?
                JOptionPane.showMessageDialog(null, "Could not access Atlas Database.");
            }

            return null;
        }

    private static ContactingController
        createContactingUseCase(ContactViewModel contactViewModel,
                ViewManagerModel viewManagerModel) {
            ContactingOutputBoundary contactingOutputBoundary =
                new ContactingPresenter(contactViewModel, viewManagerModel);

            ContactingInputBoundary contactingInteractor =
                new ContactingInteractor(contactingOutputBoundary);

            return new ContactingController(contactingInteractor);
        }

    private static ViewItemController
        createViewItemUseCase(ViewManagerModel viewManagerModel,
                ViewItemViewModel viewItemViewModel,
                HomeViewModel homeViewModel,
                ViewItemDataAccessInterface clothingDataAccessObject,
                ViewItemDataAccessInterface furnitureDataAccessObject,
                ViewItemDataAccessInterface schoolItemDataAccessObject,
                ViewItemDataAccessInterface technologyDataAccessObject)
            throws IOException {

            // Pass this method's parameters to the Presenter.
            ViewItemOutputBoundary viewItemOutputBoundary = new ViewItemPresenter(
                    viewItemViewModel, viewManagerModel, homeViewModel);

            ViewItemInputBoundary viewItemInteractor = new ViewItemInteractor(
                    clothingDataAccessObject, furnitureDataAccessObject,
                    schoolItemDataAccessObject, technologyDataAccessObject,
                    viewItemOutputBoundary);

            return new ViewItemController(viewItemInteractor);
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
