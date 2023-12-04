package interface_adapter.search;

import static org.junit.jupiter.api.Assertions.*;

import entities.Student;
import org.junit.jupiter.api.Test;
import use_case.search.SearchInputBoundary;
import use_case.search.SearchInputData;

import java.util.HashMap;

class MockSearchInteractor implements SearchInputBoundary {
    private boolean executeCalled = false;
    private SearchInputData inputData;

    @Override
    public void execute(SearchInputData input) {
        this.executeCalled = true;
        this.inputData = input;
    }

    public boolean isExecuteCalled() {
        return executeCalled;
    }

    public SearchInputData getInputData() {
        return inputData;
    }
}

class SearchControllerTest {

    @Test
    void executeTest() {
        // Create a mock implementation of SearchInputBoundary
        MockSearchInteractor mockSearchInteractor = new MockSearchInteractor();

        // Create SearchController with the mock SearchInputBoundary
        SearchController searchController = new SearchController(mockSearchInteractor);

        // Call the execute method on SearchController
        HashMap<String, Object> filteredAttributes = new HashMap<>();
        filteredAttributes.put("category", "furniture");
        filteredAttributes.put("type", "chair");

        Student student = new Student("Isabelle", "izzy", "777 Bay Street", "izzy@mail.utoronto.ca");

        searchController.execute(filteredAttributes, student);

        // Verify that the execute method of SearchInputBoundary is called with the expected SearchInputData
        assertTrue(mockSearchInteractor.isExecuteCalled());
        SearchInputData expectedInputData = new SearchInputData(filteredAttributes, student);
        assertEquals(expectedInputData.getFilteredAttributes(), mockSearchInteractor.getInputData().getFilteredAttributes());
        assertEquals(expectedInputData.getCurrentStudent(), mockSearchInteractor.getInputData().getCurrentStudent());
    }
}
