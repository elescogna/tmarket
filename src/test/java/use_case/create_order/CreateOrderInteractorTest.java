package use_case.create_order;

import data_access.*;
import entities.SchoolItem;
import entities.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.post.*;
import use_case.signup.SignupUserDataAccessInterface;
import java.time.LocalDateTime;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class CreateOrderInteractorTest {
    Student buyer;
    Student seller;
    SchoolItem item;
    @BeforeEach
    public void before() {
        seller = new Student("John", "Doe", "700 University Avenue, Toronto", "john.doe@mail.utoronto.ca");
        buyer = new Student("Jay", "Go", "700 University Avenue, Toronto", "jay.go@mail.utoronto.ca");
        item = new SchoolItem("testId","John's Pen", "Pen of John",
                12, 12, 12, false, "700 University Avenue, Toronto", "656bad0c2c9d206181466c20", "Pen",
                "", LocalDateTime.now(), "hilroy", "red");
        SchoolItemPostDataAccessInterface schoolItemDAO = new AtlasSchoolItemDataAccessObject();
        SignupUserDataAccessInterface studentDAO = new AtlasStudentDataAccessObject();
        try {
            schoolItemDAO.addItemToSchoolItemCollection(item);
            studentDAO.addStudent(seller);
            studentDAO.addStudent(buyer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void successTest() {
        CreateOrderInputData inputData = new CreateOrderInputData(item, seller, buyer.getUoftEmail(), "Yes", "", item.getName());
        CreateOrderOutputBoundary successPresenter = new CreateOrderOutputBoundary(){
            @Override
            public void prepareSuccessView() {
            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case failure is unexpected.");
            }
        };

        CreateOrderInputBoundary interactor = new CreateOrderInteractor(new AtlasOrderDataAccessObject(), new AtlasStudentDataAccessObject(), new AtlasClothingDataAccessObject(),
                new AtlasFurnitureDataAccessObject(), new AtlasSchoolItemDataAccessObject(), new AtlasTechnologyDataAccessObject(),
                successPresenter);
        interactor.execute(inputData);
    }

    @Test
    void failTest() {
        CreateOrderInputData inputData = new CreateOrderInputData(item, seller, "emailthatwon'teverexist", "Yes", "", item.getName());
        CreateOrderOutputBoundary successPresenter = new CreateOrderOutputBoundary(){
            @Override
            public void prepareSuccessView() {
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("Buyer e-mail doesn't exist.", error);
            }
        };

        CreateOrderInputBoundary interactor = new CreateOrderInteractor(new AtlasOrderDataAccessObject(), new AtlasStudentDataAccessObject(), new AtlasClothingDataAccessObject(),
                new AtlasFurnitureDataAccessObject(), new AtlasSchoolItemDataAccessObject(), new AtlasTechnologyDataAccessObject(),
                successPresenter);
        interactor.execute(inputData);
    }
}
