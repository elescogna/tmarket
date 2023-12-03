package app;

import interface_adapter.ViewManagerModel;
import interface_adapter.go_home.GoHomeController;
import interface_adapter.go_home.GoHomePresenter;
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
import use_case.go_home.GoHomeInputBoundary;
import use_case.go_home.GoHomeInteractor;
import use_case.go_home.GoHomeOutputBoundary;
import use_case.profile.ProfileDataAccessInterface;
import use_case.profile.ProfileInputBoundary;
import use_case.profile.ProfileInteractor;
import use_case.profile.ProfileOutputBoundary;
import use_case.view_item.ViewItemDataAccessInterface;
import use_case.view_item.ViewItemInteractor;
import use_case.view_item.ViewItemOutputBoundary;
import use_case.view_order.ViewOrderInteractor;
import use_case.view_order.ViewOrderDataAccessInterface;
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
                ViewOrderDataAccessInterface viewOrderDataAccessObject,
                ViewItemDataAccessInterface clothingViewItemDataAccessObject,
                ViewItemDataAccessInterface furnitureViewItemDataAccessObject,
                ViewItemDataAccessInterface schoolItemViewItemDataAccessObject,
                ViewItemDataAccessInterface technologyViewItemDataAccessObject
                ) {
            try {
                ProfileController profileController = createProfileUseCase(
                        viewManagerModel, profileViewModel, studentDataAccessObject,
                        clothingDataAccessObject, furnitureDataAccessObject,
                        schoolItemDataAccessObject, technologyDataAccessObject,
                        profileOrderDataAccessInterface);
                GoHomeController goHomeController =
                    createGoHomeUseCase(viewManagerModel, homeViewModel);
                ViewOrderController viewOrderController =
                    createViewOrderUseCase(viewOrderViewModel,
                            viewManagerModel, homeViewModel,
                            viewOrderDataAccessObject);
                ViewItemController viewItemController =
                        createViewItemUseCase(viewItemViewModel, viewManagerModel, homeViewModel,
                                clothingViewItemDataAccessObject, furnitureViewItemDataAccessObject,
                                technologyViewItemDataAccessObject, schoolItemViewItemDataAccessObject);

                return new ProfileView(profileController, profileViewModel,
                        goHomeController, viewOrderController, viewItemController);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Could not access Atlas Database.");
            }

            return null;
        }

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

        ViewOrderOutputBoundary viewOrderPresenter = new ViewOrderPresenter(
                viewOrderViewModel, viewManagerModel, homeViewModel);

        ViewOrderInteractor viewOrderInteractor =
            new ViewOrderInteractor(orderDataAccessObject, viewOrderPresenter);

        return new ViewOrderController(viewOrderInteractor);
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
