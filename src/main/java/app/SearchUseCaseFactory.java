package app;

import interface_adapter.ViewManagerModel;
import interface_adapter.search.SearchController;
import interface_adapter.search.SearchPresenter;
import use_case.home.HomeDataAccessInterface;
import use_case.search.SearchInputBoundary;
import use_case.search.SearchOutputBoundary;
import javax.swing.*;
import java.io.IOException;

public class SearchUseCaseFactory {



    /** Prevent instantiation. */
    private SearchUseCaseFactory() {}

    public static SearchView
    create(ViewManagerModel viewManagerModel, SearchViewModel searchViewModel,
           HomeDataAccessInterface clothingDataAccessObject,
           HomeDataAccessInterface furnitureDataAccessObject,
           HomeDataAccessInterface orderDataAccessObject,
           HomeDataAccessInterface schoolItemDataAccessInterface,
           HomeDataAccessInterface technologyDataAccessInterface) {

        try {
            SearchController searchController = createSearchUseCase(
                    viewManagerModel, searchViewModel, clothingDataAccessObject,
                    furnitureDataAccessObject, orderDataAccessObject, schoolItemDataAccessInterface,
                    technologyDataAccessInterface);
            return new SearchView(searchController);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not access Atlas Database.");
        }

        return null;
    }

    private static SearchController createSearchUseCase(ViewManagerModel viewManagerModel,
                      SearchViewModel searchViewModel,
                      HomeDataAccessInterface clothingDataAccessObject,
                      HomeDataAccessInterface furnitureDataAccessObject,
                      HomeDataAccessInterface orderDataAccessObject,
                      HomeDataAccessInterface schoolItemDataAccessInterface,
                      HomeDataAccessInterface technologyDataAccessInterface)
            throws IOException {

        // Pass this method's parameters to the Presenter.
        SearchOutputBoundary searchOutputBoundary =
                new SearchPresenter(viewManagerModel, searchViewModel);

        SearchInputBoundary searchInteractor =
                new SearchInteractor(clothingDataAccessObject, furnitureDataAccessObject,
                        orderDataAccessObject,
                        schoolItemDataAccessInterface,
                        technologyDataAccessInterface, searchOutputBoundary);

        return new SearchController(searchInteractor);
    }
}
