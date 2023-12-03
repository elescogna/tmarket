package use_case.search;

import data_access.*;
import entities.SchoolItem;
import entities.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.post.*;
import java.time.LocalDateTime;
import java.io.IOException;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class SearchInteractorTest {

    @BeforeEach
    public void before() {
        SchoolItem schoolItem = new SchoolItem("John's Pen", "Pen of John",
                12, 12, 12, false, "700 University Avenue, Toronto", "656bad0c2c9d206181466c20", "Pen",
                "", LocalDateTime.now(), "hilroy", "red");
        SchoolItemPostDataAccessInterface schoolItemDAO = new AtlasSchoolItemDataAccessObject();
        try {
            schoolItemDAO.addItemToSchoolItemCollection(schoolItem);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void successTest() {
        Student student = new Student("John", "Doe", "700 University Avenue, Toronto", "john.doe@mail.utoronto.ca");
        HashMap<String, Object> filteredAttributes = new HashMap<>();
        filteredAttributes.put("category", "SchoolItem");
        filteredAttributes.put("type", "Pen");
        filteredAttributes.put("conditionScore", 12);
        filteredAttributes.put("price", 12);
        filteredAttributes.put("age", 12);
        filteredAttributes.put("distanceRange", 900.0);
        SearchInputData inputData = new SearchInputData(filteredAttributes, student);
        SearchOutputBoundary successPresenter = new SearchOutputBoundary() {
            @Override
            public void prepareSuccessView(SearchOutputData response) {
                assertFalse(response.getItemsFound().isEmpty());
                assertEquals(student, response.getStudent());
                for (int i = 0; i < response.getItemsFound().size(); i++) {
                    assertEquals("Pen", response.getItemsFound().get(i).getType());
                    assertEquals(12, response.getItemsFound().get(i).getCondition());
                    assertEquals(12, response.getItemsFound().get(i).getPrice());
                    assertEquals(12, response.getItemsFound().get(i).getAge());
                }
            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case failure is unexpected.");
            }
        };

        SearchInputBoundary interactor = new SearchInteractor(new AtlasClothingDataAccessObject(),
                new AtlasFurnitureDataAccessObject(), new AtlasSchoolItemDataAccessObject(), new AtlasTechnologyDataAccessObject(),
                successPresenter);
        interactor.execute(inputData);
    }

    @Test
    void failureTest() {
        Student student = new Student("John", "Doe", "700 University Avenue, Toronto", "john.doe@mail.utoronto.ca");
        HashMap<String, Object> filteredAttributes = new HashMap<>();
        filteredAttributes.put("category", "InvalidCategory");
        filteredAttributes.put("type", "Pen");
        filteredAttributes.put("conditionScore", 12);
        filteredAttributes.put("price", 12);
        filteredAttributes.put("age", 12);
        filteredAttributes.put("distanceRange", 900.0);
        SearchInputData inputData = new SearchInputData(filteredAttributes, student);
        SearchOutputBoundary successPresenter = new SearchOutputBoundary() {
            @Override
            public void prepareSuccessView(SearchOutputData response) {
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("There was an error in searching through the database.", error);
            }
        };

        SearchInputBoundary interactor = new SearchInteractor(new AtlasClothingDataAccessObject(),
                new AtlasFurnitureDataAccessObject(), new AtlasSchoolItemDataAccessObject(), new AtlasTechnologyDataAccessObject(),
                successPresenter);
        interactor.execute(inputData);
    }
}
