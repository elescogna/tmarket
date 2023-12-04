package interface_adapter.search;

import entities.Student;
import use_case.home.HomeInputBoundary;
import use_case.search.SearchInputBoundary;
import use_case.search.SearchInputData;
import use_case.search.SearchOutputBoundary;

import java.util.HashMap;

public class SearchController {
    final SearchInputBoundary searchInteractor;

    /**
     * Constructor for SearchController with the given interactor.
     *
     * @param searchInteractor the interactor with which to make the SearchController
     */
    public SearchController(SearchInputBoundary searchInteractor) { this.searchInteractor = searchInteractor; }

    /**
     * Creates input data from the parameters given and calls the search interactor with that data.
     *
     * @param filteredAttributes the filtered attributes with which to call the search interactor
     * @param currentStudent the current student with which to call the search interactor
     */
    public void execute(HashMap<String, Object> filteredAttributes, Student currentStudent) {
        SearchInputData searchInputData = new SearchInputData(filteredAttributes, currentStudent);
        this.searchInteractor.execute(searchInputData);
    }
}
