package interface_adapter.home;
import interface_adapter.ViewManagerModel;
import use_case.home.HomeOutputBoundary;
import use_case.home.HomeOutputData;

public class HomePresenter implements HomeOutputBoundary {
    private final HomeViewModel homeViewModel;
    private ViewManagerModel viewManagerModel;

    public HomePresenter(ViewManagerModel viewManagerModel,
            HomeViewModel homeViewModel) {
        this.homeViewModel = homeViewModel;
        this.viewManagerModel = viewManagerModel;
    }
    /**
     * Prepares the home view with the posts from the output data given.
     *
     * @param response the HomeOutputData containing the correct posts to display on the home screen.
     */
    @Override
    public void prepareSuccessView(HomeOutputData response) {
        this.homeViewModel.getState().setAllPosts(response.getAllPosts());
        homeViewModel.firePropertyChanged();
    }
}
