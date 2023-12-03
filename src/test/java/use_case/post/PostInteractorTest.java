package use_case.post;

import data_access.AtlasClothingDataAccessObject;
import data_access.AtlasFurnitureDataAccessObject;
import data_access.AtlasSchoolItemDataAccessObject;
import data_access.AtlasTechnologyDataAccessObject;
import entities.Student;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PostInteractorTest {

    @Test
    void successTestFurniture() {
        Student student = new Student("testId", "John", "Doe", "700 University", "john.doe@mail.utoronto.ca");
        PostInputData inputData = new PostInputData(student, "Furniture", "Chair",
                "John's Chair", "Chair of John", "700 University",
                3, 12, 12, 12.34, 12.45, 15.3
        );
        PostOutputBoundary successPresenter = new PostOutputBoundary() {
            @Override
            public void prepareSuccessView(PostOutputData response) {
                assertEquals(student, response.getStudent());
            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case failure is unexpected.");
            }
        };

        PostInputBoundary interactor = new PostInteractor(new AtlasClothingDataAccessObject(), new AtlasFurnitureDataAccessObject(),
                new AtlasSchoolItemDataAccessObject(), new AtlasTechnologyDataAccessObject(), successPresenter);
        interactor.execute(inputData);
    }
    @Test
    void successTestClothing() {
        Student student = new Student("testId2", "Dan", "Wu", "800 University", "dan.wu@mail.utoronto.ca");
        PostInputData inputData = new PostInputData(student, "Clothing", "Dress",
                "Dan's Dress", "Chair of Dan", "800 University",
                3, 12, 12, 12.34, 12.45, 15.3
        );
        PostOutputBoundary successPresenter = new PostOutputBoundary() {
            @Override
            public void prepareSuccessView(PostOutputData response) {
                assertEquals(student, response.getStudent());
            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case failure is unexpected.");
            }
        };

        PostInputBoundary interactor = new PostInteractor(new AtlasClothingDataAccessObject(), new AtlasFurnitureDataAccessObject(),
                new AtlasSchoolItemDataAccessObject(), new AtlasTechnologyDataAccessObject(), successPresenter);
        interactor.execute(inputData);
    }
    @Test
    void successTestTechnology() {
        Student student = new Student("testId3", "Bob", "Tan", "900 University", "bob.tan@mail.utoronto.ca");
        PostInputData inputData = new PostInputData(student, "Technology", "Dress",
                "Bob's Dress", "Dress of Bob", "900 University",
                3, 12, 12, 12.34, 12.45, 15.3
        );
        PostOutputBoundary successPresenter = new PostOutputBoundary() {
            @Override
            public void prepareSuccessView(PostOutputData response) {
                assertEquals(student, response.getStudent());
            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case failure is unexpected.");
            }
        };

        PostInputBoundary interactor = new PostInteractor(new AtlasClothingDataAccessObject(), new AtlasFurnitureDataAccessObject(),
                new AtlasSchoolItemDataAccessObject(), new AtlasTechnologyDataAccessObject(), successPresenter);
        interactor.execute(inputData);
    }
    @Test
    void successTestSchoolItem() {
        Student student = new Student("testId4", "Jay", "Go", "950 University", "jay.go@mail.utoronto.ca");
        PostInputData inputData = new PostInputData(student, "SchoolItem", "Bag",
                "Jay's Bag", "Bag of Jay", "950 University",
                3, 12, 12, 12.34, 12.45, 15.3
        );
        PostOutputBoundary successPresenter = new PostOutputBoundary() {
            @Override
            public void prepareSuccessView(PostOutputData response) {
                assertEquals(student, response.getStudent());
            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case failure is unexpected.");
            }
        };

        PostInputBoundary interactor = new PostInteractor(new AtlasClothingDataAccessObject(), new AtlasFurnitureDataAccessObject(),
                new AtlasSchoolItemDataAccessObject(), new AtlasTechnologyDataAccessObject(), successPresenter);
        interactor.execute(inputData);
    }
}