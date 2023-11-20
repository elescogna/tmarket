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

    @Override
    public void prepareSuccessView(SearchOutputData response) {
        // On success, switch to the SearchResult view

        SearchResultState searchResultState = searchResultViewModel.getState();
        searchResultState.setFilteredItems(response.getItemsFound());
        this.searchResultViewModel.setState(searchResultState);
        this.searchResultViewModel.firePropertyChanged();

        this.viewManagerModel.setActiveView(searchResultViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        SearchState searchState = searchViewModel.getState();
        searchState.setFilterChoicesError(error);
        searchViewModel.firePropertyChanged();
    }
}
