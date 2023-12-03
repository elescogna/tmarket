package app;

import interface_adapter.ViewManagerModel;
import interface_adapter.home.HomeViewModel;
import interface_adapter.search_result.SearchResultViewModel;
import interface_adapter.view_item.ViewItemController;
import interface_adapter.view_item.ViewItemPresenter;
import interface_adapter.view_item.ViewItemViewModel;
import java.io.IOException;
import javax.swing.*;
import use_case.view_item.ViewItemDataAccessInterface;
import use_case.view_item.ViewItemInteractor;
import use_case.view_item.ViewItemOutputBoundary;
import view.SearchResultView;

public class SearchResultUseCaseFactory {

    /** Prevent instantiation. */
    private SearchResultUseCaseFactory() {}

    public static SearchResultView
        create(ViewManagerModel viewManagerModel, HomeViewModel homeViewModel,
                SearchResultViewModel searchResultViewModel,
                ViewItemViewModel viewItemViewModel,
                ViewItemDataAccessInterface clothingDataAccessObject,
                ViewItemDataAccessInterface furnitureDataAccessObject,
                ViewItemDataAccessInterface schoolItemDataAccessObject,
                ViewItemDataAccessInterface technologyDataAccessObject) {

            try {
                ViewItemController viewItemController = createViewItemUseCase(
                        viewItemViewModel, viewManagerModel, homeViewModel,
                        clothingDataAccessObject, furnitureDataAccessObject,
                        technologyDataAccessObject, schoolItemDataAccessObject);

                return new SearchResultView(homeViewModel, searchResultViewModel,
                        viewItemController, viewManagerModel);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Could not access Atlas Database.");
            }

            return null;
        }

    private static ViewItemController
        createViewItemUseCase(ViewItemViewModel viewItemViewModel,
                ViewManagerModel viewManagerModel,
                HomeViewModel homeViewModel,
                ViewItemDataAccessInterface clothingDataAccessObject,
                ViewItemDataAccessInterface furnitureDataAccessObject,
                ViewItemDataAccessInterface technologyDataAccessObject,
                ViewItemDataAccessInterface schoolItemDataAccessObject)
            throws IOException {
            ViewItemOutputBoundary viewItemOutputBoundary = new ViewItemPresenter(
                    viewItemViewModel, viewManagerModel, homeViewModel);

            ViewItemInteractor viewItemInteractor = new ViewItemInteractor(
                    clothingDataAccessObject, furnitureDataAccessObject,
                    schoolItemDataAccessObject, technologyDataAccessObject,
                    viewItemOutputBoundary);

            return new ViewItemController(viewItemInteractor);
        }
}
