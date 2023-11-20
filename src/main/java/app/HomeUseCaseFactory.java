package app;

import interface_adapter.ViewManagerModel;
import interface_adapter.home.HomeController;
import interface_adapter.home.HomePresenter;
import interface_adapter.home.HomeViewModel;
import interface_adapter.viewing_item.ViewingItemController;
import java.io.IOException;
import javax.swing.*;
import use_case.home.HomeDataAccessInterface;
import use_case.home.HomeInputBoundary;
import use_case.home.HomeInteractor;
import use_case.home.HomeOutputBoundary;
import view.HomeView;

public class HomeUseCaseFactory {

    /** Prevent instantiation. */
    private HomeUseCaseFactory() {}

    public static HomeView
    create(ViewManagerModel viewManagerModel, HomeViewModel homeViewModel,
           ViewingItemController viewingItemController,
           HomeDataAccessInterface clothingDataAccessObject,
           HomeDataAccessInterface furnitureDataAccessObject,
           HomeDataAccessInterface schoolItemDataAccessInterface,
           HomeDataAccessInterface technologyDataAccessInterface) {

        try {
            HomeController homeController = createHomeUseCase(
                    viewManagerModel, homeViewModel, clothingDataAccessObject,
                    furnitureDataAccessObject, schoolItemDataAccessInterface,
                    technologyDataAccessInterface);
            return new HomeView(homeViewModel, homeController, viewingItemController);
        } catch (IOException e) {
            // TODO: what should this actually print out?
            JOptionPane.showMessageDialog(null, "Could not access Atlas Database.");
        }

        return null;
    }

    private static HomeController
    createHomeUseCase(ViewManagerModel viewManagerModel,
                      HomeViewModel homeViewModel,
                      HomeDataAccessInterface clothingDataAccessObject,
                      HomeDataAccessInterface furnitureDataAccessObject,
                      HomeDataAccessInterface schoolItemDataAccessInterface,
                      HomeDataAccessInterface technologyDataAccessInterface)
            throws IOException {

        // Pass this method's parameters to the Presenter.
        HomeOutputBoundary homeOutputBoundary =
                new HomePresenter(viewManagerModel, homeViewModel);

        HomeInputBoundary homeInteractor =
                new HomeInteractor(clothingDataAccessObject, furnitureDataAccessObject,
                        schoolItemDataAccessInterface,
                        technologyDataAccessInterface, homeOutputBoundary);

        return new HomeController(homeInteractor);
    }
}