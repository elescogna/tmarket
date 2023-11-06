package app;

import use_case.HomeDataAccessInterface;
import view.HomeView;

import javax.swing.*;
import java.io.IOException;

public class HomeUseCaseFactory {

    /** Prevent instantiation. */
    private HomeUseCaseFactory() {}

    public static HomeView create(
            ViewManagerModel viewManagerModel,
            HomeViewModel homeViewModel,
            HomeDataAccessInterface homeDataAccessObject) {

        // TODO: do we really need to have a try catch in this case?
        try {
            HomeController homeController = createHomeUseCase(viewManagerModel, homeViewModel, homeDataAccessObject);
            return new HomeView(homeViewModel, homeController);
        } catch (IOException e) {
            // TODO: what should this actually print out?
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }

        return null;
    }

    private static HomeController createHomeUseCase(
            ViewManagerModel viewManagerModel,
            HomeViewModel homeViewModel,
            HomeDataAccessInterface homeDataAccessObject) throws IOException {

        // Pass this method's parameters to the Presenter.
        HomeOutputBoundary homeOutputBoundary = new HomePresenter(viewManagerModel, homeViewModel);

        HomeInputBoundary homeInteractor = new HomeInteractor(
                homeDataAccessObject, homeOutputBoundary);

        return new HomeController(homeInteractor);
    }
}
