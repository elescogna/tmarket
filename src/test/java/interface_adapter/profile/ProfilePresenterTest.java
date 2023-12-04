package interface_adapter.profile;

import entities.Item;
import entities.Order;
import entities.Student;
import interface_adapter.ViewManagerModel;
import org.junit.jupiter.api.Test;
import use_case.profile.ProfileOutputData;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProfilePresenterTest {

    @Test
    void prepareSuccessViewTest() {
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        ProfileViewModel profileViewModel = new ProfileViewModel();


        ProfilePresenter profilePresenter = new ProfilePresenter(viewManagerModel, profileViewModel);

        ArrayList<Item> items = new ArrayList<Item>();
        ArrayList<Order> orders = new ArrayList<Order>();
        Student student = new Student("someId", "John", "Doe", "700 University", "john.doe@mail.utoronto.ca");
        ProfileOutputData profileOutputData = new ProfileOutputData("ProfileName", "ProfilePassword", "ProfileEmail", "ProfileHomeAddress",
                items, orders, student);


        profilePresenter.prepareSuccessView(profileOutputData);


        ProfileState profileState = profileViewModel.getState();
        assertEquals("ProfileEmail", profileState.getUoftEmail());
        assertEquals("ProfilePassword", profileState.getPassword());
        assertEquals("ProfileHomeAddress", profileState.getHomeAddress());
        assertEquals("ProfileName", profileState.getName());
        assertEquals(orders, profileState.getOrders());
        assertEquals(items, profileState.getPostedItems());
        assertEquals(student, profileState.getCurrentStudent());
    }

    @Test
    void prepareFailViewTest() {
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        ProfileViewModel profileViewModel = new ProfileViewModel();


        ProfilePresenter profilePresenter = new ProfilePresenter(viewManagerModel, profileViewModel);

        profilePresenter.prepareFailView("Student not found.");


        ProfileState profileState = profileViewModel.getState();
        assertEquals("Student not found.", profileState.getStudentNotFoundError());
    }
}

