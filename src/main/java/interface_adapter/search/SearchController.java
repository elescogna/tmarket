package interface_adapter.search;

import entities.Student;
import use_case.home.HomeInputBoundary;
import use_case.search.SearchInputBoundary;
import use_case.search.SearchInputData;
import use_case.search.SearchOutputBoundary;

import java.util.HashMap;

public class SearchController {
    final SearchInputBoundary searchInteractor;

    public SearchController(SearchInputBoundary searchInteractor) { this.searchInteractor = searchInteractor; }

    public void execute(HashMap<String, Object> filteredAttributes, Student currentStudent) {
        SearchInputData searchInputData = new SearchInputData(filteredAttributes, currentStudent);
        this.searchInteractor.execute(searchInputData);
    }
}
