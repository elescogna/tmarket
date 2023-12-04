package interface_adapter.search;

import static org.junit.jupiter.api.Assertions.*;
import entities.Furniture;
import entities.Item;
import entities.Student;
import interface_adapter.ViewManagerModel;
import interface_adapter.search_result.SearchResultState;
import interface_adapter.search_result.SearchResultViewModel;
import org.junit.jupiter.api.Test;
import use_case.search.SearchOutputData;

import java.time.LocalDateTime;
import java.util.ArrayList;

class SearchPresenterTest {

    @Test
    void prepareSuccessViewTest() {
        // Create instances of classes
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        SearchViewModel searchViewModel = new SearchViewModel();
        SearchResultViewModel searchResultViewModel = new SearchResultViewModel();

        // Create SearchPresenter
        SearchPresenter searchPresenter = new SearchPresenter(viewManagerModel, searchViewModel, searchResultViewModel);

        // Create dummy SearchOutputData
        ArrayList<Item> itemsFound = new ArrayList<Item>();
        itemsFound.add(new Furniture("Chair", "Vintage Teak Chair", 4, 100, 10, false, "91 Charles Street", "", "chair", "", LocalDateTime.now(), 10.0, 10.0, 10.0 ));

        Student student = new Student("clary", "clary", "Art Gallery of Ontario", "clary@mail.utoronto.ca");

        SearchOutputData searchOutputData = new SearchOutputData(itemsFound, student);

        // Call the method to be tested
        searchPresenter.prepareSuccessView(searchOutputData);

        // Verify the expected state changes
        SearchResultState searchResultState = searchResultViewModel.getState();
        assertEquals(itemsFound, searchResultState.getFilteredItems());
        assertEquals(searchResultState, searchResultViewModel.getState());
        assertEquals(searchResultViewModel.getViewName(), viewManagerModel.getActiveView());
    }

    @Test
    void prepareFailViewTest() {
        // Create instances of classes
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        SearchViewModel searchViewModel = new SearchViewModel();
        SearchResultViewModel searchResultViewModel = new SearchResultViewModel();

        // Create SearchPresenter
        SearchPresenter searchPresenter = new SearchPresenter(viewManagerModel, searchViewModel, searchResultViewModel);

        // Call the method to be tested
        searchPresenter.prepareFailView("Invalid filter choices");

        // Verify the expected state changes
        SearchState searchState = searchViewModel.getState();
        assertEquals("Invalid filter choices", searchState.getFilterChoicesError());
        assertEquals(searchState, searchViewModel.getState());
    }
}
