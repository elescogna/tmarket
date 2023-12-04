package use_case.post;

import data_access.AtlasClothingDataAccessObject;
import data_access.AtlasFurnitureDataAccessObject;
import data_access.AtlasSchoolItemDataAccessObject;
import data_access.AtlasTechnologyDataAccessObject;
import entities.Student;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PostInteractorTest {
    String basePath = System.getProperty("user.dir");
    String imagePath = basePath + "/assets/images/UC2.png";
    @Test
    void successTestFurniture() {

        Student student = new Student("testId", "John", "Doe", "700 University", "john.doe@mail.utoronto.ca");
        PostInputData inputData = new PostInputData(student, "Furniture", "Chair",
                "John's Chair", "Chair of John", "700 University",
                3, 12, 12, 12.34, 12.45, 12.45, imagePath
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
    void failTestFurniture() {

        Student student = new Student("testId", "John", "Doe", "700 University", "john.doe@mail.utoronto.ca");
        PostInputData inputData = new PostInputData(student, "Furniture", "Chair",
                "John's Chair", "Chair of John", "700 University",
                3, 12, 12, 12.34, 12.45, 12.45, "0"
        );
        PostOutputBoundary successPresenter = new PostOutputBoundary() {
            @Override
            public void prepareSuccessView(PostOutputData response) {
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("Failed to post the furniture item.", error);
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
                3, 12, 12, "Old Navy", "Red", "Large", "Cotton", imagePath
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
    void failTestClothing() {
        Student student = new Student("testId2", "Dan", "Wu", "800 University", "dan.wu@mail.utoronto.ca");
        PostInputData inputData = new PostInputData(student, "Clothing", "Dress",
                "Dan's Dress", "Chair of Dan", "800 University",
                3, 12, 12, "Old Navy", "Red", "Large", "Cotton", "0"
        );
        PostOutputBoundary successPresenter = new PostOutputBoundary() {
            @Override
            public void prepareSuccessView(PostOutputData response) {
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("Failed to post the clothing item.", error);

            }
        };

        PostInputBoundary interactor = new PostInteractor(new AtlasClothingDataAccessObject(), new AtlasFurnitureDataAccessObject(),
                new AtlasSchoolItemDataAccessObject(), new AtlasTechnologyDataAccessObject(), successPresenter);
        interactor.execute(inputData);
    }
    @Test
    void successTestTechnology() {
        Student student = new Student("testId3", "Bob", "Tan", "900 University", "bob.tan@mail.utoronto.ca");
        PostInputData inputData = new PostInputData(student, "Technology", "Laptop",
                "Bob's Laptop", "Dress of Bob", "900 University",
                3, 12, 12, "Apple", "None", "Red", imagePath
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
    void failTestTechnology() {
        Student student = new Student("testId3", "Bob", "Tan", "900 University", "bob.tan@mail.utoronto.ca");
        PostInputData inputData = new PostInputData(student, "Technology", "Laptop",
                "Bob's Laptop", "Dress of Bob", "900 University",
                3, 12, 12, "Apple", "None", "Red", "0"
        );
        PostOutputBoundary successPresenter = new PostOutputBoundary() {
            @Override
            public void prepareSuccessView(PostOutputData response) {
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("Failed to post the technology item.", error);
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
                3, 12, 12, "Maped", "Red", imagePath
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
    void failTestSchoolItem() {
        Student student = new Student("testId4", "Jay", "Go", "950 University", "jay.go@mail.utoronto.ca");
        PostInputData inputData = new PostInputData(student, "SchoolItem", "Bag",
                "Jay's Bag", "Bag of Jay", "950 University",
                3, 12, 12, "Maped", "Red", "0"
        );
        PostOutputBoundary successPresenter = new PostOutputBoundary() {
            @Override
            public void prepareSuccessView(PostOutputData response) {
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("Failed to post the school item.", error);
            }
        };

        PostInputBoundary interactor = new PostInteractor(new AtlasClothingDataAccessObject(), new AtlasFurnitureDataAccessObject(),
                new AtlasSchoolItemDataAccessObject(), new AtlasTechnologyDataAccessObject(), successPresenter);
        interactor.execute(inputData);
    }
}