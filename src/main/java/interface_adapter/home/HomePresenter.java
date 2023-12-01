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
    @Override
    public void prepareSuccessView(HomeOutputData response) {
        this.homeViewModel.getState().setAllPosts(response.getAllPosts());
        homeViewModel.firePropertyChanged();
    }
}
