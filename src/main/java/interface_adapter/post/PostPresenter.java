package interface_adapter.post;

import interface_adapter.ViewManagerModel;
import interface_adapter.home.HomeState;
import interface_adapter.home.HomeViewModel;
import use_case.post.PostOutputBoundary;
import use_case.post.PostOutputData;

public class PostPresenter implements PostOutputBoundary {
    private final HomeViewModel homeViewModel;
    private ViewManagerModel viewManagerModel;

    public PostPresenter(ViewManagerModel viewManagerModel, HomeViewModel
            homeViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.homeViewModel = homeViewModel;
    }

    /**
     * Prepares to display the post view with the output data given in the parameters.
     *
     * @param postOutputData the output data to display on the post view
     */
    @Override
    public void prepareSuccessView(PostOutputData postOutputData) {
        HomeState homeState = homeViewModel.getState();
        homeState.setCurrentStudent(postOutputData.getStudent());
        this.homeViewModel.setState(homeState);
        homeViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(homeViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    /**
     * Prepares to print out the error given.
     *
     * @param s the error string to print
     */
    @Override
    public void prepareFailView(String s) {
        System.out.println(s);
    }
}
