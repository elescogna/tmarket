package app;

import interface_adapter.ViewManagerModel;
import interface_adapter.home.HomeViewModel;
import interface_adapter.profile.ProfileController;
import interface_adapter.profile.ProfilePresenter;
import interface_adapter.profile.ProfileViewModel;
import interface_adapter.view_item.ViewItemController;
import interface_adapter.view_item.ViewItemPresenter;
import interface_adapter.view_item.ViewItemViewModel;
import interface_adapter.view_order.ViewOrderController;
import interface_adapter.view_order.ViewOrderPresenter;
import interface_adapter.view_order.ViewOrderViewModel;
import java.io.IOException;
import javax.swing.*;
import use_case.profile.ProfileDataAccessInterface;
import use_case.profile.ProfileInputBoundary;
import use_case.profile.ProfileInteractor;
import use_case.profile.ProfileOutputBoundary;
import use_case.view_order.ViewOrderDataAccessInterface;
import use_case.view_order.ViewOrderInteractor;
import use_case.view_order.ViewOrderOutputBoundary;
import view.ProfileView;

public class ProfileUseCaseFactory {
    private ProfileUseCaseFactory() {}
    public static ProfileView
        create(ProfileViewModel profileViewModel, ViewManagerModel viewManagerModel,
                HomeViewModel homeViewModel, ViewOrderViewModel viewOrderViewModel,
                ViewItemViewModel viewItemViewModel,
                ProfileDataAccessInterface studentDataAccessObject,
                ProfileDataAccessInterface clothingDataAccessObject,
                ProfileDataAccessInterface furnitureDataAccessObject,
                ProfileDataAccessInterface schoolItemDataAccessObject,
                ProfileDataAccessInterface technologyDataAccessObject,
                ProfileDataAccessInterface profileOrderDataAccessInterface,
<<<<<<< HEAD
                ViewOrderDataAccessInterface viewOrderDataAccessObject,
                ViewItemDataAccessInterface clothingViewItemDataAccessObject,
                ViewItemDataAccessInterface furnitureViewItemDataAccessObject,
                ViewItemDataAccessInterface schoolItemViewItemDataAccessObject,
                ViewItemDataAccessInterface technologyViewItemDataAccessObject
                ) {
=======
                ViewOrderDataAccessInterface viewOrderDataAccessObject) {
>>>>>>> c6d67e0 (chore(remove-dummy-use-cases): removed go home use case)
            try {
                ProfileController profileController = createProfileUseCase(
                        viewManagerModel, profileViewModel, studentDataAccessObject,
                        clothingDataAccessObject, furnitureDataAccessObject,
                        schoolItemDataAccessObject, technologyDataAccessObject,
                        profileOrderDataAccessInterface);
                ViewOrderController viewOrderController =
<<<<<<< HEAD
                    createViewOrderUseCase(viewOrderViewModel,
                            viewManagerModel, homeViewModel,
                            viewOrderDataAccessObject);
                ViewItemController viewItemController =
                        createViewItemUseCase(viewItemViewModel, viewManagerModel, homeViewModel,
                                clothingViewItemDataAccessObject, furnitureViewItemDataAccessObject,
                                technologyViewItemDataAccessObject, schoolItemViewItemDataAccessObject);

                return new ProfileView(profileController, profileViewModel,
                        goHomeController, viewOrderController, viewItemController);
=======
                    createViewOrderUseCase(viewOrderViewModel, viewManagerModel,
                            homeViewModel, viewOrderDataAccessObject);

                return new ProfileView(profileController, profileViewModel, homeViewModel,
                        viewOrderController, viewManagerModel);
>>>>>>> c6d67e0 (chore(remove-dummy-use-cases): removed go home use case)
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Could not access Atlas Database.");
            }

            return null;
        }

<<<<<<< HEAD
    private static ViewItemController createViewItemUseCase(
            ViewItemViewModel viewItemViewModel, ViewManagerModel viewManagerModel,
            HomeViewModel homeViewModel,
            ViewItemDataAccessInterface clothingDataAccessObject,
            ViewItemDataAccessInterface furnitureDataAccessObject,
            ViewItemDataAccessInterface technologyDataAccessObject,
            ViewItemDataAccessInterface schoolItemDataAccessObject) {
        ViewItemOutputBoundary viewItemOutputBoundary = new ViewItemPresenter(
                viewItemViewModel, viewManagerModel, homeViewModel);

        ViewItemInteractor viewItemInteractor = new ViewItemInteractor(
                clothingDataAccessObject, furnitureDataAccessObject,
                schoolItemDataAccessObject, technologyDataAccessObject,
                viewItemOutputBoundary);

        return new ViewItemController(viewItemInteractor);
    }

    private static ViewOrderController createViewOrderUseCase(
            ViewOrderViewModel viewOrderViewModel, ViewManagerModel viewManagerModel,
            HomeViewModel homeViewModel,
            ViewOrderDataAccessInterface orderDataAccessObject) {
=======
    private static ViewOrderController
        createViewOrderUseCase(ViewOrderViewModel viewOrderViewModel,
                ViewManagerModel viewManagerModel,
                HomeViewModel homeViewModel,
                ViewOrderDataAccessInterface orderDataAccessObject) {
>>>>>>> c6d67e0 (chore(remove-dummy-use-cases): removed go home use case)

            ViewOrderOutputBoundary viewOrderPresenter = new ViewOrderPresenter(
                    viewOrderViewModel, viewManagerModel, homeViewModel);

            ViewOrderInteractor viewOrderInteractor =
                new ViewOrderInteractor(orderDataAccessObject, viewOrderPresenter);

            return new ViewOrderController(viewOrderInteractor);
        }

    private static ProfileController createProfileUseCase(
            ViewManagerModel viewManagerModel, ProfileViewModel profileViewModel,
            ProfileDataAccessInterface studentDataAccessInterface,
            ProfileDataAccessInterface clothingDataAccessInterface,
            ProfileDataAccessInterface furnitureDataAccessInterface,
            ProfileDataAccessInterface schoolItemDataAccessInterface,
            ProfileDataAccessInterface technologyDataAccessInterface,
            ProfileDataAccessInterface orderDataAccessInterface) throws IOException {
        ProfileOutputBoundary profileOutputBoundary =
            new ProfilePresenter(viewManagerModel, profileViewModel);
        ProfileInputBoundary profileInteractor = new ProfileInteractor(
                studentDataAccessInterface, clothingDataAccessInterface,
                furnitureDataAccessInterface, schoolItemDataAccessInterface,
                technologyDataAccessInterface, orderDataAccessInterface,
                profileOutputBoundary);

        return new ProfileController(profileInteractor);
            }
}
