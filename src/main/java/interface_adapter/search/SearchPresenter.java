package interface_adapter.search;

import interface_adapter.ViewManagerModel;
import interface_adapter.search_result.SearchResultState;
import interface_adapter.search_result.SearchResultViewModel;
import use_case.search.SearchOutputBoundary;
import use_case.search.SearchOutputData;

import javax.naming.directory.SearchResult;

public class SearchPresenter implements SearchOutputBoundary {
    private final SearchViewModel searchViewModel;
    private final SearchResultViewModel searchResultViewModel;
    private ViewManagerModel viewManagerModel;

    public SearchPresenter(ViewManagerModel viewManagerModel,
                           SearchViewModel searchViewModel,
                           SearchResultViewModel searchResultViewModel) {
        this.searchViewModel = searchViewModel;
        this.viewManagerModel = viewManagerModel;
        this.searchResultViewModel = searchResultViewModel;
    }

    /**
     * Prepares the search view with the info from the output data given.
     *
     * @param response the SearchOutputData containing the correct info to
     * display on the search screen.
     */
    @Override
    public void prepareSuccessView(SearchOutputData response) {
        // On success, switch to the SearchResult view

        SearchResultState searchResultState = searchResultViewModel.getState();
        searchResultState.setFilteredItems(response.getItemsFound());
        searchResultState.setCurrentStudent(response.getStudent());
        searchResultViewModel.setState(searchResultState);
        searchResultViewModel.firePropertyChanged();

        this.viewManagerModel.setActiveView(searchResultViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    /**
     * Prepares to display the error given.
     *
     * @param error the error to display
     */
    @Override
    public void prepareFailView(String error) {
        SearchState searchState = searchViewModel.getState();
        searchState.setFilterChoicesError(error);
        searchViewModel.firePropertyChanged();
    }
}
