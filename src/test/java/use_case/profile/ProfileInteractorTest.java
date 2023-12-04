package use_case.profile;

import data_access.*;
import entities.Furniture;
import entities.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.create_order.CreateOrderDataAccessInterfaceOrder;
import use_case.post.*;
import use_case.signup.SignupUserDataAccessInterface;
import java.time.LocalDateTime;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class ProfileInteractorTest {

    @BeforeEach
    public void before() {
        Student student = new Student("John", "Doe", "700 University", "john.doe@mail.utoronto.ca");
        Furniture furniture = new Furniture("someId","John's Chair", "Chair of John",3,
                12, 12, false, "700 University", "656bad0c2c9d206181466c20",
                "Chair", "", LocalDateTime.now(), 12.34, 12.45, 15.3);
        SignupUserDataAccessInterface studentDAO = new AtlasStudentDataAccessObject();
        FurniturePostDataAccessInterface furnitureDAO = new AtlasFurnitureDataAccessObject();
        CreateOrderDataAccessInterfaceOrder orderDAO = new AtlasOrderDataAccessObject();
        try {
            studentDAO.addStudent(student);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ProfileDataAccessInterface newStudentDAO = new AtlasStudentDataAccessObject();
        try {
            furniture.setOwnerId(newStudentDAO.getStudentByEmail("john.doe@mail.utoronto.ca").getId());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            furnitureDAO.addItemToFurnitureCollection(furniture);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            orderDAO.createOrder("testEmail", "john.doe@mail.utoronto.ca", "testId",
                    "700 University", "John's Chair");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void successTest() {
        String studentID;
        Student student = new Student("someId", "John", "Doe", "700 University", "john.doe@mail.utoronto.ca");
        ProfileDataAccessInterface newStudentDAO = new AtlasStudentDataAccessObject();
        try {
            studentID = newStudentDAO.getStudentByEmail("john.doe@mail.utoronto.ca").getId();
            student.setId(studentID);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ProfileInputData inputData = new ProfileInputData(student);
        ProfileOutputBoundary successPresenter = new ProfileOutputBoundary() {
            @Override
            public void prepareSuccessView(ProfileOutputData response) {
                assertEquals("john.doe@mail.utoronto.ca", response.getCurrentStudent().getUoftEmail());
                assertEquals("john.doe@mail.utoronto.ca", response.getUoftEmail());
                assertFalse(response.getAllItems().isEmpty());
                assertFalse(response.getAllOrders().isEmpty());
                for (int i = 0; i < response.getAllItems().size(); i++) {
                    assertEquals(studentID, response.getAllItems().get(i).getOwnerId());
                }
                for (int i = 0; i < response.getAllOrders().size(); i++) {
                    assertEquals("john.doe@mail.utoronto.ca", response.getAllOrders().get(i).getSellerEmail());
                }
            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case failure is unexpected.");
            }
        };

        ProfileInputBoundary interactor = new ProfileInteractor(new AtlasStudentDataAccessObject(),
                new AtlasClothingDataAccessObject(), new AtlasFurnitureDataAccessObject(),
                new AtlasSchoolItemDataAccessObject(), new AtlasTechnologyDataAccessObject(),
                new AtlasOrderDataAccessObject(), successPresenter);
        interactor.execute(inputData);
    }

    @Test
    void failureTest() {
        Student student = new Student("someId", "John", "Doe", "700 University", "jane.doe@mail.utoronto.ca");
        ProfileDataAccessInterface newStudentDAO = new AtlasStudentDataAccessObject();
        try {
            student.setId(newStudentDAO.getStudentByEmail("john.doe@mail.utoronto.ca").getId());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ProfileInputData inputData = new ProfileInputData(student);
        ProfileOutputBoundary successPresenter = new ProfileOutputBoundary() {
            @Override
            public void prepareSuccessView(ProfileOutputData response) {
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("Student not found", error);
            }
        };

        ProfileInputBoundary interactor = new ProfileInteractor(new AtlasStudentDataAccessObject(),
                new AtlasClothingDataAccessObject(), new AtlasFurnitureDataAccessObject(),
                new AtlasSchoolItemDataAccessObject(), new AtlasTechnologyDataAccessObject(),
                new AtlasOrderDataAccessObject(), successPresenter);
        interactor.execute(inputData);
    }
}