package app;

import interface_adapter.ViewManagerModel;
import interface_adapter.go_home.GoHomeController;
import interface_adapter.home.HomeController;
import interface_adapter.home.HomePresenter;
import interface_adapter.home.HomeViewModel;
import interface_adapter.view_item.ViewItemController;
import interface_adapter.view_item.ViewItemPresenter;
import interface_adapter.view_item.ViewItemViewModel;
import java.io.IOException;
import javax.swing.*;
import use_case.home.HomeDataAccessInterface;
import use_case.home.HomeInputBoundary;
import use_case.home.HomeInteractor;
import use_case.home.HomeOutputBoundary;
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
                ViewItemViewModel viewItemViewModel, GoHomeController goHomeController,
                HomeDataAccessInterface clothingHomeDataAccessObject,
                HomeDataAccessInterface furnitureHomeDataAccessObject,
                HomeDataAccessInterface orderHomeDataAccessObject,
                HomeDataAccessInterface schoolItemHomeDataAccessObject,
                HomeDataAccessInterface technologyHomeDataAccessObject,
                ViewItemDataAccessInterface clothingViewItemDataAccessObject,
                ViewItemDataAccessInterface furnitureViewItemDataAccessObject,
                ViewItemDataAccessInterface orderViewItemDataAccessObject,
                ViewItemDataAccessInterface schoolItemViewItemDataAccessObject,
                ViewItemDataAccessInterface technologyViewItemDataAccessObject) {

            try {
                HomeController homeController = createHomeUseCase(
                        viewManagerModel, homeViewModel, clothingHomeDataAccessObject,
                        furnitureHomeDataAccessObject, schoolItemHomeDataAccessObject,
                        technologyHomeDataAccessObject);
                ViewItemController viewItemController = createViewItemUseCase(
                        viewManagerModel, viewItemViewModel, clothingViewItemDataAccessObject,
                        furnitureViewItemDataAccessObject, schoolItemViewItemDataAccessObject,
                        technologyViewItemDataAccessObject);
                return new ViewItemView(homeController, viewItemController,
                        goHomeController);
            } catch (IOException e) {
                // TODO: what should this actually print out?
                JOptionPane.showMessageDialog(null, "Could not access Atlas Database.");
            }

            return null;
        }

    private static HomeController createHomeUseCase(
            ViewManagerModel viewManagerModel, HomeViewModel homeViewModel,
            HomeDataAccessInterface clothingDataAccessObject,
            HomeDataAccessInterface furnitureDataAccessObject,
            HomeDataAccessInterface schoolItemDataAccessObject,
            HomeDataAccessInterface technologyDataAccessObject) throws IOException {

        // Pass this method's parameters to the Presenter.
        HomeOutputBoundary homeOutputBoundary =
            new HomePresenter(viewManagerModel, homeViewModel);

        HomeInputBoundary homeInteractor =
            new HomeInteractor(clothingDataAccessObject, furnitureDataAccessObject,
                    schoolItemDataAccessObject,
                    technologyDataAccessObject, homeOutputBoundary);

        return new HomeController(homeInteractor);
            }

    private static ViewItemController
        createViewItemUseCase(ViewManagerModel viewManagerModel,
                ViewItemViewModel viewItemViewModel,
                ViewItemDataAccessInterface clothingDataAccessObject,
                ViewItemDataAccessInterface furnitureDataAccessObject,
                ViewItemDataAccessInterface schoolItemDataAccessObject,
                ViewItemDataAccessInterface technologyDataAccessObject)
            throws IOException {

            // Pass this method's parameters to the Presenter.
            ViewItemOutputBoundary viewItemOutputBoundary =
                new ViewItemPresenter(viewManagerModel, viewItemViewModel);

            ViewItemInputBoundary viewItemInteractor = new ViewItemInteractor(
                    clothingDataAccessObject, furnitureDataAccessObject,
                    schoolItemDataAccessObject, technologyDataAccessObject,
                    viewItemOutputBoundary);

            return new ViewItemController(viewItemInteractor);
        }
}
