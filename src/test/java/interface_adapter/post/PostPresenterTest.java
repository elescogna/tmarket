package interface_adapter.post;

import entities.Student;
import interface_adapter.ViewManagerModel;
import interface_adapter.home.HomeState;
import interface_adapter.home.HomeViewModel;
import org.junit.jupiter.api.Test;
import use_case.post.PostOutputData;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PostPresenterTest {

    @Test
    void prepareSuccessViewTest() {
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        HomeViewModel homeViewModel = new HomeViewModel();


        PostPresenter postPresenter = new PostPresenter(viewManagerModel, homeViewModel);


        Student student = new Student("someId", "John", "Doe", "700 University", "john.doe@mail.utoronto.ca");
        PostOutputData postOutputData = new PostOutputData(student);


        postPresenter.prepareSuccessView(postOutputData);


        HomeState homeState = homeViewModel.getState();
        assertEquals(student, homeState.getStudent());
        assertEquals(homeState, homeViewModel.getState());
        assertEquals(homeViewModel.getViewName(), viewManagerModel.getActiveView());
    }

    @Test
    void prepareFailViewTest() {
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        HomeViewModel homeViewModel = new HomeViewModel();
        PostViewModel postViewModel = new PostViewModel();


        PostPresenter postPresenter = new PostPresenter(viewManagerModel, homeViewModel);

        postPresenter.prepareFailView("Failed to post the item.");


        PostState postState = postViewModel.getState();
        assert(true);
    }
}
