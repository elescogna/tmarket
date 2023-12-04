package interface_adapter.home;

import entities.Item;
import interface_adapter.ViewManagerModel;
import org.junit.jupiter.api.Test;
import use_case.home.HomeOutputData;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HomePresenterTest {

    @Test
    void prepareSuccessViewTest() {
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        HomeViewModel homeViewModel = new HomeViewModel();


        HomePresenter homePresenter = new HomePresenter(viewManagerModel, homeViewModel);


        ArrayList<Item> posts = new ArrayList<Item>();
        HomeOutputData homeOutputData = new HomeOutputData(posts);


        homePresenter.prepareSuccessView(homeOutputData);


        HomeState homeState = homeViewModel.getState();
        assertEquals(posts, homeState.getAllPosts());
    }
}

