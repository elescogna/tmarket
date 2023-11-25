package app;

import interface_adapter.ViewManagerModel;
import interface_adapter.go_home.GoHomeController;
import interface_adapter.go_home.GoHomePresenter;
import interface_adapter.home.HomeViewModel;
import interface_adapter.search_result.SearchResultViewModel;
import interface_adapter.view_item.ViewItemViewModel;
import interface_adapter.viewing_item.ViewingItemController;
import interface_adapter.viewing_item.ViewingItemPresenter;
import interface_adapter.viewing_item.ViewingItemViewModel;
import java.io.IOException;
import javax.swing.*;
import use_case.go_home.GoHomeInputBoundary;
import use_case.go_home.GoHomeInteractor;
import use_case.go_home.GoHomeOutputBoundary;
import use_case.viewing_item.ViewingItemInputBoundary;
import use_case.viewing_item.ViewingItemInteractor;
import use_case.viewing_item.ViewingItemOutputBoundary;
import view.SearchResultView;

public class SearchResultUseCaseFactory {

    /** Prevent instantiation. */
    private SearchResultUseCaseFactory() {}

    public static SearchResultView
        create(ViewManagerModel viewManagerModel, HomeViewModel homeViewModel,
                SearchResultViewModel searchResultViewModel,
                ViewItemViewModel viewItemViewModel) {

            try {
                GoHomeController goHomeController =
                    createSearchResultUseCase(viewManagerModel, homeViewModel);
                ViewingItemController viewingItemController =
                    createViewingItemUseCase(viewItemViewModel,
                            viewManagerModel);

                return new SearchResultView(goHomeController, searchResultViewModel,
                        viewingItemController);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Could not access Atlas Database.");
            }

            return null;
        }

    private static GoHomeController
        createSearchResultUseCase(ViewManagerModel viewManagerModel,
                HomeViewModel homeViewModel) throws IOException {
            // Pass this method's parameters to the Presenter.
            GoHomeOutputBoundary goHomeOutputBoundary =
                new GoHomePresenter(viewManagerModel, homeViewModel);

            GoHomeInputBoundary goHomeInteractor =
                new GoHomeInteractor(goHomeOutputBoundary);

            return new GoHomeController(goHomeInteractor);
        }

    private static ViewingItemController
        createViewingItemUseCase(ViewItemViewModel viewingItemViewModel,
                ViewManagerModel viewManagerModel) {
            ViewingItemOutputBoundary viewingItemOutputBoundary =
                new ViewingItemPresenter(viewingItemViewModel, viewManagerModel);

            ViewingItemInputBoundary viewingItemInteractor =
                new ViewingItemInteractor(viewingItemOutputBoundary);

            return new ViewingItemController(viewingItemInteractor);
        }
}
