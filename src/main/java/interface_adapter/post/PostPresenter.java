package interface_adapter.post;

import interface_adapter.ViewManagerModel;
import interface_adapter.home.HomeState;
import interface_adapter.home.HomeViewModel;
import use_case.post.PostOutputBoundary;
import use_case.post.PostOutputData;

public class PostPresenter implements PostOutputBoundary {
    private final PostViewModel postViewModel;
    private final HomeViewModel homeViewModel;
    private ViewManagerModel viewManagerModel;

    public PostPresenter(ViewManagerModel viewManagerModel,
                         PostViewModel postViewModel,
                         HomeViewModel homeViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.postViewModel = postViewModel;
        this.homeViewModel = homeViewModel;
    }

    @Override
    public void prepareSuccessView(PostOutputData postOutputData) {
        HomeState homeState = homeViewModel.getState();
        homeState.setUser(postOutputData.getStudent());
        this.homeViewModel.setState(homeState);
        homeViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(homeViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String s) {
        System.out.println(s);
    }
}