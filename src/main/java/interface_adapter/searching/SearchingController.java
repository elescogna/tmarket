package interface_adapter.searching;

import entities.Student;
import use_case.searching.SearchingInputBoundary;
import use_case.searching.SearchingInputData;
import use_case.searching.SearchingInteractor;

public class SearchingController {

    final SearchingInputBoundary searchingInteractor;

    public SearchingController(SearchingInteractor searchingInteractor) {
        this.searchingInteractor = searchingInteractor;
    }

    public void execute(Student currentStudent) {
        SearchingInputData searchingInputData =
            new SearchingInputData(currentStudent);

        this.searchingInteractor.execute(searchingInputData);
    }
}
