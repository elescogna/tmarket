package app;

import interface_adapter.ViewManagerModel;
import interface_adapter.go_home.GoHomeController;
import interface_adapter.go_home.GoHomePresenter;
import interface_adapter.home.HomeViewModel;
import interface_adapter.search_result.SearchResultViewModel;
import interface_adapter.view_item.ViewItemViewModel;
import java.io.IOException;
import javax.swing.*;
import use_case.go_home.GoHomeInputBoundary;
import use_case.go_home.GoHomeInteractor;
import use_case.go_home.GoHomeOutputBoundary;
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

                return new SearchResultView(goHomeController, searchResultViewModel);
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
}
