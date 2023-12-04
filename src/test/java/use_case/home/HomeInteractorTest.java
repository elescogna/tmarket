package use_case.home;

import data_access.*;
import entities.SchoolItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.post.*;
import java.time.LocalDateTime;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class HomeInteractorTest {

    @BeforeEach
    public void before() {
        SchoolItem item = new SchoolItem("testId", "John's Pen", "Pen of John",
                12, 12, 12, false, "700 University Avenue, Toronto", "656bad0c2c9d206181466c20", "Pen",
                "0", LocalDateTime.now(), "hilroy", "red");
        SchoolItemPostDataAccessInterface schoolItemDAO = new AtlasSchoolItemDataAccessObject();
        try {
            schoolItemDAO.addItemToSchoolItemCollection(item);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void successTest() {
        HomeInputData inputData = new HomeInputData();
        HomeOutputBoundary successPresenter = new HomeOutputBoundary() {
            @Override
            public void prepareSuccessView(HomeOutputData response) {
                assertFalse(response.getAllPosts().isEmpty());
            }
        };

        HomeInputBoundary interactor = new HomeInteractor(
                new AtlasClothingDataAccessObject(), new AtlasFurnitureDataAccessObject(),
                new AtlasSchoolItemDataAccessObject(), new AtlasTechnologyDataAccessObject(),
                successPresenter);
        interactor.execute(inputData);
    }
}
